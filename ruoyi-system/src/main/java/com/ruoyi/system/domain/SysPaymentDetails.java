package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 收款明细对象 sys_payment_details
 *
 * @author ruoyi
 * @date 2020-05-23
 */
public class SysPaymentDetails extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long paymentId;


    /**
     * 关联销售单
     **/
    private String salesDetailId;

    /**
     * 收款类型
     */
    @Excel(name = "收款类型")
    private String paymentType;

    /**
     * 收款金额
     */
    @Excel(name = "收款金额")
    private Long amount;

    /**
     * 收款时间
     */
    @Excel(name = "收款时间", width = 30, dateFormat = "yyyy-MM-dd")
    private String paymentTime;


    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("paymentId", getPaymentId())
                .append("paymentType", getPaymentType())
                .append("amount", getAmount())
                .append("paymentTime", getPaymentTime())
                .append("remark", getRemark())
                .append("salesDetailId", getSalesDetailId())
                .toString();
    }

    public String getSalesDetailId() {
        return salesDetailId;
    }

    public void setSalesDetailId(String salesDetailId) {
        this.salesDetailId = salesDetailId;
    }
}
