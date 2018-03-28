package com.luzj.kpimanage.addkpis.repository;

import com.luzj.kpimanage.addkpis.entity.UserAppKpiMap;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author luzj
 * @description: id原表jpa接口
 * @date 2018/3/21
 */
public interface UserAppKpiRepository extends JpaRepository<UserAppKpiMap,String> {

}
