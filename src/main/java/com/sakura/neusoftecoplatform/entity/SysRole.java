package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 角色表
 * <p>对应数据库表 {@code sys_role}</p>
 */
@TableName("sys_role")
public class SysRole {

    /** 主键 */
    @TableId(value = "rid", type = IdType.AUTO)
    private Long rid;

    /** 角色标识（英文 code） */
    private String roleCode;

    /** 角色名称 */
    private String roleName;

    /** 登录后跳转的首页路由（前端根据角色跳转） */
    private String homePage;

    /** 角色说明 */
    private String description;

    /** 排序 */
    private Integer sortNo;

    /** 创建时间 */
    private Timestamp createTime;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysRole{"
                + "rid=" + rid
                + ", roleCode='" + roleCode + '\''
                + ", roleName='" + roleName + '\''
                + ", homePage='" + homePage + '\''
                + ", description='" + description + '\''
                + ", sortNo=" + sortNo
                + ", createTime=" + createTime
                + '}';
    }
}
