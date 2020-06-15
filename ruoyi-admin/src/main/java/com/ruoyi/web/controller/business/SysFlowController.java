package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysFlowService;
import com.ruoyi.service.ISysFlowinfoMovingService;
import com.ruoyi.system.domain.SysFlow;
import com.ruoyi.system.domain.SysFlowinfoMoving;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程流转信息Controller
 *
 * @author ruoyi
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/system/flow")
public class SysFlowController extends BaseController {
    private String prefix = "system/flow";

    @Autowired
    private ISysFlowService sysFlowService;


    @Autowired
    private ISysUserService userService;


    @Autowired
    private ISysFlowinfoMovingService flowinfoMovingService;


    @RequiresPermissions("system:flow:view")
    @GetMapping()
    public String flow() {
        return prefix + "/flow";
    }

    /**
     * 查询流程流转信息列表
     */
    @RequiresPermissions("system:flow:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFlow sysFlow) {
        startPage();
        List<SysFlow> list = sysFlowService.selectSysFlowList(sysFlow);
        return getDataTable(list);
    }


    /**
     * 导出流程流转信息列表
     */
    @RequiresPermissions("system:flow:export")
    @Log(title = "流程流转信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysFlow sysFlow) {
        List<SysFlow> list = sysFlowService.selectSysFlowList(sysFlow);
        ExcelUtil<SysFlow> util = new ExcelUtil<SysFlow>(SysFlow.class);
        return util.exportExcel(list, "flow");
    }

    /**
     * 新增流程流转信息
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存流程流转信息
     */
    @RequiresPermissions("system:flow:add")
    @Log(title = "流程流转信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysFlow sysFlow, String dealLink) {
            //新增流程中间表数据
            sysFlow.setCreateBy(ShiroUtils.getLoginName());
            sysFlow.setApplyuser(ShiroUtils.getUserId());
            if (sysFlow.getOptionStatus() == 0) {
                sysFlow.setHandleruser(ShiroUtils.getUserId());
                sysFlow.setStatus(0);
            }else{
                sysFlow.setStatus(1);
            }
            sysFlowService.insertSysFlow(sysFlow);
            SysUser user =  userService.selectUserById(sysFlow.getApplyuser());

            //新增流程明细
            SysFlowinfoMoving moving = new SysFlowinfoMoving();
            moving.setFlowId(sysFlow.getId());
            moving.setDealUser(sysFlow.getHandleruser());
            moving.setCreateBy(ShiroUtils.getLoginName());
            moving.setDealtime(DateUtils.getNowDate());
            moving.setOpinion("新建");
            moving.setRemark("新建审批单");
            if (sysFlow.getOptionStatus() == 0) {
                moving.setStatus(1);
                moving.setTitle(user.getUserName()+ "新建审批单！");
            } else if (sysFlow.getOptionStatus() == 1) {
                moving.setStatus(0);
                moving.setTitle(user.getUserName()+ "提交审批单！");
            }
            flowinfoMovingService.insertSysFlowinfoMoving(moving);
            if (sysFlow.getOptionStatus() == 1) {
                moving = new SysFlowinfoMoving();
                moving.setFlowId(sysFlow.getId());
                moving.setDealUser(sysFlow.getHandleruser());
                moving.setCreateBy(ShiroUtils.getLoginName());
                moving.setStatus(1);
                moving.setTitle("请审批"+user.getUserName() + "提交的审批单！");
                moving.setDealLink(dealLink);
                flowinfoMovingService.insertSysFlowinfoMoving(moving);
            }
        return success();
    }


    /**
     * 新增保存流程流转信息
     */
    @RequiresPermissions("system:flow:edit")
    @Log(title = "流程流转信息", businessType = BusinessType.INSERT)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysFlow sysFlow, String dealLink) {
        if (sysFlow.getOptionStatus() == 1) {
            SysFlow flow =  sysFlowService.selectSysFlowByTypeId(sysFlow.getTypeId());
            flow.setUpdateBy(ShiroUtils.getLoginName());
            flow.setStatus(1);
            flow.setHandleruser(sysFlow.getHandleruser());
            sysFlowService.updateSysFlow(flow);

            SysUser user =  userService.selectUserById(flow.getApplyuser());

            //更新上一条明细信息
            SysFlowinfoMoving moving = new SysFlowinfoMoving();
            moving.setFlowId(flow.getId());
            moving =  flowinfoMovingService.selectSysLastNumber(moving);
            if(moving.getStatus()==0){
                moving.setDealtime(DateUtils.getNowDate());
                moving.setStatus(0);
                flowinfoMovingService.updateSysFlowinfoMoving(moving);
                //新增下一条审批流信息
                moving = new SysFlowinfoMoving();
                moving.setFlowId(flow.getId());
                moving.setDealUser(flow.getHandleruser());
                moving.setStatus(1);
                moving.setTitle("请审批"+user.getUserName() + "提交的审批单！");
                moving.setDealLink(dealLink);
                flowinfoMovingService.insertSysFlowinfoMoving(moving);
            }
        }
        return success();
    }

    /**
     * 修改流程流转信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SysFlow sysFlow = sysFlowService.selectSysFlowById(id);
        mmap.put("sysFlow", sysFlow);
        return prefix + "/edit";
    }

//    /**
//     * 修改保存流程流转信息
//     */
//    @RequiresPermissions("system:flow:edit")
//    @Log(title = "流程流转信息", businessType = BusinessType.UPDATE)
//    @PostMapping("/edit")
//    @ResponseBody
//    public AjaxResult editSave(SysFlow sysFlow) {
//        return toAjax(sysFlowService.updateSysFlow(sysFlow));
//    }

    /**
     * 删除流程流转信息
     */
    @RequiresPermissions("system:flow:remove")
    @Log(title = "流程流转信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysFlowService.deleteSysFlowByIds(ids));
    }
}
