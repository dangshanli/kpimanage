package com.luzj.kpimanage.addkpis.entity;

/**
 * @author luzj
 * @description: 枚举所有的单位情况
 * @date 2018/3/23
 */
public enum UnitEnum {
    TEN_THOUSANDS("万",10000,"###,##0.00"),//以万开头的
    AVG_VALUE("/",1,"###,##0.00"),//平均值，即(分钟/户)这种平均值
    PERCENT("%",1,"###,##0.00"),//百分比
    ONE("个",1,"###,##0.00"),//个
    PERSON("户",1,"###,##0.00"),//户
    TB("TB",1048576,"###,##0.00"),//TB
    BILLION("亿",100000000,"###,##0.00")//以亿开头，比如 亿分钟
    ;
    private String unit;
    private long ratio;
    private String format;

    UnitEnum(String unit, long ratio, String format) {
        this.unit = unit;
        this.ratio = ratio;
        this.format = format;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public long getRatio() {
        return ratio;
    }

    public void setRatio(long ratio) {
        this.ratio = ratio;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
