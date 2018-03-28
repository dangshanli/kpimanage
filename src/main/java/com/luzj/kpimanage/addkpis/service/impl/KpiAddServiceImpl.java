package com.luzj.kpimanage.addkpis.service.impl;

import com.luzj.kpimanage.addkpis.entity.*;
import com.luzj.kpimanage.addkpis.repository.AppBaseInfoRepository;
import com.luzj.kpimanage.addkpis.repository.AppKpiMapRepository;
import com.luzj.kpimanage.addkpis.repository.UserAppKpiRepository;
import com.luzj.kpimanage.addkpis.service.KpiAddService;
import com.luzj.kpimanage.addkpis.vo.KpiDetails;
import com.luzj.kpimanage.common.ResultValue;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author luzj
 * @description: 添加单指标组件
 * @date 2018/3/22
 */
@Service
public class KpiAddServiceImpl implements KpiAddService {
    @Autowired
    AppBaseInfoRepository appBaseInfoRepository;//导航表
    @Autowired
    AppKpiMapRepository kpiMapRepository;//指标表
    @Autowired
    UserAppKpiRepository userAppKpiRepository;//ip表

    public static final int KPI_ID_LENGTH = 6;
    public static final String FORMAT = "###,##0.00";
    public static final int ORD_UP_DOWN_STEP = 100;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    @Transactional(value = "transactionManagerCustomer")
    public ResultValue addKpi(KpiDetails params) {
        ResultValue rv = new ResultValue();

        //todo 验证参数完整
        if (params == null) {
            rv.setCode(ErrCodeEnum.NONE_PARAM.getCode());
            rv.setMsg(ErrCodeEnum.NONE_PARAM.getMsg().replace("KEY", "KpiDetails"));
            return rv;
        }
        String empty = checkParam(params);
        if (empty.equals("ok"))
            logger.info("KPIDetail参数完备");
        else if (empty.equals("parentKpiCode"))
            logger.info("指标参数无父指标");
        else {
            logger.error("必要指标" + empty + "为空");
            rv.setCode(ErrCodeEnum.NONE_PARAM.getCode());
            rv.setMsg(ErrCodeEnum.NONE_PARAM.getMsg().replace("KEY", empty));
            return rv;
        }

        //todo 取出参数
        String kpiName = params.getKpiName();//指标名称
        String kpiCode = params.getKpiCode();//指标code
        String appClass = params.getAppClass();//所属分类：leader cloud other
        String navigationAppName = params.getAppNameNavigation();//导航栏名称：移动业务 3G套餐 2G套餐 固网业务 融合业务
        String secondaryAppName = params.getAppNameSecondary();//二级菜单appName,运营概况 渠道运营 合约发展等
        String dateType = params.getDateType();//账期类型，D(日) M(月)
        String kpiUnit = params.getUnit();//指标单位 万元 万户 等
        String parentKpiCode = params.getParentKpiCode();//父级指标kpiCode
        String ceilingKpi = params.getCeilingKpi();//上限kpiCode
        String floorKpi = params.getFloorKpi();//下限KpiCode

        //TODO 执行指标添加业务
        //todo 拿到导航栏Appcode
        List<AppBaseInfo> baseInfos = appBaseInfoRepository.findByAppNameContaining(navigationAppName);

        String parentAppCode = "";
        for (AppBaseInfo baseInfo : baseInfos) {
            if (appClass.equals(AppClassEnum.CLOUD.getAppClass()) || appClass.equals(AppClassEnum.LEADER.getAppClass())) {
                if (appClass.equals(baseInfo.getAppClass()))
                    parentAppCode = baseInfo.getAppCode();
            } else {
                String temp = baseInfo.getAppClass();
                if (!temp.equals(AppClassEnum.LEADER.getAppClass()) && !temp.equals(AppClassEnum.CLOUD.getAppClass()))
                    parentAppCode = baseInfo.getAppCode();
            }
        }
        logger.info("app_class:" + appClass);
        logger.info("parentAppCode:" + parentAppCode);

        //todo 拿到二级菜单AppBaseInfo信息
        AppBaseInfo appSecondary = appBaseInfoRepository.findByAppParentCodeAndDateTypeAndAppName(parentAppCode, dateType, secondaryAppName);

        //todo 包装kc_dm_appkpi_map_info表条目字段
        AppKpiMapInfo kpiMapInfo = fillKpiMapInfo(params, appSecondary);
        AppKpiMapInfo returnedMapInfo = kpiMapRepository.save(kpiMapInfo);

        //todo 包装kc_dm_user_appkpi_map id原表
        UserAppKpiMap userAppKpiMap = new UserAppKpiMap();
        userAppKpiMap.setUserId("default");
        userAppKpiMap.setAppCode(kpiCode);
        userAppKpiMap.setDataType(dateType);
        userAppKpiMap.setKpiId(returnedMapInfo.getKpiId());

        userAppKpiRepository.save(userAppKpiMap);

        rv.setData(returnedMapInfo);
        return rv;
    }

