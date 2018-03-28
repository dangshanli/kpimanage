package com.luzj.kpimanage.addkpis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author luzj
 * @description: 指标原表
 * @date 2018/3/21
 */
@Data
@Table(name = "kc_dm_appkpi_map_info")
@Entity
public class AppKpiMapInfo {

    @Column(name = "app_code",unique = false)
    private String appCode;

    @Column(name = "date_type")
    private String dateType;
    @Id
    @Column(name = "kpi_id")
    private String kpiId;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "kpi_code")
    private String kpiCode;

    @Column(name = "kpi_name")
    private String kpiName;

    @Column(name = "display_kpi_name")
    private String displayKpiName;

    @Column(name = "is_space")
    private int isSpace;

    @Column(name = "kpi_prefix")
    private String kpiPrefix;

    @Column(name = "kpi_unit")
    private String kpiUnit;

    @Column(name = "display_ratio")
    private long displayRatio;

    @Column(name = "display_format")
    private String displayFormat;

    @Column(name = "is_positive")
    private int isPositive;

    @Column(name = "is_inuse")
    private int isInUse;

    @Column(name = "is_sum")
    private int isSum;

    private double ord;

    @Column(name = "is_extend")
    private int isExtend;

    @Column(name = "is_extended")
    private int isExtended;

    @Column(name = "unit_area")
    private String unitArea;

    @Column(name = "ratio_area")
    private long ratioArea;

    @Column(name = "format_area")
    private String formatArea;
}
