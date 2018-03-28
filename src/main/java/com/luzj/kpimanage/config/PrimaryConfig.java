package com.luzj.kpimanage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author luzj
 * @description: 第一数据源的配置
 * @date 2018/3/14
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = {"com.luzj.kpimanage.addkpis.repository"},
        transactionManagerRef = "transactionManagerCustomer",
        entityManagerFactoryRef = "customerEntityManagerFactory")
public class PrimaryConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.primary")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "customerEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(firstDataSource())
                .packages("com.luzj.kpimanage.addkpis.entity")
                .persistenceUnit("customers")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String,String > getVendorProperties(DataSource dataSource){
        return jpaProperties.getHibernateProperties(dataSource);
    }

    @Bean(name = "transactionManagerCustomer")
    PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder){
        return new JpaTransactionManager(customerEntityManagerFactory(builder).getObject());
    }

}
