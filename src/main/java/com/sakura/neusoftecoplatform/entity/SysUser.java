package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 账号信息表
 * <p>对应数据库表 {@code sys_user}</p>
 */
@TableName("sys_user")
public class SysUser {

    /** 主键 */
    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /** 用户名（登录账号） */
    private String username;

    /** 密码（建议存 BCrypt 哈希，勿存明文） */
    private String password;

    /** 手机号（亦可作为登录账号） */
    private String phone;

    /** 角色 id（sys_role.rid） */
    private Long roleId;

    /** 状态：1=正常 0=禁用 */
    private Integer status;

    /** 最近登录时间 */
    private LocalDateTime lastLoginTime;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
        return "SysUser{"
                + "uid=" + uid
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + ", phone='" + phone + '\''
                + ", roleId=" + roleId
                + ", status=" + status
                + ", lastLoginTime=" + lastLoginTime
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
