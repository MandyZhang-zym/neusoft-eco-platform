package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;

/**
 * 区/县表
 * <p>对应数据库表 {@code sys_district}</p>
 */
@TableName("sys_district")
public class SysDistrict {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 所属市 id（sys_city.id） */
    private Long cityId;

    /** 区/县行政区划代码 */
    private String districtCode;

    /** 区/县名称 */
    private String districtName;

    /** 排序 */
    private Integer sortNo;

    /** 创建时间 */
    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysDistrict{"
                + "id=" + id
                + ", cityId=" + cityId
                + ", districtCode='" + districtCode + '\''
                + ", districtName='" + districtName + '\''
                + ", sortNo=" + sortNo
                + ", createTime=" + createTime
                + '}';
    }
}
