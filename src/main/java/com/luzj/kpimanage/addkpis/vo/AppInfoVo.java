package com.luzj.kpimanage.addkpis.vo;

/**
 * @author luzj
 * @description:
 * @date 2018/3/16
 */
public class AppInfoVo {

    private String appCode;
    private String dateType;
    private String appClass;
    private String appParentCode;
    private String ord;

    public AppInfoVo() {
    }

    public AppInfoVo(String appCode, String dateType, String appClass, String appParentCode, String ord) {
        this.appCode = appCode;
        this.dateType = dateType;
        this.appClass = appClass;
        this.appParentCode = appParentCode;
        this.ord = ord;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public String getAppParentCode() {
        return appParentCode;
    }

    public void setAppParentCode(String appParentCode) {
        this.appParentCode = appParentCode;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }
}
