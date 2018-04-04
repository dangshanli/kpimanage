package com.luzj.kpimanage.addkpis.service.impl;

import com.luzj.kpimanage.addkpis.entity.AppBaseInfo;
import com.luzj.kpimanage.addkpis.log.MyLog;
import com.luzj.kpimanage.addkpis.repository.AppBaseInfoRepository;
import com.luzj.kpimanage.addkpis.service.AppInfoService;
import com.luzj.kpimanage.addkpis.vo.AppInfoVo;
import com.luzj.kpimanage.common.ResultValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luzj
 * @description:
 * @date 2018/3/16
 */
@Service
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    AppBaseInfoRepository baseInfoRepository;

    @Override
    @MyLog()
    public ResultValue findAppInfoByName(String appName) {
        ResultValue rv = new ResultValue();
        List<AppBaseInfo> appInfos = null;
        List<AppInfoVo> appInfoVos= null;
        try {
            appInfos = baseInfoRepository.findByAppNameContaining(appName);
            appInfoVos = fillVo(appInfos);
        }catch (Exception e){
            rv.setMsg("exception");
            rv.setCode(-1);
            return rv;
        }

        rv.setData(appInfoVos);

        return rv;
    }

    private List<AppInfoVo> fillVo(List<AppBaseInfo> appInfos){
        if (appInfos == null || appInfos.size() == 0)
            return null;
        List<AppInfoVo> vos = new ArrayList<>();
        for (AppBaseInfo baseInfo: appInfos) {
            AppInfoVo vo = new AppInfoVo();
            vo.setAppClass(baseInfo.getAppClass());
            vo.setAppCode(baseInfo.getAppCode());
            vo.setAppParentCode(baseInfo.getAppParentCode());
            vo.setDateType(baseInfo.getDateType());
            vo.setOrd(baseInfo.getOrd());
            vos.add(vo);
        }

        return vos;
    }
}
