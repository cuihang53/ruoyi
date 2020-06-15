package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.service.ISysSoParticularsService;
import com.ruoyi.system.domain.SysSoParticulars;
import com.ruoyi.system.mapper.SysSoParticularsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售单当前状态明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-30
 */
@Service
public class SysSoParticularsServiceImpl implements ISysSoParticularsService
{
    @Autowired
    private SysSoParticularsMapper sysSoParticularsMapper;

    /**
     * 查询销售单当前状态明细
     * 
     * @param sysSoParticularsId 销售单当前状态明细ID
     * @return 销售单当前状态明细
     */
    @Override
    public SysSoParticulars selectSysSoParticularsById(Long sysSoParticularsId)
    {
        return sysSoParticularsMapper.selectSysSoParticularsById(sysSoParticularsId);
    }

    /**
     * 查询销售单当前状态明细列表
     * 
     * @param sysSoParticulars 销售单当前状态明细
     * @return 销售单当前状态明细
     */
    @Override
    public List<SysSoParticulars> selectSysSoParticularsList(SysSoParticulars sysSoParticulars)
    {
        return sysSoParticularsMapper.selectSysSoParticularsList(sysSoParticulars);
    }

    /**
     * 新增销售单当前状态明细
     * 
     * @param sysSoParticulars 销售单当前状态明细
     * @return 结果
     */
    @Override
    public int insertSysSoParticulars(SysSoParticulars sysSoParticulars)
    {
        sysSoParticulars.setCreateTime(DateUtils.getNowDate());
        return sysSoParticularsMapper.insertSysSoParticulars(sysSoParticulars);
    }

    /**
     * 修改销售单当前状态明细
     * 
     * @param sysSoParticulars 销售单当前状态明细
     * @return 结果
     */
    @Override
    public int updateSysSoParticulars(SysSoParticulars sysSoParticulars)
    {
        sysSoParticulars.setUpdateTime(DateUtils.getNowDate());
        return sysSoParticularsMapper.updateSysSoParticulars(sysSoParticulars);
    }

    /**
     * 删除销售单当前状态明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysSoParticularsByIds(String ids)
    {
        return sysSoParticularsMapper.deleteSysSoParticularsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售单当前状态明细信息
     * 
     * @param sysSoParticularsId 销售单当前状态明细ID
     * @return 结果
     */
    @Override
    public int deleteSysSoParticularsById(Long sysSoParticularsId)
    {
        return sysSoParticularsMapper.deleteSysSoParticularsById(sysSoParticularsId);
    }
}
