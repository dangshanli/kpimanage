package com.luzj.kpimanage.addkpis.service;

import com.luzj.kpimanage.addkpis.vo.KpiDetails;
import com.luzj.kpimanage.common.ResultValue;

/**
 * @author luzj
 * @description:
 * @date 2018/3/21
 */
public interface KpiAddService {
    ResultValue addKpi(KpiDetails params);
}
