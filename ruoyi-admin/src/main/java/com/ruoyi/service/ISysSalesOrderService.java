package com.ruoyi.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysSalesOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 销售信息录入Service接口
 * 
 * @author ruoyi
 * @date 2020-05-16
 */
public interface ISysSalesOrderService
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
     * 导出
     * @param checkedNum
     */
    public AjaxResult importExcel(String checkedNum, SysSalesOrder salesOrder, HttpServletRequest request, HttpServletResponse response);

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
     * 批量删除销售信息录入
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSalesDetailByIds(String ids);

    /**
     * 删除销售信息录入信息
     * 
     * @param salesDetailId 销售信息录入ID
     * @return 结果
     */
    public int deleteSysSalesDetailById(Long salesDetailId);
}
