package com.luzj.kpimanage.addkpis.repository;

import com.luzj.kpimanage.addkpis.entity.AppKpiMapInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author luzj
 * @description: 指标原表Jpa接口
 * @date 2018/3/21
 */
public interface AppKpiMapRepository extends JpaRepository<AppKpiMapInfo, String> {

    AppKpiMapInfo findByKpiCode(String kpiCode);

    /**
     * 在当前二级目录对应的指标集中搜索指标
     * @param appCode 应用代号，对应二级目录
     * @param dateType 账期日/月
     * @param kpiCode
     * @return
     */
    AppKpiMapInfo findByAppCodeAndDateTypeAndKpiCode(String appCode,String dateType,String kpiCode);

    List<AppKpiMapInfo> findByParentId(String parentId);

    /**
     * 寻找parentId下所有的子指标，在指定目录的指标集里面搜索
     * @param appCode
     * @param dateType
     * @param parentId
     * @return
     */
    List<AppKpiMapInfo> findByAppCodeAndDateTypeAndParentId(String appCode,String dateType,String parentId);
}
