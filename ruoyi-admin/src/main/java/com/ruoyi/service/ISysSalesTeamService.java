package com.ruoyi.service;

import com.ruoyi.system.domain.SysSalesTeam;

import java.util.List;

/**
 * 销售小组管理Service接口
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public interface ISysSalesTeamService 
{
    /**
     * 查询销售小组管理
     * 
     * @param id 销售小组管理ID
     * @return 销售小组管理
     */
    public SysSalesTeam selectSysSalesTeamById(Long id);

    /**
     * 查询销售小组管理列表
     * 
     * @param sysSalesTeam 销售小组管理
     * @return 销售小组管理集合
     */
    public List<SysSalesTeam> selectSysSalesTeamList(SysSalesTeam sysSalesTeam);

    /**
     * 新增销售小组管理
     * 
     * @param sysSalesTeam 销售小组管理
     * @return 结果
     */
    public int insertSysSalesTeam(SysSalesTeam sysSalesTeam);

    /**
     * 修改销售小组管理
     * 
     * @param sysSalesTeam 销售小组管理
     * @return 结果
     */
    public int updateSysSalesTeam(SysSalesTeam sysSalesTeam);

    /**
     * 批量删除销售小组管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSalesTeamByIds(String ids);

    /**
     * 删除销售小组管理信息
     * 
     * @param id 销售小组管理ID
     * @return 结果
     */
    public int deleteSysSalesTeamById(Long id);
}