    /**
     * 填充一个APPKPIMapInfo对象，对应表：kc_dm_appkpi_map_info
     *
     * @param kpiDetails   入参参数
     * @param appSecondary 二级目录的APPBaseInfo实体信息类
     * @return
     */
    private AppKpiMapInfo fillKpiMapInfo(KpiDetails kpiDetails, AppBaseInfo appSecondary) {
        //todo appCode+dateType 可以限定住某一个二级目录的指标集
        String appCode = appSecondary.getAppCode();
        String dateType = kpiDetails.getDateType();

        //todo 填充AppKpiMapInfo对象
        AppKpiMapInfo kpiMapInfo = new AppKpiMapInfo();
        kpiMapInfo.setAppCode(appCode);//app_code
        kpiMapInfo.setDateType(dateType);//date_type
        kpiMapInfo.setKpiCode(kpiDetails.getKpiCode());//kpi_code
        kpiMapInfo.setKpiName(kpiDetails.getKpiName());//kpi_name

        //display_kpi_name 和 kpi_prefix
        String kpiName = kpiDetails.getKpiName();
        if (kpiName.startsWith("其中")) {
            kpiMapInfo.setDisplayKpiName(kpiName.substring(kpiName.indexOf(":") + 1));
            kpiMapInfo.setKpiPrefix("其中:");//kpi_prefix
        } else {
            kpiMapInfo.setDisplayKpiName(kpiName);
        }

        //todo kpi_id 和 parent_kpi_id 和 is_space
        String kpiId = null;
        String parentKpiId = null;
        int isSpace;
        String parentKpiCode = kpiDetails.getParentKpiCode();
        String random = RandomStringUtils.random(KPI_ID_LENGTH, "0123456789abcdef");
        //todo 没有父指标则parent_kpi_id为空
        if (null == parentKpiCode || "".equals(parentKpiCode)) {
            kpiId = kpiDetails.getKpiCode() + "_" + random;
            isSpace = 0;
            //todo 有父指标，则填充parent_kpi_id以及进一步计算is_space
        } else {
            AppKpiMapInfo parentKpiMapInfo = kpiMapRepository.findByAppCodeAndDateTypeAndKpiCode(appCode, dateType, parentKpiCode);//当前指标的父指标
            parentKpiId = parentKpiMapInfo.getKpiId();

            if (!StringUtils.isEmpty(parentKpiId))
                kpiId = parentKpiId + "_" + kpiDetails.getKpiCode() + "_" + random;
            else
                kpiId = kpiDetails.getKpiCode() + "_" + random;

            //当前指标有祖父指标时
            if (!StringUtils.isEmpty(parentKpiMapInfo.getParentId())) {
                isSpace = 29;
            } else {
                if (hasSibling(parentKpiId, appCode, dateType))
                    isSpace = 29;
                else
                    isSpace = 0;
            }
        }

        //todo 如果"其中"开头，isSpace必为0
        if (kpiName.startsWith("其中"))
            isSpace = 0;

        kpiMapInfo.setKpiId(kpiId);
        if (parentKpiId != null)
            kpiMapInfo.setParentId(parentKpiId);
        kpiMapInfo.setIsSpace(isSpace);

        //todo is_positive=0 is_inuse = 1 is_sum = 1
        kpiMapInfo.setIsPositive(0);
        kpiMapInfo.setIsInUse(1);
        kpiMapInfo.setIsSum(1);

        //todo is_extend=1 is_extended=1
        kpiMapInfo.setIsExtend(1);
        kpiMapInfo.setIsExtend(1);

        //todo kpi_unit 和 display_ratio 和 display_format
        //todo unit_area 和 ratio_area 和 format_area
        String unit = kpiDetails.getUnit();
        kpiMapInfo.setKpiUnit(unit);
        kpiMapInfo.setUnitArea(unit);

        long ratio = 0L;
        if (unit.contains(UnitEnum.PERCENT.getUnit()))// 平局值 分钟/户
            ratio = UnitEnum.AVG_VALUE.getRatio();
        else if (unit.startsWith(UnitEnum.TEN_THOUSANDS.getUnit()))
            ratio = UnitEnum.TEN_THOUSANDS.getRatio();
        else if (unit.equals(UnitEnum.PERCENT.getUnit()))
            ratio = UnitEnum.PERCENT.getRatio();
        else if (unit.equals(UnitEnum.ONE.getUnit()))
            ratio = UnitEnum.ONE.getRatio();
        else if (unit.equals(UnitEnum.PERSON.getUnit()))
            ratio = UnitEnum.PERSON.getRatio();
        else if (unit.startsWith(UnitEnum.BILLION.getUnit()))
            ratio = UnitEnum.BILLION.getRatio();
        else if (unit.equals(UnitEnum.TB.getUnit()))
            ratio = UnitEnum.TB.getRatio();
        else
            ratio = 1;

        kpiMapInfo.setDisplayRatio(ratio);
        kpiMapInfo.setRatioArea(ratio);

        kpiMapInfo.setDisplayFormat(FORMAT);
        kpiMapInfo.setFormatArea(FORMAT);


        //todo ord
        double ordUp = 0;//上行ord
        double ordDown = 0;//下行ord
        double ord = 0;//当前指标ord，ord升序
        String ceilingKpi = kpiDetails.getCeilingKpi();
        String floorKpi = kpiDetails.getFloorKpi();

        if (!StringUtils.isEmpty(ceilingKpi)) {
            AppKpiMapInfo upKpi = kpiMapRepository.findByAppCodeAndDateTypeAndKpiCode(appCode, dateType, ceilingKpi);
            if (upKpi != null)
                ordUp = upKpi.getOrd();
        }

        if (!StringUtils.isEmpty(floorKpi)) {
            AppKpiMapInfo downKpi = kpiMapRepository.findByAppCodeAndDateTypeAndKpiCode(appCode, dateType, floorKpi);
            if (downKpi != null)
                ordDown = downKpi.getOrd();
        }

        if (ordDown == 0 && ordUp > 0)
            ord = ordUp + ORD_UP_DOWN_STEP;
        if (ordDown > 0 && ordDown == 0)
            ord = ordDown - ORD_UP_DOWN_STEP;
        if (ordUp > 0 && ordDown > 0) {
//            ord = div((ordDown + ordUp), 2.00, 2);
            ord = calMidNumber(ordUp, ordDown);
        }
//        if (ordUp == 0 && ordDown ==0) //注意都没有拿到ord的状况

        kpiMapInfo.setOrd(ord);
        return kpiMapInfo;
    }

