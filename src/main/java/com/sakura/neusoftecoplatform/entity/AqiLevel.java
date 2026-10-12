package com.sakura.neusoftecoplatform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 空气质量指数等级分类表
 * <p>对应数据库表 {@code aqi_level}</p>
 */
@TableName("aqi_level")
public class AqiLevel {

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 等级序号 1~6 */
    private Integer levelNo;

    /** 等级名称 */
    private String levelName;

    /** AQI 下限 */
    private Integer aqiMin;

    /** AQI 上限（严重污染按 500 计） */
    private Integer aqiMax;

    /** 是否计入"超标"：1=是 0=否 */
    private Integer isExceed;

    /** 大屏配色（十六进制） */
    private String color;

    /** 健康影响说明 */
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(Integer levelNo) {
        this.levelNo = levelNo;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getAqiMin() {
        return aqiMin;
    }

    public void setAqiMin(Integer aqiMin) {
        this.aqiMin = aqiMin;
    }

    public Integer getAqiMax() {
        return aqiMax;
    }

    public void setAqiMax(Integer aqiMax) {
        this.aqiMax = aqiMax;
    }

    public Integer getIsExceed() {
        return isExceed;
    }

    public void setIsExceed(Integer isExceed) {
        this.isExceed = isExceed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AqiLevel{"
                + "id=" + id
                + ", levelNo=" + levelNo
                + ", levelName='" + levelName + '\''
                + ", aqiMin=" + aqiMin
                + ", aqiMax=" + aqiMax
                + ", isExceed=" + isExceed
                + ", color='" + color + '\''
                + ", description='" + description + '\''
                + '}';
    }
}
