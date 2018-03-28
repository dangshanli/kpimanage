package com.luzj.kpimanage.common;

/**
 * @author luzj
 * @description: 接口的标准返回参数
 * @date 2018/3/13
 */
public class ResultValue<T> {
    private Integer code = 0;//结果状态码，0表示OK，其他表示异常或错误
    private String msg = "OK";//结果返回信息
    private T data;//返回数据集合

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
