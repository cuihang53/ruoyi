package com.ruoyi.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.SysFlow;

import java.util.List;

/**
 * 流程流转信息Service接口
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public interface ISysFlowService 
{
    /**
     * 查询流程流转信息
     * 
     * @param id 流程流转信息ID
     * @return 流程流转信息
     */
    public SysFlow selectSysFlowById(Long id);


    /**
     * 新建流程的时候流程明细处理
     * @return
     */
    public AjaxResult addSave(SysFlow flow, String dealLink);


    /**
     * 查询流程流转信息
     *
     * @param id 流程id
     * @return 流程流转信息
     */
    public SysFlow selectSysFlowByTypeId(Long id);

    /**
     * 查询流程流转信息列表
     * 
     * @param sysFlow 流程流转信息
     * @return 流程流转信息集合
     */
    public List<SysFlow> selectSysFlowList(SysFlow sysFlow);

    /**
     * 新增流程流转信息
     * 
     * @param sysFlow 流程流转信息
     * @return 结果
     */
    public int insertSysFlow(SysFlow sysFlow);

    /**
     * 修改流程流转信息
     * 
     * @param sysFlow 流程流转信息
     * @return 结果
     */
    public int updateSysFlow(SysFlow sysFlow);

    /**
     * 批量删除流程流转信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFlowByIds(String ids);

    /**
     * 删除流程流转信息信息
     * 
     * @param id 流程流转信息ID
     * @return 结果
     */
    public int deleteSysFlowById(Long id);
}
