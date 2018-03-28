package com.luzj.kpimanage.addkpis.entity;

/**
 * @author luzj
 * @description:
 * @date 2018/3/22
 */
public enum AppClassEnum {
    // 导航栏信息表，经分——云门户——领导首页
    LEADER("leader","领导首页"),
    CLOUD("cloud","云门户"),
    BUSI_ANALYSIS("business","经分")
    ;

    private String appClass;
    private String name;

    AppClassEnum(String appClass, String name) {
        this.appClass = appClass;
        this.name = name;
    }

    public String getAppClass() {
        return appClass;
    }

    public void setAppClass(String appClass) {
        this.appClass = appClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
