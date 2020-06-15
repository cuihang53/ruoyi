package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 流程流转信息对象 sys_flow
 *
 * @author ruoyi
 * @date 2020-06-09
 */
public class SysFlow extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * id
     */
    private Long id;

    /**
     * 申请人
     */
    @Excel(name = "申请人")
    private Long applyuser;

    /**
     * 申请人
     */
    private SysUser user;



    /**
     * 当前处理人
     */
    @Excel(name = "当前处理人")
    private Long handleruser;

    /**
     * 状态：0暂存未开始,1已审核,2未审核,3,本申请已经结束,
     */
    @Excel(name = "状态：0暂存未开始,1,流程中，2流程结束")
    private int status;

    /**
     * 类别
     */
    @Excel(name = "类别")
    private String type;

    /**
     * 关联业务单ID
     */
    private Long typeId;


    /**
     * 操作標記，1.提交，0.保存
     */
    private int optionStatus;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setApplyuser(Long applyuser) {
        this.applyuser = applyuser;
    }

    public Long getApplyuser() {
        return applyuser;
    }

    public void setHandleruser(Long handleruser) {
        this.handleruser = handleruser;
    }

    public Long getHandleruser() {
        return handleruser;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("applyuser", getApplyuser())
                .append("handleruser", getHandleruser())
                .append("status", getStatus())
                .append("type", getType())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("typeId", getTypeId())
                .toString();
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public int getOptionStatus() {
        return optionStatus;
    }

    public void setOptionStatus(int optionStatus) {
        this.optionStatus = optionStatus;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }
}
