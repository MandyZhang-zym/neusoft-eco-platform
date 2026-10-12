package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 省表
 * <p>对应数据库表 {@code sys_province}</p>
 */
@TableName("sys_province")
public class SysProvince {

    /** 主键 */
    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    /** 省级行政区划代码 */
    private String provinceCode;

    /** 省/自治区/直辖市名称（大屏地图按此名称注册） */
    private String provinceName;

    /** 简称 */
    private String shortName;

    /** 中心经度（如需地图打点，可后续导入） */
    private BigDecimal longitude;

    /** 中心纬度（如需地图打点，可后续导入） */
    private BigDecimal latitude;

    /** 排序 */
    private Integer sortNo;

    /** 创建时间 */
    private Timestamp createTime;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SysProvince{"
                + "pid=" + pid
                + ", provinceCode='" + provinceCode + '\''
                + ", provinceName='" + provinceName + '\''
                + ", shortName='" + shortName + '\''
                + ", longitude=" + longitude
                + ", latitude=" + latitude
                + ", sortNo=" + sortNo
                + ", createTime=" + createTime
                + '}';
    }
}
