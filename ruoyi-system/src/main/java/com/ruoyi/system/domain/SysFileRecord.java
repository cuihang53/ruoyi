package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 资料领取记录对象 sys_file_record
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
public class SysFileRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public  final static String saleFileRecord = "SALEFILERECORD";

    /** id */
    private Long fileRecordId;

    /** 关联表类型 */
    @Excel(name = "关联表类型")
    private String relevancyType;

    /** 关联外键id */
    @Excel(name = "关联外键id")
    private Long saleOrderId;

    /** 领取人 */
    @Excel(name = "领取人")
    private String recordPerson;

    /** 领取时间 */
    @Excel(name = "领取时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String recordTime;

    /** 领取文件名称 */
    @Excel(name = "领取文件名称")
    private String recordFileName;

    /** 附件 */
    @Excel(name = "附件")
    private String unitFile;

    public void setFileRecordId(Long fileRecordId) 
    {
        this.fileRecordId = fileRecordId;
    }

    public Long getFileRecordId() 
    {
        return fileRecordId;
    }
    public void setRelevancyType(String relevancyType) 
    {
        this.relevancyType = relevancyType;
    }

    public String getRelevancyType() 
    {
        return relevancyType;
    }
    public void setSaleOrderId(Long saleOrderId) 
    {
        this.saleOrderId = saleOrderId;
    }

    public Long getSaleOrderId() 
    {
        return saleOrderId;
    }
    public void setRecordPerson(String recordPerson) 
    {
        this.recordPerson = recordPerson;
    }

    public String getRecordPerson() 
    {
        return recordPerson;
    }
    public void setRecordTime(String recordTime)
    {
        this.recordTime = recordTime;
    }

    public String getRecordTime()
    {
        return recordTime;
    }
    public void setRecordFileName(String recordFileName) 
    {
        this.recordFileName = recordFileName;
    }

    public String getRecordFileName() 
    {
        return recordFileName;
    }
    public void setUnitFile(String unitFile) 
    {
        this.unitFile = unitFile;
    }

    public String getUnitFile() 
    {
        return unitFile;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("fileRecordId", getFileRecordId())
            .append("relevancyType", getRelevancyType())
            .append("saleOrderId", getSaleOrderId())
            .append("recordPerson", getRecordPerson())
            .append("recordTime", getRecordTime())
            .append("recordFileName", getRecordFileName())
            .append("remark", getRemark())
            .append("unitFile", getUnitFile())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
