package com.luzj.kpimanage.addkpis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author luzj
 * @description: kc_dim_app_base_info ,导航总览表 实体类
 * @date 2018/3/16
 */
@Data
@Table(name = "kc_dim_app_base_info")
@Entity
public class AppBaseInfo {
    @Id
    @Column(name = "app_code")
    private String appCode;

    @Column(name = "date_type",nullable = false)
    private String dateType;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "app_class")
    private String appClass;

    @Column(name = "app_parent_code")
    private String appParentCode;

    @Column(name = "data_table",length = 320)
    private String dataTable;

    @Column(name = "is_inuse")
    private int isInuse;

    @Column(name = "url")
    private String url;

    @Column(name = "app_desc")
    private String appDesc;

    @Column(name = "ord")
    private String ord;

    @Column(name = "display_type")
    private String displayType;

    @Column(name = "ignore_user")
    private String ignoreUser;

    @Column(name = "memo")
    private String memo;

    @Column(name = "table_module")
    private String tableModule;

    @Column(name = "pic_module")
    private String picModule;

    @Column(name = "max_kpi_num")
    private int maxKpiNum;

    @Column(name = "is_open_win")
    private int isOpenWin;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "acct_date")
    private String acctDate;

    @Column(name = "deploy_id")
    private String deployId;

    @Column(name = "min_date")
    private String minDate;

    @Column(name = "max_date")
    private String maxDate;

    @Column(name = "tab_class")
    private String tabClass;

    @Column(name = "data_time")
    private Date dataTime;

    @Column(name = "update_date")
    private String updateDate;

    public AppBaseInfo() {
    }
}
