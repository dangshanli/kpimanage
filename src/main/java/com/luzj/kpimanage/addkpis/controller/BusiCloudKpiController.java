package com.luzj.kpimanage.addkpis.controller;

import com.luzj.kpimanage.addkpis.service.KpiAddService;
import com.luzj.kpimanage.addkpis.vo.KpiDetails;
import com.luzj.kpimanage.common.ResultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzj
 * @description: 经分、云门户加指标特务
 * @date 2018/3/26
 */
@RestController
@RequestMapping("/busiKpi")
public class BusiCloudKpiController {
    @Autowired
    KpiAddService kpiAddService;

    @RequestMapping("/addSingleKpi")
    public ResultValue addSingleKpi(KpiDetails details){
        ResultValue rv = kpiAddService.addKpi(details);
        return rv;
    }
}
