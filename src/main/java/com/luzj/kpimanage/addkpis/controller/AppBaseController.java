package com.luzj.kpimanage.addkpis.controller;

import com.luzj.kpimanage.addkpis.service.AppInfoService;
import com.luzj.kpimanage.addkpis.service.impl.AppInfoServiceImpl;
import com.luzj.kpimanage.common.ResultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luzj
 * @description:
 * @date 2018/3/16
 */
@RestController
@RequestMapping("/appInfo")
public class AppBaseController {
    @Autowired
    AppInfoService appInfoService;

    @RequestMapping("/findBaseInfo")
    ResultValue findAppBaseInfo(String appName){
        return appInfoService.findAppInfoByName(appName);
    }
}
