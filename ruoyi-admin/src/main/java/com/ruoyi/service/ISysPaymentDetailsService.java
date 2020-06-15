package com.ruoyi.service;

import com.ruoyi.system.domain.SysPaymentDetails;

import java.util.List;

/**
 * 收款明细Service接口
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public interface ISysPaymentDetailsService 
{
    /**
     * 查询收款明细
     * 
     * @param paymentId 收款明细ID
     * @return 收款明细
     */
    public SysPaymentDetails selectSysPaymentDetailsById(Long paymentId);

    /**
     * 查询收款明细列表
     * 
     * @param sysPaymentDetails 收款明细
     * @return 收款明细集合
     */
    public List<SysPaymentDetails> selectSysPaymentDetailsList(SysPaymentDetails sysPaymentDetails);

    /**
     * 根据销售单id查询收款明细列表
     *
     * @param salesDetailId 销售单Id
     * @return 收款明细集合
     */
    public List<SysPaymentDetails> selectPaymentByOrderId(String salesDetailId);


    /**
     * 新增收款明细
     * 
     * @param sysPaymentDetails 收款明细
     * @return 结果
     */
    public int insertSysPaymentDetails(SysPaymentDetails sysPaymentDetails);

    /**
     * 修改收款明细
     * 
     * @param sysPaymentDetails 收款明细
     * @return 结果
     */
    public int updateSysPaymentDetails(SysPaymentDetails sysPaymentDetails);

    /**
     * 批量删除收款明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysPaymentDetailsByIds(String ids);

    /**
     * 删除收款明细信息
     * 
     * @param paymentId 收款明细ID
     * @return 结果
     */
    public int deleteSysPaymentDetailsById(Long paymentId);
}
