package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * AQI 统计数据表（供大屏调用）
 * <p>对应数据库表 {@code aqi_statistics}</p>
 */
@TableName("aqi_statistics")
public class AqiStatistics {

    /** 主键 */
    @TableId(value = "stid", type = IdType.AUTO)
    private Long stid;

    /** 统计类型：AQI_LEVEL_DIST=等级分布 PROVINCE_EXCEED=各省超标 MONTH_TREND=12个月趋势 GRID_COVERAGE=网格覆盖率 POLLUTANT_EXCEED=污染物超标 */
    private String statType;

    /** 统计维度：PROVINCE/CITY/GRID/MONTH/DAY/ALL */
    private String statDimension;

    /** 维度对象 id（省/市/网格 id，按维度含义使用） */
    private Long dimensionId;

    /** 维度对象名称（省名/市名/网格名/月份） */
    private String dimensionName;

    /** 统计周期，如 2026-10、2026 */
    private String statPeriod;

    /** AQI 等级 id（等级分布统计时使用） */
    private Long aqiLevelId;

    /** 污染物种类：PM25 / SO2 / CO */
    private String pollutant;

    /** 指标值（数量或百分比，按 stat_type 解释） */
    private BigDecimal metricValue;

    /** 生成时间 */
    private Timestamp createTime;

    public Long getStid() {
        return stid;
    }

    public void setStid(Long stid) {
        this.stid = stid;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public String getStatDimension() {
        return statDimension;
    }

    public void setStatDimension(String statDimension) {
        this.statDimension = statDimension;
    }

    public Long getDimensionId() {
        return dimensionId;
    }

    public void setDimensionId(Long dimensionId) {
        this.dimensionId = dimensionId;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getStatPeriod() {
        return statPeriod;
    }

    public void setStatPeriod(String statPeriod) {
        this.statPeriod = statPeriod;
    }

    public Long getAqiLevelId() {
        return aqiLevelId;
    }

    public void setAqiLevelId(Long aqiLevelId) {
        this.aqiLevelId = aqiLevelId;
    }

    public String getPollutant() {
        return pollutant;
    }

    public void setPollutant(String pollutant) {
        this.pollutant = pollutant;
    }

    public BigDecimal getMetricValue() {
        return metricValue;
    }

    public void setMetricValue(BigDecimal metricValue) {
        this.metricValue = metricValue;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "AqiStatistics{"
                + "stid=" + stid
                + ", statType='" + statType + '\''
                + ", statDimension='" + statDimension + '\''
                + ", dimensionId=" + dimensionId
                + ", dimensionName='" + dimensionName + '\''
                + ", statPeriod='" + statPeriod + '\''
                + ", aqiLevelId=" + aqiLevelId
                + ", pollutant='" + pollutant + '\''
                + ", metricValue=" + metricValue
                + ", createTime=" + createTime
                + '}';
    }
}
