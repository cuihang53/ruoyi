package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 附件对象 sys_file_util
 *
 * @author ruoyi
 * @date 2020-05-23
 */
public class SysFileUtil extends BaseEntity {
    private static final long serialVersionUID = 1L;

    //銷售明细code
    public static String OEDERENTITY = "OEDERENTITY";


    /**
     * id
     */
    private Long fileUtilId;

    /**
     * 文件名稱
     */
    private String fileName;

    /**
     * 每个实体对应的唯一标识
     */
    @Excel(name = "每个实体对应的唯一标识")
    private String fileUtilCode;

    /**
     * 附件表ID
     */
    @Excel(name = "附件表ID")
    private Long fileAttachmentId;

    /**
     * 实体表ID
     */
    @Excel(name = "实体表ID")
    private Long fileDomainId;

    private SysAttachment attachment;

    public void setFileUtilId(Long fileUtilId) {
        this.fileUtilId = fileUtilId;
    }

    public Long getFileUtilId() {
        return fileUtilId;
    }

    public void setFileUtilCode(String fileUtilCode) {
        this.fileUtilCode = fileUtilCode;
    }

    public String getFileUtilCode() {
        return fileUtilCode;
    }

    public void setFileAttachmentId(Long fileAttachmentId) {
        this.fileAttachmentId = fileAttachmentId;
    }

    public Long getFileAttachmentId() {
        return fileAttachmentId;
    }

    public void setFileDomainId(Long fileDomainId) {
        this.fileDomainId = fileDomainId;
    }

    public Long getFileDomainId() {
        return fileDomainId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("fileUtilId", getFileUtilId())
                .append("fileUtilCode", getFileUtilCode())
                .append("fileAttachmentId", getFileAttachmentId())
                .append("fileDomainId", getFileDomainId())
                .toString();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public SysAttachment getAttachment() {
        if (attachment == null) {
            attachment = new SysAttachment();
        }
        return attachment;
    }

    public void setAttachment(SysAttachment attachment) {
        this.attachment = attachment;
    }
}
