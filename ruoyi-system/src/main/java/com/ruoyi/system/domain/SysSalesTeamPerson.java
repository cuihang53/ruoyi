package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 销售小组成员信息对象 sys_sales_teamperson
 *
 * @author ruoyi
 * @date 2020-06-02
 */
public class SysSalesTeamPerson extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long salesId;

    /**
     * 关联小组
     */
    @Excel(name = "关联小组")
    private Long salesTeamId;

    private SysSalesTeam team;

    /**
     * 人员id
     */
    private Long userId;


    /**
     * 员工号
     */
    private String employeeNumber;

    /**
     * 姓名
     */
    private String name;

    /**
     * 联系方式
     */
    private String phoneNumber;

    /**
     * 职位
     */
    private String position;

    private SysUser user;

    /**
     * 进入时间
     */
    @Excel(name = "进入时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date entryTime;

    /**
     * 退出时间
     */
    @Excel(name = "退出时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesTeamId(Long salesTeamId) {
        this.salesTeamId = salesTeamId;
    }

    public Long getSalesTeamId() {
        return salesTeamId;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("salesId", getSalesId())
                .append("salesTeamId", getSalesTeamId())
                .append("employeeNumber", getEmployeeNumber())
                .append("name", getName())
                .append("phoneNumber", getPhoneNumber())
                .append("position", getPosition())
                .append("entryTime", getEntryTime())
                .append("endTime", getEndTime())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("userId", getUserId())
                .toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public SysSalesTeam getTeam() {
        return team;
    }

    public void setTeam(SysSalesTeam team) {
        this.team = team;
    }
}
