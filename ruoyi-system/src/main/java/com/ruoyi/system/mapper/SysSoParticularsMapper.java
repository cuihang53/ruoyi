package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysSoParticulars;

import java.util.List;

/**
 * 销售单当前状态明细Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-30
 */
public interface SysSoParticularsMapper 
{
    /**
     * 查询销售单当前状态明细
     * 
     * @param sysSoParticularsId 销售单当前状态明细ID
     * @return 销售单当前状态明细
     */
    public SysSoParticulars selectSysSoParticularsById(Long sysSoParticularsId);

    /**
     * 查询销售单当前状态明细列表
     * 
     * @param sysSoParticulars 销售单当前状态明细
     * @return 销售单当前状态明细集合
     */
    public List<SysSoParticulars> selectSysSoParticularsList(SysSoParticulars sysSoParticulars);

    /**
     * 新增销售单当前状态明细
     * 
     * @param sysSoParticulars 销售单当前状态明细
     * @return 结果
     */
    public int insertSysSoParticulars(SysSoParticulars sysSoParticulars);

    /**
     * 修改销售单当前状态明细
     * 
     * @param sysSoParticulars 销售单当前状态明细
     * @return 结果
     */
    public int updateSysSoParticulars(SysSoParticulars sysSoParticulars);

    /**
     * 删除销售单当前状态明细
     * 
     * @param sysSoParticularsId 销售单当前状态明细ID
     * @return 结果
     */
    public int deleteSysSoParticularsById(Long sysSoParticularsId);

    /**
     * 批量删除销售单当前状态明细
     * 
     * @param sysSoParticularsIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSoParticularsByIds(String[] sysSoParticularsIds);
}
