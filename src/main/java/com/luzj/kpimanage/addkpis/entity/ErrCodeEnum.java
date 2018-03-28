package com.luzj.kpimanage.addkpis.entity;

/**
 * @author luzj
 * @description: 错误、异常返回代号定义
 * @date 2018/3/22
 */
public enum ErrCodeEnum {
    SUCCESS(0,"ok"),
    NONE_PARAM(-101,"参数KEY为空"),
    ;
    private int code;
    private String msg;

    ErrCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
