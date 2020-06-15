package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysSalesTeamPerson;

import java.util.List;

/**
 * 销售小组成员信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public interface SysSalesTeampersonMapper 
{
    /**
     * 查询销售小组成员信息
     * 
     * @param salesId 销售小组成员信息ID
     * @return 销售小组成员信息
     */
    public SysSalesTeamPerson selectSysSalesTeampersonById(Long salesId);

    /**
     * 查询销售小组成员信息
     *
     * @param teamId 销售小组ID
     * @return 销售小组成员信息
     */
    public List<SysSalesTeamPerson> selectByTeamId(Long teamId);

    /**
     * 查询销售小组成员信息列表
     * 
     * @param sysSalesTeamperson 销售小组成员信息
     * @return 销售小组成员信息集合
     */
    public List<SysSalesTeamPerson> selectSysSalesTeampersonList(SysSalesTeamPerson sysSalesTeamperson);

    /**
     * 新增销售小组成员信息
     * 
     * @param sysSalesTeamperson 销售小组成员信息
     * @return 结果
     */
    public int insertSysSalesTeamperson(SysSalesTeamPerson sysSalesTeamperson);

    /**
     * 修改销售小组成员信息
     * 
     * @param sysSalesTeamperson 销售小组成员信息
     * @return 结果
     */
    public int updateSysSalesTeamperson(SysSalesTeamPerson sysSalesTeamperson);

    /**
     * 删除销售小组成员信息
     * 
     * @param salesId 销售小组成员信息ID
     * @return 结果
     */
    public int deleteSysSalesTeampersonById(Long salesId);

    /**
     * 批量删除销售小组成员信息
     * 
     * @param salesIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSalesTeampersonByIds(String[] salesIds);
}
