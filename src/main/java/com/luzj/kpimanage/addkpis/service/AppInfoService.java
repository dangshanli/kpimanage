package com.luzj.kpimanage.addkpis.service;

import com.luzj.kpimanage.common.ResultValue;

/**
 * @author luzj
 * @description:
 * @date 2018/3/16
 */
public interface AppInfoService {

    ResultValue findAppInfoByName(String AppName);
}
