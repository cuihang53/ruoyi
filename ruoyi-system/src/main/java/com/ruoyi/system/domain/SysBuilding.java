package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.File;

/**
 * 楼盘基础信息对象 sys_building
 *
 * @author ruoyi
 * @date 2020-05-09
 */
public class SysBuilding extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 楼盘id
     */
    private Long buildingId;

    /**
     * 楼盘编号
     */
    private String buildingCode;

    /**
     * 楼盘名称
     */
    @Excel(name = "楼盘名称")
    private String buildingName;

    /**
     * 楼盘类型
     */
    @Excel(name = "楼盘类型")
    private String buildingType;

    /**
     * 附件
     */
    private File buldingFile;

    /**
     * 用户是否存在此标识 默认不存在
     */
    private boolean flag = false;

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingCode(String buildingCode) {
        this.buildingCode = buildingCode;
    }

    public String getBuildingCode() {
        return buildingCode;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getBuildingType() {
        return buildingType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("buildingId", getBuildingId())
                .append("buildingCode", getBuildingCode())
                .append("buildingName", getBuildingName())
                .append("buildingType", getBuildingType())
                .append("buldingFile", getBuldingFile())
                .toString();
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public File getBuldingFile() {
        return buldingFile;
    }

    public void setBuldingFile(File buldingFile) {
        this.buldingFile = buldingFile;
    }
}
