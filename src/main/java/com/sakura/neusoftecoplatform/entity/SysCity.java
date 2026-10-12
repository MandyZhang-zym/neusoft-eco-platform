package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 市表
 * <p>对应数据库表 {@code sys_city}</p>
 */
@TableName("sys_city")
public class SysCity {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 所属省 id（sys_province.id） */
    private Long provinceId;

    /** 市级行政区划代码 */
    private String cityCode;

    /** 城市名称 */
    private String cityName;

    /** 中心经度 */
    private BigDecimal longitude;

    /** 中心纬度 */
    private BigDecimal latitude;

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

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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
        return "SysCity{"
                + "id=" + id
                + ", provinceId=" + provinceId
                + ", cityCode='" + cityCode + '\''
                + ", cityName='" + cityName + '\''
                + ", longitude=" + longitude
                + ", latitude=" + latitude
                + ", sortNo=" + sortNo
                + ", createTime=" + createTime
                + '}';
    }
}
