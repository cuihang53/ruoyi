package com.ruoyi.service;

import com.ruoyi.system.domain.SysFlowinfoMoving;

import java.util.List;

/**
 * 流程流转处理意见记录Service接口
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public interface ISysFlowinfoMovingService 
{
    /**
     * 查询流程流转处理意见记录
     * 
     * @param id 流程流转处理意见记录ID
     * @return 流程流转处理意见记录
     */
    public SysFlowinfoMoving selectSysFlowinfoMovingById(Long id);


    /**
     * 根据流程主表id获取详情表最新详情
     *
     * @return 流程流转处理意见记录
     */
    public SysFlowinfoMoving selectSysLastNumber(SysFlowinfoMoving sysFlowinfoMoving);


    /**
     * 查询流程流转处理意见记录列表
     * 
     * @param sysFlowinfoMoving 流程流转处理意见记录
     * @return 流程流转处理意见记录集合
     */
    public List<SysFlowinfoMoving> selectSysFlowinfoMovingList(SysFlowinfoMoving sysFlowinfoMoving);


    /**
     * 根據流程主表id查詢所有詳情
     *
     * @param flowId 流程流转处理意见记录
     * @return 流程流转处理意见记录集合
     */
    public List<SysFlowinfoMoving> selectByFlowId(Long flowId);

    /**
     * 新增流程流转处理意见记录
     * 
     * @param sysFlowinfoMoving 流程流转处理意见记录
     * @return 结果
     */
    public int insertSysFlowinfoMoving(SysFlowinfoMoving sysFlowinfoMoving);

    /**
     * 修改流程流转处理意见记录
     * 
     * @param sysFlowinfoMoving 流程流转处理意见记录
     * @return 结果
     */
    public int updateSysFlowinfoMoving(SysFlowinfoMoving sysFlowinfoMoving);

    /**
     * 批量删除流程流转处理意见记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFlowinfoMovingByIds(String ids);

    /**
     * 删除流程流转处理意见记录信息
     * 
     * @param id 流程流转处理意见记录ID
     * @return 结果
     */
    public int deleteSysFlowinfoMovingById(Long id);
}
