package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * AQI 实测数据表
 * <p>对应数据库表 {@code aqi_measure}</p>
 */
@TableName("aqi_measure")
public class AqiMeasure {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 关联工单 id（feedback_order.id） */
    private Long orderId;

    /** 冗余-省 id（统计用） */
    private Long provinceId;

    /** 冗余-市 id（统计用） */
    private Long cityId;

    /** SO2 浓度（ug/m³） */
    private BigDecimal so2;

    /** CO 浓度（ug/m³） */
    private BigDecimal co;

    /** PM2.5 浓度（ug/m³） */
    private BigDecimal pm25;

    /** 自动计算出的实测 AQI 等级 id（aqi_level.id） */
    private Long measuredLevelId;

    /** 录入网格员 user_id */
    private Long gridManagerId;

    /** 确认日期（列表筛选用） */
    private LocalDate confirmDate;

    /** 确认时间 */
    private LocalDateTime confirmTime;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public BigDecimal getSo2() {
        return so2;
    }

    public void setSo2(BigDecimal so2) {
        this.so2 = so2;
    }

    public BigDecimal getCo() {
        return co;
    }

    public void setCo(BigDecimal co) {
        this.co = co;
    }

    public BigDecimal getPm25() {
        return pm25;
    }

    public void setPm25(BigDecimal pm25) {
        this.pm25 = pm25;
    }

    public Long getMeasuredLevelId() {
        return measuredLevelId;
    }

    public void setMeasuredLevelId(Long measuredLevelId) {
        this.measuredLevelId = measuredLevelId;
    }

    public Long getGridManagerId() {
        return gridManagerId;
    }

    public void setGridManagerId(Long gridManagerId) {
        this.gridManagerId = gridManagerId;
    }

    public LocalDate getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(LocalDate confirmDate) {
        this.confirmDate = confirmDate;
    }

    public LocalDateTime getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(LocalDateTime confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "AqiMeasure{"
                + "id=" + id
                + ", orderId=" + orderId
                + ", provinceId=" + provinceId
                + ", cityId=" + cityId
                + ", so2=" + so2
                + ", co=" + co
                + ", pm25=" + pm25
                + ", measuredLevelId=" + measuredLevelId
                + ", gridManagerId=" + gridManagerId
                + ", confirmDate=" + confirmDate
                + ", confirmTime=" + confirmTime
                + ", remark='" + remark + '\''
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
