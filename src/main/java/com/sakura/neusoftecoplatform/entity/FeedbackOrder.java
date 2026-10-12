package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 公众反馈工单表
 * <p>对应数据库表 {@code feedback_order}</p>
 */
@TableName("feedback_order")
public class FeedbackOrder {

    /** 主键 */
    @TableId(value = "oid", type = IdType.AUTO)
    private Long oid;

    /** 工单编号 */
    private String orderNo;

    /** 上报人 user_id（公众监督员） */
    private Long reporterId;

    /** 网格 id（sys_grid.id） */
    private Long gridId;

    /** 冗余-省 id（管理端按省市筛选） */
    private Long provinceId;

    /** 冗余-市 id */
    private Long cityId;

    /** 公众预估 AQI 等级 id（aqi_level.id） */
    private Long estimatedLevelId;

    /** 反馈信息 */
    private String feedbackContent;

    /** 反馈时间（公众填写/提交时间） */
    private LocalDateTime feedbackTime;

    /** 工单状态：PENDING_ASSIGN=待指派 ASSIGNED=已指派 CHECKED=已核查 */
    private String status;

    /** 当前负责网格员 user_id */
    private Long assigneeId;

    /** 最近一次指派时间 */
    private Timestamp assignTime;

    /** 核查完成时间 */
    private Timestamp checkTime;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getReporterId() {
        return reporterId;
    }

    public void setReporterId(Long reporterId) {
        this.reporterId = reporterId;
    }

    public Long getGridId() {
        return gridId;
    }

    public void setGridId(Long gridId) {
        this.gridId = gridId;
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

    public Long getEstimatedLevelId() {
        return estimatedLevelId;
    }

    public void setEstimatedLevelId(Long estimatedLevelId) {
        this.estimatedLevelId = estimatedLevelId;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public LocalDateTime getFeedbackTime() {
        return feedbackTime;
    }

    public void setFeedbackTime(LocalDateTime feedbackTime) {
        this.feedbackTime = feedbackTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAssigneeId() {
        return assigneeId;
    }

    public void setAssigneeId(Long assigneeId) {
        this.assigneeId = assigneeId;
    }

    public Timestamp getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Timestamp assignTime) {
        this.assignTime = assignTime;
    }

    public Timestamp getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Timestamp checkTime) {
        this.checkTime = checkTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "FeedbackOrder{"
                + "oid=" + oid
                + ", orderNo='" + orderNo + '\''
                + ", reporterId=" + reporterId
                + ", gridId=" + gridId
                + ", provinceId=" + provinceId
                + ", cityId=" + cityId
                + ", estimatedLevelId=" + estimatedLevelId
                + ", feedbackContent='" + feedbackContent + '\''
                + ", feedbackTime=" + feedbackTime
                + ", status='" + status + '\''
                + ", assigneeId=" + assigneeId
                + ", assignTime=" + assignTime
                + ", checkTime=" + checkTime
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
