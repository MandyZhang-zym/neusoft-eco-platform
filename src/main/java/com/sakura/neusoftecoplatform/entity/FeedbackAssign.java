package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 工单指派记录表
 * <p>对应数据库表 {@code feedback_assign}</p>
 */
@TableName("feedback_assign")
public class FeedbackAssign {

    /** 主键 */
    @TableId(value = "aid", type = IdType.AUTO)
    private Long aid;

    /** 工单 id（feedback_order.oid） */
    private Long orderId;

    /** 目标网格员 user_id */
    private Long gridManagerId;

    /** 操作人 user_id（系统管理员） */
    private Long assignerId;

    /** 指派时间 */
    private Timestamp assignTime;

    /** 状态：EFFECTIVE=生效 REVOKED=已撤销 */
    private String status;

    /** 备注 */
    private String remark;

    /** 创建时间 */
    private LocalDateTime createTime;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGridManagerId() {
        return gridManagerId;
    }

    public void setGridManagerId(Long gridManagerId) {
        this.gridManagerId = gridManagerId;
    }

    public Long getAssignerId() {
        return assignerId;
    }

    public void setAssignerId(Long assignerId) {
        this.assignerId = assignerId;
    }

    public Timestamp getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Timestamp assignTime) {
        this.assignTime = assignTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "FeedbackAssign{"
                + "aid=" + aid
                + ", orderId=" + orderId
                + ", gridManagerId=" + gridManagerId
                + ", assignerId=" + assignerId
                + ", assignTime=" + assignTime
                + ", status='" + status + '\''
                + ", remark='" + remark + '\''
                + ", createTime=" + createTime
                + '}';
    }
}
