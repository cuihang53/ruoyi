package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.service.ISysSalesTeamService;
import com.ruoyi.system.domain.SysSalesTeam;
import com.ruoyi.system.mapper.SysSalesTeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售小组管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
public class SysSalesTeamServiceImpl implements ISysSalesTeamService
{
    @Autowired
    private SysSalesTeamMapper sysSalesTeamMapper;

    /**
     * 查询销售小组管理
     * 
     * @param id 销售小组管理ID
     * @return 销售小组管理
     */
    @Override
    public SysSalesTeam selectSysSalesTeamById(Long id)
    {
        return sysSalesTeamMapper.selectSysSalesTeamById(id);
    }

    /**
     * 查询销售小组管理列表
     * 
     * @param sysSalesTeam 销售小组管理
     * @return 销售小组管理
     */
    @Override
    public List<SysSalesTeam> selectSysSalesTeamList(SysSalesTeam sysSalesTeam)
    {
        return sysSalesTeamMapper.selectSysSalesTeamList(sysSalesTeam);
    }

    /**
     * 新增销售小组管理
     * 
     * @param sysSalesTeam 销售小组管理
     * @return 结果
     */
    @Override
    public int insertSysSalesTeam(SysSalesTeam sysSalesTeam)
    {
        sysSalesTeam.setCreateTime(DateUtils.getNowDate());
        return sysSalesTeamMapper.insertSysSalesTeam(sysSalesTeam);
    }

    /**
     * 修改销售小组管理
     * 
     * @param sysSalesTeam 销售小组管理
     * @return 结果
     */
    @Override
    public int updateSysSalesTeam(SysSalesTeam sysSalesTeam)
    {
        sysSalesTeam.setUpdateTime(DateUtils.getNowDate());
        return sysSalesTeamMapper.updateSysSalesTeam(sysSalesTeam);
    }

    /**
     * 删除销售小组管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysSalesTeamByIds(String ids)
    {
        return sysSalesTeamMapper.deleteSysSalesTeamByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售小组管理信息
     * 
     * @param id 销售小组管理ID
     * @return 结果
     */
    @Override
    public int deleteSysSalesTeamById(Long id)
    {
        return sysSalesTeamMapper.deleteSysSalesTeamById(id);
    }
}
