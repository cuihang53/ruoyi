package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 提成明细对象 sys_commission
 *
 * @author ruoyi
 * @date 2020-05-17
 */
public class SysCommission extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long commissionId;

    /**
     * 关联销售单
     */
    @Excel(name = "关联销售单")
    private Long salesDetailId;

    /**
     * 关联房号
     */
    @Excel(name = "关联房号")
    private Long houseid;

    /**
     * 岗位
     */
    @Excel(name = "岗位")
    private String postId;

    /**
     * 岗位
     */
    private SysPost post;

    /**
     * 提点
     */
    @Excel(name = "提点")
    private String remind;

    /**
     * 对应人员
     */
    @Excel(name = "对应人员")
    private String userId;
    /**
     * 对应人员
     */
    private SysUser user;


    /**
     * 完成任务提成发放比例
     */
    private String completeProp;

    /**
     * 回款发放比例
     */
    @Excel(name = "回款发放比例")
    private String dibProportion;

    /**
     * 回款提成发放时间
     */
    private String dibTime;
    /**
     * 交房发放比例
     */
    @Excel(name = "交房发放比例")
    private String roomProportion;
    /**
     * 交房提成发放时间
     */
    private String roomPrpTime;


    public void setSalesDetailId(Long salesDetailId) {
        this.salesDetailId = salesDetailId;
    }

    public Long getSalesDetailId() {
        return salesDetailId;
    }

    public void setHouseid(Long houseid) {
        this.houseid = houseid;
    }

    public Long getHouseid() {
        return houseid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getCommissionId())
                .append("salesDetailId", getSalesDetailId())
                .append("houseid", getHouseid())
                .append("postId", getPostId())
                .append("remind", getRemind())
                .append("userId", getUserId())
                .append("dibProportion", getDibProportion())
                .append("roomProportion", getRoomProportion())
                .append("remark", getRemark())
                .append("post", getPost())
                .append("user", getUser())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("completeProp", getCompleteProp())
                .append("roomPrpTime", getRoomPrpTime())
                .append("dibTime", getDibTime())
                .toString();
    }


    public SysUser getUser() {
        if (user == null) {
            user = new SysUser();
        }
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public String getRemind() {
        return remind;
    }

    public void setRemind(String remind) {
        this.remind = remind;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDibProportion() {
        return dibProportion;
    }

    public void setDibProportion(String dibProportion) {
        this.dibProportion = dibProportion;
    }

    public String getRoomProportion() {
        return roomProportion;
    }

    public void setRoomProportion(String roomProportion) {
        this.roomProportion = roomProportion;
    }


    public Long getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(Long commissionId) {
        this.commissionId = commissionId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public SysPost getPost() {
        return post;
    }

    public void setPost(SysPost post) {
        this.post = post;
    }

    public String getCompleteProp() {
        return completeProp;
    }

    public void setCompleteProp(String completeProp) {
        this.completeProp = completeProp;
    }

    public String getDibTime() {
        return dibTime;
    }

    public void setDibTime(String dibTime) {
        this.dibTime = dibTime;
    }

    public String getRoomPrpTime() {
        return roomPrpTime;
    }

    public void setRoomPrpTime(String roomPrpTime) {
        this.roomPrpTime = roomPrpTime;
    }
}
