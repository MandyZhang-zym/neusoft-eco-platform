package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 网格表
 * <p>对应数据库表 {@code sys_grid}</p>
 */
@TableName("sys_grid")
public class SysGrid {

    /** 主键 */
    @TableId(value = "gid", type = IdType.AUTO)
    private Long gid;

    /** 网格编号 */
    private String gridCode;

    /** 网格名称 */
    private String gridName;

    /** 所属省 id */
    private Long provinceId;

    /** 所属市 id */
    private Long cityId;

    /** 所属区/县 id */
    private Long districtId;

    /** 详细地址 */
    private String address;

    /** 经度（示例值） */
    private BigDecimal longitude;

    /** 纬度（示例值） */
    private BigDecimal latitude;

    /** 状态：1=启用 0=停用 */
    private Integer status;

    /** 创建时间 */
    private Timestamp createTime;

    /** 更新时间 */
    private Timestamp updateTime;

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getGridCode() {
        return gridCode;
    }

    public void setGridCode(String gridCode) {
        this.gridCode = gridCode;
    }

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
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

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "SysGrid{"
                + "gid=" + gid
                + ", gridCode='" + gridCode + '\''
                + ", gridName='" + gridName + '\''
                + ", provinceId=" + provinceId
                + ", cityId=" + cityId
                + ", districtId=" + districtId
                + ", address='" + address + '\''
                + ", longitude=" + longitude
                + ", latitude=" + latitude
                + ", status=" + status
                + ", createTime=" + createTime
                + ", updateTime=" + updateTime
                + '}';
    }
}