    /**
     * 产生两个数的中间数
     *
     * @param up   小的数
     * @param down 大的数
     * @return 中间数
     */
    private double calMidNumber(double up, double down) {
        double mid = 0;
        double step = div((down - up), 2);
        mid = up + step;

        return mid;
    }

    /**
     * 当前指标有兄弟指标名称带有"其中"的
     *
     * @return
     */
    private boolean hasSibling(String parentKpiId, String appCode, String dateType) {
        List<AppKpiMapInfo> kpiMapInfos = kpiMapRepository.findByAppCodeAndDateTypeAndParentId(appCode, dateType, parentKpiId);
        for (AppKpiMapInfo info : kpiMapInfos) {
            if (info.getKpiName().startsWith("其中"))
                return true;
        }
        return false;
    }

    /**
     * 检查参数完整性
     *
     * @param params
     * @return 返回null或者为空的参数
     */
    private String checkParam(KpiDetails params) {
        if (params == null)
            return "KpiDetails";
        if (StringUtils.isEmpty(params.getKpiName()))
            return "kpiName";
        if (StringUtils.isEmpty(params.getKpiCode()))
            return "kpiCode";
        if (StringUtils.isEmpty(params.getAppClass()))
            return "appClass";
        if (StringUtils.isEmpty(params.getAppNameNavigation()))
            return "appNameNavigation";
        if (StringUtils.isEmpty(params.getAppNameSecondary()))
            return "appNameSecondary";
        if (StringUtils.isEmpty(params.getDateType()))
            return "dateType";
        if (StringUtils.isEmpty(params.getUnit()))
            return "unit";
        if (StringUtils.isEmpty(params.getFloorKpi()) && StringUtils.isEmpty(params.getCeilingKpi()))
            return "floorKpi || ceilingKpi";
        if (StringUtils.isEmpty(params.getParentKpiCode()))
            return "parentKpiCode";
        return "ok";
    }

    /**
     * double数值精确除法
     *
     * @param dividend
     * @param divisor
     * @param len      保留位数
     * @return
     */
    private double div(double dividend, double divisor) {
        BigDecimal big1 = new BigDecimal(dividend);
        BigDecimal big2 = new BigDecimal(divisor);
        return big1.divide(big2).doubleValue();
    }

    public static void main(String[] args) {
       /* String s = "其中韩灵儿:陆雪琪,长相思";
        System.out.println(s.substring(0, 3).equals("其中:"));
        System.out.println(s.substring(s.indexOf(":") + 1));
        System.out.println(s.startsWith("其中"));*/

        double a = new KpiAddServiceImpl().calMidNumber(1105000.019, 1105000.02);
        System.out.println(a);
    }
}
