package com.luzj.kpimanage.addkpis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luzj
 * @description: id原表
 * @date 2018/3/21
 */
@Data
@Table(name = "kc_dm_user_appkpi_map")
@Entity
public class UserAppKpiMap {
    @Id
    @Column(name = "kpi_id")
    private String kpiId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "app_code")
    private String appCode;

    @Column(name = "date_type")
    private String dataType;



    public UserAppKpiMap() {
    }
}
