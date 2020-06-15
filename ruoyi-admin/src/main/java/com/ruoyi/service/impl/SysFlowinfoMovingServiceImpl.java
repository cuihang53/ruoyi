package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.service.ISysFlowinfoMovingService;
import com.ruoyi.system.domain.SysFlowinfoMoving;
import com.ruoyi.system.mapper.SysFlowinfoMovingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程流转处理意见记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Service
public class SysFlowinfoMovingServiceImpl implements ISysFlowinfoMovingService
{
    @Autowired
    private SysFlowinfoMovingMapper sysFlowinfoMovingMapper;

    /**
     * 查询流程流转处理意见记录
     * 
     * @param id 流程流转处理意见记录ID
     * @return 流程流转处理意见记录
     */
    @Override
    public SysFlowinfoMoving selectSysFlowinfoMovingById(Long id)
    {
        return sysFlowinfoMovingMapper.selectSysFlowinfoMovingById(id);
    }

    /**
     * 根据流程主表id获取详情表最新详情
     *
     * @return 流程流转处理意见记录
     */
    @Override
    public SysFlowinfoMoving selectSysLastNumber(SysFlowinfoMoving sysFlowinfoMoving) {

        return sysFlowinfoMovingMapper.selectSysLastNumber(sysFlowinfoMoving);
    }

    /**
     * 查询流程流转处理意见记录列表
     * 
     * @param sysFlowinfoMoving 流程流转处理意见记录
     * @return 流程流转处理意见记录
     */
    @Override
    public List<SysFlowinfoMoving> selectSysFlowinfoMovingList(SysFlowinfoMoving sysFlowinfoMoving)
    {
        return sysFlowinfoMovingMapper.selectSysFlowinfoMovingList(sysFlowinfoMoving);
    }

    @Override
    public List<SysFlowinfoMoving> selectByFlowId(Long flowId) {
        return sysFlowinfoMovingMapper.selectByFlowId(flowId);
    }

    /**
     * 新增流程流转处理意见记录
     * 
     * @param sysFlowinfoMoving 流程流转处理意见记录
     * @return 结果
     */
    @Override
    public int insertSysFlowinfoMoving(SysFlowinfoMoving sysFlowinfoMoving)
    {
        sysFlowinfoMoving.setCreateTime(DateUtils.getNowDate());
        return sysFlowinfoMovingMapper.insertSysFlowinfoMoving(sysFlowinfoMoving);
    }

    /**
     * 修改流程流转处理意见记录
     * 
     * @param sysFlowinfoMoving 流程流转处理意见记录
     * @return 结果
     */
    @Override
    public int updateSysFlowinfoMoving(SysFlowinfoMoving sysFlowinfoMoving)
    {
        sysFlowinfoMoving.setUpdateTime(DateUtils.getNowDate());
        return sysFlowinfoMovingMapper.updateSysFlowinfoMoving(sysFlowinfoMoving);
    }

    /**
     * 删除流程流转处理意见记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFlowinfoMovingByIds(String ids)
    {
        return sysFlowinfoMovingMapper.deleteSysFlowinfoMovingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程流转处理意见记录信息
     * 
     * @param id 流程流转处理意见记录ID
     * @return 结果
     */
    @Override
    public int deleteSysFlowinfoMovingById(Long id)
    {
        return sysFlowinfoMovingMapper.deleteSysFlowinfoMovingById(id);
    }
}
