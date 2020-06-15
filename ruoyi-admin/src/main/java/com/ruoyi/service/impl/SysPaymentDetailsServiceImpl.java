package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.service.ISysPaymentDetailsService;
import com.ruoyi.system.domain.SysPaymentDetails;
import com.ruoyi.system.mapper.SysPaymentDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 收款明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Service
public class SysPaymentDetailsServiceImpl implements ISysPaymentDetailsService
{
    @Autowired
    private SysPaymentDetailsMapper sysPaymentDetailsMapper;

    /**
     * 查询收款明细
     * 
     * @param paymentId 收款明细ID
     * @return 收款明细
     */
    @Override
    public SysPaymentDetails selectSysPaymentDetailsById(Long paymentId)
    {
        return sysPaymentDetailsMapper.selectSysPaymentDetailsById(paymentId);
    }

    /**
     * 查询收款明细列表
     * 
     * @param sysPaymentDetails 收款明细
     * @return 收款明细
     */
    @Override
    public List<SysPaymentDetails> selectSysPaymentDetailsList(SysPaymentDetails sysPaymentDetails)
    {
        return sysPaymentDetailsMapper.selectSysPaymentDetailsList(sysPaymentDetails);
    }

    @Override
    public List<SysPaymentDetails> selectPaymentByOrderId(String salesDetailId) {

        return sysPaymentDetailsMapper.selectPaymentByOrderId(salesDetailId);
    }

    /**
     * 新增收款明细
     * 
     * @param sysPaymentDetails 收款明细
     * @return 结果
     */
    @Override
    public int insertSysPaymentDetails(SysPaymentDetails sysPaymentDetails)
    {
        return sysPaymentDetailsMapper.insertSysPaymentDetails(sysPaymentDetails);
    }

    /**
     * 修改收款明细
     * 
     * @param sysPaymentDetails 收款明细
     * @return 结果
     */
    @Override
    public int updateSysPaymentDetails(SysPaymentDetails sysPaymentDetails)
    {
        return sysPaymentDetailsMapper.updateSysPaymentDetails(sysPaymentDetails);
    }

    /**
     * 删除收款明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysPaymentDetailsByIds(String ids)
    {
        return sysPaymentDetailsMapper.deleteSysPaymentDetailsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除收款明细信息
     * 
     * @param paymentId 收款明细ID
     * @return 结果
     */
    @Override
    public int deleteSysPaymentDetailsById(Long paymentId)
    {
        return sysPaymentDetailsMapper.deleteSysPaymentDetailsById(paymentId);
    }
}
