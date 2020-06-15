package com.ruoyi.service;

import com.ruoyi.system.domain.SysCustomer;

import java.util.List;

/**
 * 客户信息Service接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface ISysCustomerService 
{
    /**
     * 查询客户信息
     * 
     * @param customerId 客户信息ID
     * @return 客户信息
     */
    public SysCustomer selectSysCustomerById(Long customerId);

    /**
     * 查询客户信息列表
     * 
     * @param sysCustomer 客户信息
     * @return 客户信息集合
     */
    public List<SysCustomer> selectSysCustomerList(SysCustomer sysCustomer);

    /**
     * 新增客户信息
     * 
     * @param sysCustomer 客户信息
     * @return 结果
     */
    public int insertSysCustomer(SysCustomer sysCustomer);

    /**
     * 修改客户信息
     * 
     * @param sysCustomer 客户信息
     * @return 结果
     */
    public int updateSysCustomer(SysCustomer sysCustomer);

    /**
     * 批量删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCustomerByIds(String ids);

    /**
     * 删除客户信息信息
     * 
     * @param customerId 客户信息ID
     * @return 结果
     */
    public int deleteSysCustomerById(Long customerId);
}
