package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.service.ISysCustomerService;
import com.ruoyi.system.domain.SysCustomer;
import com.ruoyi.system.mapper.SysCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 客户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class SysCustomerServiceImpl implements ISysCustomerService
{
    @Autowired
    private SysCustomerMapper sysCustomerMapper;

    /**
     * 查询客户信息
     * 
     * @param customerId 客户信息ID
     * @return 客户信息
     */
    @Override
    public SysCustomer selectSysCustomerById(Long customerId)
    {
        return sysCustomerMapper.selectSysCustomerById(customerId);
    }

    /**
     * 查询客户信息列表
     * 
     * @param sysCustomer 客户信息
     * @return 客户信息
     */
    @Override
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer)
    {
        return sysCustomerMapper.selectSysCustomerList(sysCustomer);
    }

    /**
     * 新增客户信息
     * 
     * @param sysCustomer 客户信息
     * @return 结果
     */
    @Override
    public int insertSysCustomer(SysCustomer sysCustomer)
    {
        return sysCustomerMapper.insertSysCustomer(sysCustomer);
    }

    /**
     * 修改客户信息
     * 
     * @param sysCustomer 客户信息
     * @return 结果
     */
    @Override
    public int updateSysCustomer(SysCustomer sysCustomer)
    {
        return sysCustomerMapper.updateSysCustomer(sysCustomer);
    }

    /**
     * 删除客户信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCustomerByIds(String ids)
    {
        return sysCustomerMapper.deleteSysCustomerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户信息信息
     * 
     * @param customerId 客户信息ID
     * @return 结果
     */
    @Override
    public int deleteSysCustomerById(Long customerId)
    {
        return sysCustomerMapper.deleteSysCustomerById(customerId);
    }
}
