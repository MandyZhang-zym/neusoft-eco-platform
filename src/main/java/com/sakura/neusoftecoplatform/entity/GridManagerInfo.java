package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 网格员信息表
 * <p>对应数据库表 {@code grid_manager_info}</p>
 */
@TableName("grid_manager_info")
public class GridManagerInfo {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 账号 id（sys_user.id） */
    private Long userId;

    /** 负责的网格 id（sys_grid.id，可为空表示多网格） */
    private Long gridId;

    /** 姓名 */
    private String realName;

    /** 性别：1=男 2=女 */
    private Integer gender;

    /** 工号 */
    private String workNo;

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

    public Long getGridId() {
        return gridId;
    }

    public void setGridId(Long gridId) {
        this.gridId = gridId;
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

    public String getWorkNo() {
        return workNo;
    }

    public void setWorkNo(String workNo) {
        this.workNo = workNo;
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
        return "GridManagerInfo{"
                + "id=" + id
                + ", userId=" + userId
                + ", gridId=" + gridId
                + ", realName='" + realName + '\''
                + ", gender=" + gender
                + ", workNo='" + workNo + '\''
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
