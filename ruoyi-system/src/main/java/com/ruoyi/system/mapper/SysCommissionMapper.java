package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysCommission;

import java.util.List;

/**
 * 提成明细Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-17
 */
public interface SysCommissionMapper 
{
    /**
     * 查询提成明细
     * 
     * @param id 提成明细ID
     * @return 提成明细
     */
    public SysCommission selectSysCommissionById(Long id);

    /**
     * 查询提成明细列表
     * 
     * @param sysCommission 提成明细
     * @return 提成明细集合
     */
    public List<SysCommission> selectSysCommissionList(SysCommission sysCommission);


    /**
     * 根据销售单ID查询提成明细列表
     *
     * @param salesDetailId 提成明细
     * @return 提成明细集合
     */
    public List<SysCommission> selectByorderId(Long salesDetailId);

    /**
     * 新增提成明细
     * 
     * @param sysCommission 提成明细
     * @return 结果
     */
    public int insertSysCommission(SysCommission sysCommission);

    /**
     * 修改提成明细
     * 
     * @param sysCommission 提成明细
     * @return 结果
     */
    public int updateSysCommission(SysCommission sysCommission);

    /**
     * 删除提成明细
     * 
     * @param id 提成明细ID
     * @return 结果
     */
    public int deleteSysCommissionById(Long id);

    /**
     * 批量删除提成明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCommissionByIds(String[] ids);
}
