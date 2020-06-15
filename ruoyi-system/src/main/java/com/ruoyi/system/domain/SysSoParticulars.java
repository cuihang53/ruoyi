package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 销售单当前状态明细对象 sys_so_particulars
 *
 * @author ruoyi
 * @date 2020-05-30
 */
public class SysSoParticulars extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long sysSoParticularsId;

    /**
     * 关联销售单
     */
    private String salesDetailId;


    /**
     * 完成时间
     */
    private String completionTime;

    /**
     * 当前状态
     */
    @Excel(name = "当前状态")
    private String orderstatus;

    public void setSysSoParticularsId(Long sysSoParticularsId) {
        this.sysSoParticularsId = sysSoParticularsId;
    }

    public Long getSysSoParticularsId() {
        return sysSoParticularsId;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("sysSoParticularsId", getSysSoParticularsId())
                .append("orderstatus", getOrderstatus())
                .append("remark", getRemark())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("salesDetailId", getSalesDetailId())
                .append("completionTime", getCompletionTime())
                .toString();
    }

    public String getSalesDetailId() {
        return salesDetailId;
    }

    public void setSalesDetailId(String salesDetailId) {
        this.salesDetailId = salesDetailId;
    }

    public String getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(String completionTime) {
        this.completionTime = completionTime;
    }
}
