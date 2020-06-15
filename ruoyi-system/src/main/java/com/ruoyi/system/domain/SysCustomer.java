package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 客户信息对象 sys_customer
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public class SysCustomer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long customerId;

    /** 客户姓名 */
    @Excel(name = "客户姓名")
    private String cName;

    /** 客户性别 */
    @Excel(name = "客户性别")
    private String cSex;

    /** 客户电话 */
    @Excel(name = "客户电话")
    private String cPhone;

    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setcName(String cName) 
    {
        this.cName = cName;
    }

    public String getcName() 
    {
        return cName;
    }
    public void setcSex(String cSex) 
    {
        this.cSex = cSex;
    }

    public String getcSex() 
    {
        return cSex;
    }
    public void setcPhone(String cPhone) 
    {
        this.cPhone = cPhone;
    }

    public String getcPhone() 
    {
        return cPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("customerId", getCustomerId())
            .append("cName", getcName())
            .append("cSex", getcSex())
            .append("cPhone", getcPhone())
            .toString();
    }
}
