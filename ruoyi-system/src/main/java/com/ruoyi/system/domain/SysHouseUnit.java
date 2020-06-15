package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 楼盘单元楼号中间对象 sys_house_unit
 *
 * @author ruoyi
 * @date 2020-05-16
 */
public class SysHouseUnit extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long houseUnitId;

    /**
     * 所在楼盘ID
     */
    private String buildingId;

    /**导入专用**/
    @Excel(name = "所在楼盘",combo = "yes")
    private String buildingName;

    /** 用户是否存在此标识 默认不存在 */
    private boolean flag = false;

    /**
     * 单元名称
     */
    @Excel(name = "单元名称")
    private String houseUnitName;

    private String unitCode;

    /**
     * 所在楼盘
     */
    private SysBuilding bulding;



    public void setHouseUnitName(String houseUnitName) {
        this.houseUnitName = houseUnitName;
    }

    public String getHouseUnitName() {
        return houseUnitName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("houseUnitId", getHouseUnitId())
                .append("updateBy", getUpdateBy())
                .append("createBy", getCreateBy())
                .append("buildingId", getBuildingId())
                .append("houseUnitName", getHouseUnitName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("unitCode", getUnitCode())
                .toString();
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public Long getHouseUnitId() {
        return houseUnitId;
    }

    public void setHouseUnitId(Long houseUnitId) {
        this.houseUnitId = houseUnitId;
    }


    public SysBuilding getBulding() {
        if(bulding==null){
            bulding = new SysBuilding();
        }
        return bulding;
    }

    public void setBulding(SysBuilding bulding) {
        this.bulding = bulding;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getBuildingName() {
       return buildingName;
    }
}
