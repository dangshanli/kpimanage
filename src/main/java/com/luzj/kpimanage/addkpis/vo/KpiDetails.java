package com.luzj.kpimanage.addkpis.vo;

/**
 * @author luzj
 * @description: 指标增加入参
 * @date 2018/3/21
 */
public class KpiDetails {
    private String kpiName;
    private String kpiCode;
    private String appClass;//导航分类： leader：领到首页;cloud：云门户;business:经分
    private String appNameNavigation;//导航栏appName 如：移动业务
    private String appNameSecondary;//二级目录appName 如：渠道运营
    private String dateType;//日期类型 M月 : D日
    private String unit;//单位 ：万元 万户等
    private String ceilingKpi;//上限指标，紧挨着给出的指标
    private String floorKpi;//下限指标
    private String parentKpiCode;//父指标的kpiCode,用于确定parent_id

    public KpiDetails() {
    }

    public KpiDetails(String kpiName, String kpiCode, String appClass, String appNameNavigation, String appNameSecondary, String dateType, String unit, String ceilingKpi, String floorKpi, String parentKpiCode) {
        this.kpiName = kpiName;
        this.kpiCode = kpiCode;
        this.appClass = appClass;
        this.appNameNavigation = appNameNavigation;
        this.appNameSecondary = appNameSecondary;
        this.dateType = dateType;
        this.unit = unit;
        this.ceilingKpi = ceilingKpi;
        this.floorKpi = floorKpi;
        this.parentKpiCode = parentKpiCode;
    }

    public String getParentKpiCode() {
        return parentKpiCode;
    }

    public void setParentKpiCode(String parentKpiCode) {
        this.parentKpiCode = parentKpiCode;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public String getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(String kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public String getAppNameNavigation() {
        return appNameNavigation;
    }

    public void setAppNameNavigation(String appNameNavigation) {
        this.appNameNavigation = appNameNavigation;
    }

    public String getAppNameSecondary() {
        return appNameSecondary;
    }

    public void setAppNameSecondary(String appNameSecondary) {
        this.appNameSecondary = appNameSecondary;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCeilingKpi() {
        return ceilingKpi;
    }

    public void setCeilingKpi(String ceilingKpi) {
        this.ceilingKpi = ceilingKpi;
    }

    public String getFloorKpi() {
        return floorKpi;
    }

    public void setFloorKpi(String floorKpi) {
        this.floorKpi = floorKpi;
    }
}
