package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 销售信息录入对象 sys_sales_detail
 *
 * @author ruoyi
 * @date 2020-05-16
 */
public class SysSalesOrder extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long salesDetailId;

    /**
     * 关联楼盘
     */
    @Excel(name = "关联楼盘")
    private Long buldingid;

    /**
     * 关联楼盘
     */
    private SysBuilding bulding;


    /**
     * 关联房号
     */
    @Excel(name = "关联房号")
    private Long houseid;

    /**
     * 关联楼盘
     */
    private SysHouse house;

    /**
     * 关联单元
     */
    private SysHouseUnit houseUnit;


    /**
     * 销售人员
     */
    private String salePerson;


    /**
     * 销售单状态
     */
    private String orderStatus;

    /**
     * 签约人联系方式
     */
    private String finalBuyerPhone;

    /**
     * 已收首付金额
     */
    private String rdInstallmentPrice;
    /**
     * 剩余首付金额
     */
    private String rmiInstallmentPrice;

    /**
     * 房款结清时间
     */
    private Date priceEndTime;

    /**
     * 销售底价
     */
    @Excel(name = "销售底价")
    private String salseFloorPrice;

    /**
     * 认购人
     */
    @Excel(name = "认购人")
    private String bookBuyer;

    /**
     * 认购时间
     */
    @Excel(name = "认购时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date buyertime;

    /**
     * 认购单价
     */
    @Excel(name = "认购单价")
    private String bookBuyerPrice;

    /**
     * 认购金金额
     */
    @Excel(name = "认购金金额")
    private String buyerPrice;

    /**
     * 认购人联系方式
     */
    @Excel(name = "认购人联系方式")
    private String bookBuyerPhone;


    /**
     * 认购总价
     */
    @Excel(name = "认购总价")
    private String bookBuyerAllprice;

    /**
     * 返租金额
     */
    @Excel(name = "返租金额")
    private String rantPrice;

    /**
     * 首付分期金额
     */
    @Excel(name = "首付分期金额")
    private String installmentPrice;

    /**
     * 按揭金额
     */
    @Excel(name = "按揭金额")
    private String mortgagePrice;




    /**
     * 签合同时间
     */
    @Excel(name = "签合同时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date contracttime;

    /**
     * 购房人
     */
    @Excel(name = "购房人")
    private String finalBuyer;

    /**
     * 合同单价
     */
    @Excel(name = "合同单价")
    private String contractPrice;

    /**
     * 合同总价
     */
    @Excel(name = "合同总价")
    private String contractAllprice;

    /**
     * 付款方式
     */
    @Excel(name = "付款方式")
    private String payMethod;

    /**
     * 底价验证
     */
    @Excel(name = "底价验证")
    private String floorPriceSure;

    /**
     * 已收款金额
     */
    @Excel(name = "已收款金额")
    private String collection;
    /**
     * 剩余金额
     */
    @Excel(name = "剩余金额")
    private String surplusPrice;

    /**
     * 提成发放时间
     */
    @Excel(name = "提成发放时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date commissionTime;


    /**
     * 提成明细
     **/
    private List<SysCommission> commissionList;

    /**
     * 新增提成明细使用
     **/
    private String cids;


    /**
     * 新增收款明细使用
     **/
    private String payids;

    /**
     * 新增附件使用
     */
    private String fileUtilIds;


    /**
     * 删除资料明细使用
     */
    private String recordids;



    /**
     * 新增进度状态使用
     */
    private String soParticularsIds;


    /**
     * 存储当前订单审核状态
     */
    private SysFlow flow;



    /**
     * 存储当前订单审核状态明细
     */
    private SysFlowinfoMoving flowinfoMoving;

    /**
     * 申请人
     */
    private SysUser createUser;


    public void setSalesDetailId(Long salesDetailId) {
        this.salesDetailId = salesDetailId;
    }

    public Long getSalesDetailId() {
        return salesDetailId;
    }

    public void setBuldingid(Long buldingid) {
        this.buldingid = buldingid;
    }

    public Long getBuldingid() {
        return buldingid;
    }

    public void setHouseid(Long houseid) {
        this.houseid = houseid;
    }

    public Long getHouseid() {
        return houseid;
    }

    public void setSalseFloorPrice(String salseFloorPrice) {
        this.salseFloorPrice = salseFloorPrice;
    }

    public String getSalseFloorPrice() {
        return salseFloorPrice;
    }

    public void setBookBuyer(String bookBuyer) {
        this.bookBuyer = bookBuyer;
    }

    public String getBookBuyer() {
        return bookBuyer;
    }

    public void setBuyertime(Date buyertime) {
        this.buyertime = buyertime;
    }

    public Date getBuyertime() {
        return buyertime;
    }

    public void setBookBuyerPrice(String bookBuyerPrice) {
        this.bookBuyerPrice = bookBuyerPrice;
    }

    public String getBookBuyerPrice() {
        return bookBuyerPrice;
    }

    public void setBookBuyerAllprice(String bookBuyerAllprice) {
        this.bookBuyerAllprice = bookBuyerAllprice;
    }

    public String getBookBuyerAllprice() {
        return bookBuyerAllprice;
    }

    public void setContracttime(Date contracttime) {
        this.contracttime = contracttime;
    }

    public Date getContracttime() {
        return contracttime;
    }

    public void setFinalBuyer(String finalBuyer) {
        this.finalBuyer = finalBuyer;
    }

    public String getFinalBuyer() {
        return finalBuyer;
    }

    public void setContractPrice(String contractPrice) {
        this.contractPrice = contractPrice;
    }

    public String getContractPrice() {
        return contractPrice;
    }

    public void setContractAllprice(String contractAllprice) {
        this.contractAllprice = contractAllprice;
    }

    public String getContractAllprice() {
        return contractAllprice;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setFloorPriceSure(String floorPriceSure) {
        this.floorPriceSure = floorPriceSure;
    }

    public String getFloorPriceSure() {
        return floorPriceSure;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getCollection() {
        return collection;
    }

    public void setSurplusPrice(String surplusPrice) {
        this.surplusPrice = surplusPrice;
    }

    public String getSurplusPrice() {
        return surplusPrice;
    }

    public void setCommissionTime(Date commissionTime) {
        this.commissionTime = commissionTime;
    }

    public Date getCommissionTime() {
        return commissionTime;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("salesDetailId", getSalesDetailId())
                .append("buldingid", getBuldingid())
                .append("houseid", getHouseid())
                .append("salseFloorPrice", getSalseFloorPrice())
                .append("bookBuyer", getBookBuyer())
                .append("buyertime", getBuyertime())
                .append("bookBuyerPrice", getBookBuyerPrice())
                .append("bookBuyerAllprice", getBookBuyerAllprice())
                .append("contracttime", getContracttime())
                .append("finalBuyer", getFinalBuyer())
                .append("contractPrice", getContractPrice())
                .append("contractAllprice", getContractAllprice())
                .append("payMethod", getPayMethod())
                .append("floorPriceSure", getFloorPriceSure())
                .append("collection", getCollection())
                .append("surplusPrice", getSurplusPrice())
                .append("commissionTime", getCommissionTime())
                .append("remark", getRemark())
                .append("commissionList", getCommissionList())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("installmentPrice", getInstallmentPrice())
                .append("mortgagePrice", getMortgagePrice())
                .append("rantPrice", getRantPrice())
                .append("buyerPrice", getBuyerPrice())
                .append("bookBuyerPhone", getBookBuyerPhone())
                .append("orderStatus", getOrderStatus())
                .append("finalBuyerPhone", getFinalBuyerPhone())
                .append("rdInstallmentPrice", getRdInstallmentPrice())
                .append("rmiInstallmentPrice", getRmiInstallmentPrice())
                .append("priceEndTime", getPriceEndTime())
                .append("salePerson", getSalePerson())
                .toString();
    }

    public SysBuilding getBulding() {
        if (bulding == null) {
            bulding = new SysBuilding();
        }
        return bulding;
    }

    public void setBulding(SysBuilding bulding) {
        this.bulding = bulding;
    }

    public SysHouse getHouse() {
        if (house == null) {
            house = new SysHouse();
        }
        return house;
    }

    public void setHouse(SysHouse house) {
        this.house = house;
    }

    public List<SysCommission> getCommissionList() {
        if (commissionList == null) {
            commissionList = new ArrayList<SysCommission>();
        }
        return commissionList;
    }

    public void setCommissionList(List<SysCommission> commissionList) {
        this.commissionList = commissionList;
    }

    public String getCids() {
        return cids;
    }

    public void setCids(String cids) {
        this.cids = cids;
    }

    public String getInstallmentPrice() {
        return installmentPrice;
    }

    public void setInstallmentPrice(String installmentPrice) {
        this.installmentPrice = installmentPrice;
    }

    public String getRantPrice() {
        return rantPrice;
    }

    public void setRantPrice(String rantPrice) {
        this.rantPrice = rantPrice;
    }

    public String getMortgagePrice() {
        return mortgagePrice;
    }

    public void setMortgagePrice(String mortgagePrice) {
        this.mortgagePrice = mortgagePrice;
    }

    public String getBuyerPrice() {
        return buyerPrice;
    }

    public void setBuyerPrice(String buyerPrice) {
        this.buyerPrice = buyerPrice;
    }


    public String getBookBuyerPhone() {
        return bookBuyerPhone;
    }

    public void setBookBuyerPhone(String bookBuyerPhone) {
        this.bookBuyerPhone = bookBuyerPhone;
    }

    public String getPayids() {
        return payids;
    }

    public void setPayids(String payids) {
        this.payids = payids;
    }

    public String getFileUtilIds() {
        return fileUtilIds;
    }

    public void setFileUtilIds(String fileUtilIds) {
        this.fileUtilIds = fileUtilIds;
    }


    public String getFinalBuyerPhone() {
        return finalBuyerPhone;
    }

    public void setFinalBuyerPhone(String finalBuyerPhone) {
        this.finalBuyerPhone = finalBuyerPhone;
    }

    public String getRdInstallmentPrice() {
        return rdInstallmentPrice;
    }

    public void setRdInstallmentPrice(String rdInstallmentPrice) {
        this.rdInstallmentPrice = rdInstallmentPrice;
    }

    public String getRmiInstallmentPrice() {
        return rmiInstallmentPrice;
    }

    public void setRmiInstallmentPrice(String rmiInstallmentPrice) {
        this.rmiInstallmentPrice = rmiInstallmentPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSoParticularsIds() {
        return soParticularsIds;
    }

    public void setSoParticularsIds(String soParticularsIds) {
        this.soParticularsIds = soParticularsIds;
    }

    public Date getPriceEndTime() {
        return priceEndTime;
    }

    public void setPriceEndTime(Date priceEndTime) {
        this.priceEndTime = priceEndTime;
    }

    public SysHouseUnit getHouseUnit() {
        return houseUnit;
    }

    public void setHouseUnit(SysHouseUnit houseUnit) {
        this.houseUnit = houseUnit;
    }

    public String getSalePerson() {
        return salePerson;
    }

    public void setSalePerson(String salePerson) {
        this.salePerson = salePerson;
    }

    public String getRecordids() {
        return recordids;
    }

    public void setRecordids(String recordids) {
        this.recordids = recordids;
    }

    public SysFlow getFlow() {
        return flow;
    }

    public void setFlow(SysFlow flow) {
        this.flow = flow;
    }

    public SysUser getCreateUser() {
        return createUser;
    }

    public void setCreateUser(SysUser createUser) {
        this.createUser = createUser;
    }

    public SysFlowinfoMoving getFlowinfoMoving() {
        return flowinfoMoving;
    }

    public void setFlowinfoMoving(SysFlowinfoMoving flowinfoMoving) {
        this.flowinfoMoving = flowinfoMoving;
    }
}
