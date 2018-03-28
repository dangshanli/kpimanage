package com.luzj.kpimanage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author luzj
 * @description: 第二数据源的配置
 * @date 2018/3/14
 */
@Configuration
@EnableTransactionManagement//开启事务管理
@EnableJpaRepositories(
        basePackages = {"com.luzj.kpimanage.test.repository"},//jpa repository定义位置
        transactionManagerRef = "transactionManagerOrder",//事务管理bean
        entityManagerFactoryRef = "orderEntityManagerFactory")//实体管理bean
public class SecondaryConfig {

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.secondary")
    public DataSource secondDataSource() {//数据源
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "orderEntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(secondDataSource())
                .packages("com.luzj.kpimanage.test.entity")//实体类位置
                .persistenceUnit("orders")
                .build();
    }

    @Autowired
    private JpaProperties jpaProperties;

    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }

    /**
     * 事务管理bean
     * @param builder
     * @return
     */
    @Bean(name = "transactionManagerOrder")
    @Primary
    PlatformTransactionManager transactionManagerOrder(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(orderEntityManagerFactory(builder).getObject());
    }
}
