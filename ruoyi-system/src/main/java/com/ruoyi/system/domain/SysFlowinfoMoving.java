package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 流程流转处理意见记录对象 sys_flowinfo_moving
 *
 * @author ruoyi
 * @date 2020-06-09
 */
public class SysFlowinfoMoving extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 关联流程id
     */
    @Excel(name = "关联流程id")
    private Long flowId;

    /**
     * 关联流程单实体
     */
    private  SysFlow flow;

    /**
     * 当前处理人
     */
    private Long dealUser;


    /**
     * 当前处理人
     */
    private SysUser user;


    /**
     * 处理时间
     */
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date dealtime;

    /**
     * 处理意见，1，同意，2驳回
     */
    @Excel(name = "处理意见，1，同意，2驳回 3,新建")
    private String opinion;


    /**
     * 处理状态，0.已处理，1.未处理
     */
    private int status=1;

    /**
     * 处理事项标题，待办标题
     */
    private String title;

    /**
     * 处理链接
     */
    private String dealLink;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setFlowId(Long flowId) {
        this.flowId = flowId;
    }

    public Long getFlowId() {
        return flowId;
    }



    public void setDealtime(Date dealtime) {
        this.dealtime = dealtime;
    }

    public Date getDealtime() {
        return dealtime;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getOpinion() {
        return opinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("flowId", getFlowId())
                .append("dealUser", getDealUser())
                .append("dealtime", getDealtime())
                .append("opinion", getOpinion())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("dealLink", getDealLink())
                .append("status", getStatus())
                .append("title", getTitle())
                .toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDealLink() {
        return dealLink;
    }

    public void setDealLink(String dealLink) {
        this.dealLink = dealLink;
    }

    public Long getDealUser() {
        return dealUser;
    }

    public void setDealUser(Long dealUser) {
        this.dealUser = dealUser;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysFlow getFlow() {
        return flow;
    }

    public void setFlow(SysFlow flow) {
        this.flow = flow;
    }
}
