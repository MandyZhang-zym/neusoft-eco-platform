package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 操作日志表
 * <p>对应数据库表 {@code oper_log}</p>
 */
@TableName("oper_log")
public class OperLog {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 操作人 user_id */
    private Long userId;

    /** 操作人用户名（冗余，便于直接展示） */
    private String username;

    /** 所属模块，如 反馈工单/指派/核查 */
    private String module;

    /** 操作描述，如「指派工单」 */
    private String operation;

    /** 请求接口 */
    private String method;

    /** 请求参数 */
    private String requestParam;

    /** 关联工单 id（按工单追溯用） */
    private Long orderId;

    /** 操作 IP */
    private String ip;

    /** 结果：1=成功 0=失败 */
    private Integer status;

    /** 失败原因 */
    private String errorMsg;

    /** 操作时间 */
    private LocalDateTime operTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public LocalDateTime getOperTime() {
        return operTime;
    }

    public void setOperTime(LocalDateTime operTime) {
        this.operTime = operTime;
    }

    @Override
    public String toString() {
        return "OperLog{"
                + "id=" + id
                + ", userId=" + userId
                + ", username='" + username + '\''
                + ", module='" + module + '\''
                + ", operation='" + operation + '\''
                + ", method='" + method + '\''
                + ", requestParam='" + requestParam + '\''
                + ", orderId=" + orderId
                + ", ip='" + ip + '\''
                + ", status=" + status
                + ", errorMsg='" + errorMsg + '\''
                + ", operTime=" + operTime
                + '}';
    }
}
