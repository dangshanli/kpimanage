package com.luzj.kpimanage.addkpis.repository;

import com.luzj.kpimanage.addkpis.entity.AppBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author luzj
 * @description: 导航栏表Jpa接口
 * @date 2018/3/16
 */
public interface AppBaseInfoRepository extends JpaRepository<AppBaseInfo,String> {

    List<AppBaseInfo> findByAppClass(String appClass);

    @Query(value = "select t from AppBaseInfo t where t.appName like ?1% and t.dateType = '*'")
    List<AppBaseInfo> findByAppNameContaining(String appName);

    AppBaseInfo findByAppParentCodeAndDateTypeAndAppName(String parentCode,String dateType,String appName);


}
