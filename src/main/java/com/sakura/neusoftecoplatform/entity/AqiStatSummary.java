package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 统计指标汇总表（大屏指标卡片）
 * <p>对应数据库表 {@code aqi_stat_summary}</p>
 */
@TableName("aqi_stat_summary")
public class AqiStatSummary {

    /** 主键 */
    @TableId(value = "smid", type = IdType.AUTO)
    private Long smid;

    /** 统计周期，如 2026-10 */
    private String statPeriod;

    /** 检测总量 */
    private Integer measureTotal;

    /** 污染数量 */
    private Integer pollutionCount;

    /** 向好数量 */
    private Integer improvementCount;

    /** 网格总数 */
    private Integer gridTotal;

    /** 已覆盖网格数 */
    private Integer gridCovered;

    /** 网格覆盖率（%） */
    private BigDecimal gridCoverage;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    public Long getSmid() {
        return smid;
    }

    public void setSmid(Long smid) {
        this.smid = smid;
    }

    public String getStatPeriod() {
        return statPeriod;
    }

    public void setStatPeriod(String statPeriod) {
        this.statPeriod = statPeriod;
    }

    public Integer getMeasureTotal() {
        return measureTotal;
    }

    public void setMeasureTotal(Integer measureTotal) {
        this.measureTotal = measureTotal;
    }

    public Integer getPollutionCount() {
        return pollutionCount;
    }

    public void setPollutionCount(Integer pollutionCount) {
        this.pollutionCount = pollutionCount;
    }

    public Integer getImprovementCount() {
        return improvementCount;
    }

    public void setImprovementCount(Integer improvementCount) {
        this.improvementCount = improvementCount;
    }

    public Integer getGridTotal() {
        return gridTotal;
    }

    public void setGridTotal(Integer gridTotal) {
        this.gridTotal = gridTotal;
    }

    public Integer getGridCovered() {
        return gridCovered;
    }

    public void setGridCovered(Integer gridCovered) {
        this.gridCovered = gridCovered;
    }

    public BigDecimal getGridCoverage() {
        return gridCoverage;
    }

    public void setGridCoverage(BigDecimal gridCoverage) {
        this.gridCoverage = gridCoverage;
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
        return "AqiStatSummary{"
                + "smid=" + smid
                + ", statPeriod='" + statPeriod + '\''
                + ", measureTotal=" + measureTotal
                + ", pollutionCount=" + pollutionCount
                + ", improvementCount=" + improvementCount
                + ", gridTotal=" + gridTotal
                + ", gridCovered=" + gridCovered
                + ", gridCoverage=" + gridCoverage
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
