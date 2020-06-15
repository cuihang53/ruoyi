package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysSalesOrder;

import java.util.List;

/**
 * 销售信息录入Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-16
 */
public interface SysSalesOrderMapper
{
    /**
     * 查询销售信息录入
     * 
     * @param salesDetailId 销售信息录入ID
     * @return 销售信息录入
     */
    public SysSalesOrder selectSysSalesDetailById(Long salesDetailId);

    /**
     * 查询销售信息录入列表
     * 
     * @param sysSalesDetail 销售信息录入
     * @return 销售信息录入集合
     */
    public List<SysSalesOrder> selectSysSalesDetailList(SysSalesOrder sysSalesDetail);

    /**
     * 新增销售信息录入
     * 
     * @param sysSalesDetail 销售信息录入
     * @return 结果
     */
    public int insertSysSalesDetail(SysSalesOrder sysSalesDetail);

    /**
     * 修改销售信息录入
     * 
     * @param sysSalesDetail 销售信息录入
     * @return 结果
     */
    public int updateSysSalesDetail(SysSalesOrder sysSalesDetail);

    /**
     * 删除销售信息录入
     * 
     * @param salesDetailId 销售信息录入ID
     * @return 结果
     */
    public int deleteSysSalesDetailById(Long salesDetailId);

    /**
     * 批量删除销售信息录入
     * 
     * @param salesDetailIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSalesDetailByIds(String[] salesDetailIds);
}
