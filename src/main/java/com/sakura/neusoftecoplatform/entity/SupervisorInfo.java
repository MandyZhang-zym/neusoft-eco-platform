package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 公众监督员信息表
 * <p>对应数据库表 {@code supervisor_info}</p>
 */
@TableName("supervisor_info")
public class SupervisorInfo {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 账号 id（sys_user.id） */
    private Long userId;

    /** 姓名 */
    private String realName;

    /** 性别：1=男 2=女 */
    private Integer gender;

    /** 年龄 */
    private Integer age;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
        return "SupervisorInfo{"
                + "id=" + id
                + ", userId=" + userId
                + ", realName='" + realName + '\''
                + ", gender=" + gender
                + ", age=" + age
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
