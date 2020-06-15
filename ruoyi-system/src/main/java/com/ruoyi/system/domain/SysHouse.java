package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼房基础信息对象 sys_house
 *
 * @author ruoyi
 * @date 2020-05-09
 */
public class SysHouse extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** id */
    private Long houseId;

    /** 所在楼盘id */
    private String buldingId;

    /** 所在楼盘名称，导入专用 */
    @Excel(name = "所在楼盘",combo = "yes")
    private String buldingName;

    /**所在单元id*/
    private String houseUtilId;

    /**所在单元，导入专用*/
    @Excel(name = "所在单元",prompt = "数据来自楼盘单元基础数据，不可随意填写,必填")
    private String houseUtilName;

    /** 所在楼盘 */
    private SysBuilding bulding;

    /**所在单元*/
    private SysHouseUnit houseUnit;

    /**建筑面积*/
    @Excel(name = "建筑面积",prompt = "只可输入整数和小数,必填")
    private Double area;


    /**套内面积*/
    @Excel(name = "套内面积",prompt = "只可输入整数和小数,必填")
    private Double innerArea;

    /**实测面积*/
    @Excel(name = "实测面积",prompt = "只可输入整数和小数,必填")
    private Double realArea;

    /**
     * 房间号
     */
    @Excel(name = "房间号")
    private String houseNumber;

    /** 用户是否存在此标识 默认不存在 */
    private boolean flag = false;


    /**
     * 类型
     */
    @Excel(name = "类型")
    private String buildingType;

    /**
     * 楼层
     */
    @Excel(name = "楼层")
    private String floor;

    /**
     * 单价
     */
    @Excel(name = "单价")
    private Double price;

    /**
     * 产权
     */
    private String propertyRights;






    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getFloor() {
        return floor;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPropertyRights(String propertyRights) {
        this.propertyRights = propertyRights;
    }

    public String getPropertyRights() {
        return propertyRights;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("houseId", getHouseId())
                .append("buldingId", getBuldingId())
                .append("houseUtilId", getHouseUtilId())
                .append("bulding",getBulding())
                .append("area",getArea())
                .append("houseUnit", getHouseUnit())
                .append("houseNumber", getHouseNumber())
                .append("buildingType", getBuildingType())
                .append("floor", getFloor())
                .append("price", getPrice())
                .append("propertyRights", getPropertyRights())
                .append("innerArea", getInnerArea())
                .append("realArea", getRealArea())
                .toString();
    }



    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public SysBuilding getBulding() {
        return bulding;
    }

    public void setBulding(SysBuilding bulding) {
        if(bulding == null){
            bulding = new SysBuilding();
        }
        this.bulding = bulding;
    }

    public SysHouseUnit getHouseUnit() {
        if(houseUnit == null){
            houseUnit = new SysHouseUnit();
        }
        return houseUnit;
    }

    public void setHouseUnit(SysHouseUnit houseUnit) {
        this.houseUnit = houseUnit;
    }

    public String getBuldingId() {
        return buldingId;
    }

    public void setBuldingId(String buldingId) {
        this.buldingId = buldingId;
    }

    public String getHouseUtilId() {
        return houseUtilId;
    }

    public void setHouseUtilId(String houseUtilId) {
        this.houseUtilId = houseUtilId;
    }


    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }


    public Double getRealArea() {
        return realArea;
    }

    public void setRealArea(Double realArea) {
        this.realArea = realArea;
    }

    public Double getInnerArea() {
        return innerArea;
    }

    public void setInnerArea(Double innerArea) {
        this.innerArea = innerArea;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setBuldingName(String buldingName) {
        this.buldingName = buldingName;
    }
    public String getBuldingName() {
        return buldingName;
    }

    public String getHouseUtilName() {
        return houseUtilName;
    }

    public void setHouseUtilName(String houseUtilName) {
        this.houseUtilName = houseUtilName;
    }
}
