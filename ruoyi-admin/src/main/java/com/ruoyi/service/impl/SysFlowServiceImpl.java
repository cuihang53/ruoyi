package com.ruoyi.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysFlowService;
import com.ruoyi.system.domain.SysFlow;
import com.ruoyi.system.domain.SysFlowinfoMoving;
import com.ruoyi.system.mapper.SysFlowMapper;
import com.ruoyi.system.mapper.SysFlowinfoMovingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 流程流转信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Service
public class SysFlowServiceImpl implements ISysFlowService
{
    @Autowired
    private SysFlowMapper sysFlowMapper;

    @Autowired
    private SysFlowinfoMovingMapper movingMapper;


    /**
     * 查询流程流转信息
     * 
     * @param id 流程流转信息ID
     * @return 流程流转信息
     */
    @Override
    public SysFlow selectSysFlowById(Long id)
    {
        return sysFlowMapper.selectSysFlowById(id);
    }


    /**
     * 新建流程的时候流程明细处理
     * @return
     */
    @Override
    public AjaxResult addSave(SysFlow sysFlow, String  dealLink) {

        AjaxResult ajax = new AjaxResult();
        sysFlow.setCreateBy(ShiroUtils.getLoginName());
        sysFlow.setApplyuser(ShiroUtils.getUserId());
        Boolean bool = true;
        if (sysFlow.getHandleruser() == null) {
            sysFlow.setHandleruser(ShiroUtils.getUserId());
            bool = false;
        }
        sysFlowMapper.insertSysFlow(sysFlow);
        SysFlowinfoMoving sysFlowinfoMoving = null;
        if (bool) {
            //先插入新建的的流程记录
            sysFlowinfoMoving = new SysFlowinfoMoving();
            sysFlowinfoMoving.setFlowId(sysFlow.getId());
            sysFlowinfoMoving.setDealUser(sysFlow.getHandleruser());
            sysFlowinfoMoving.setDealtime(DateUtils.getNowDate());
            sysFlowinfoMoving.setRemark("创建人提交");
            sysFlowinfoMoving.setCreateBy(ShiroUtils.getLoginName());
            sysFlowinfoMoving.setStatus(0);
            sysFlowinfoMoving.setDealLink(dealLink);
            sysFlowinfoMoving.setCreateBy(ShiroUtils.getLoginName());
            movingMapper.insertSysFlowinfoMoving(sysFlowinfoMoving);
        }
        ajax.put("sysFlow",sysFlow);
        ajax.put("sysFlowinfoMoving",sysFlowinfoMoving);
        return ajax;
    }

    @Override
    public SysFlow selectSysFlowByTypeId(Long id) {
        return sysFlowMapper.selectSysFlowByTypeId(id);
    }

    /**
     * 查询流程流转信息列表
     * 
     * @param sysFlow 流程流转信息
     * @return 流程流转信息
     */
    @Override
    public List<SysFlow> selectSysFlowList(SysFlow sysFlow)
    {
        return sysFlowMapper.selectSysFlowList(sysFlow);
    }

    /**
     * 新增流程流转信息
     * 
     * @param sysFlow 流程流转信息
     * @return 结果
     */
    @Override
    public int insertSysFlow(SysFlow sysFlow)
    {
        sysFlow.setCreateTime(DateUtils.getNowDate());
        return sysFlowMapper.insertSysFlow(sysFlow);
    }

    /**
     * 修改流程流转信息
     * 
     * @param sysFlow 流程流转信息
     * @return 结果
     */
    @Override
    public int updateSysFlow(SysFlow sysFlow)
    {
        sysFlow.setUpdateTime(DateUtils.getNowDate());
        return sysFlowMapper.updateSysFlow(sysFlow);
    }

    /**
     * 删除流程流转信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFlowByIds(String ids)
    {
        return sysFlowMapper.deleteSysFlowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流程流转信息信息
     * 
     * @param id 流程流转信息ID
     * @return 结果
     */
    @Override
    public int deleteSysFlowById(Long id)
    {
        return sysFlowMapper.deleteSysFlowById(id);
    }
}
