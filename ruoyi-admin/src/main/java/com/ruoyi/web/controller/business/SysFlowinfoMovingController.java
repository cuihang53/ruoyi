package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysFlowService;
import com.ruoyi.service.ISysFlowinfoMovingService;
import com.ruoyi.system.domain.SysFlowinfoMoving;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 流程流转处理意见记录Controller
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/system/moving")
public class SysFlowinfoMovingController extends BaseController
{
    private String prefix = "system/moving";

    @Autowired
    private ISysFlowinfoMovingService sysFlowinfoMovingService;


    @Autowired
    private ISysFlowService flowService;

    @RequiresPermissions("system:moving:view")
    @GetMapping()
    public String moving()
    {
        return prefix + "/moving";
    }

    /**
     * 查询流程流转处理意见记录列表
     */
    @RequiresPermissions("system:moving:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFlowinfoMoving sysFlowinfoMoving)
    {
        startPage();
        sysFlowinfoMoving.setDealUser(ShiroUtils.getUserId());
        List<SysFlowinfoMoving> list = sysFlowinfoMovingService.selectSysFlowinfoMovingList(sysFlowinfoMoving);
        return getDataTable(list);
    }

    /**
     * 导出流程流转处理意见记录列表
     */
    @RequiresPermissions("system:moving:export")
    @Log(title = "流程流转处理意见记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysFlowinfoMoving sysFlowinfoMoving)
    {
        List<SysFlowinfoMoving> list = sysFlowinfoMovingService.selectSysFlowinfoMovingList(sysFlowinfoMoving);
        ExcelUtil<SysFlowinfoMoving> util = new ExcelUtil<SysFlowinfoMoving>(SysFlowinfoMoving.class);
        return util.exportExcel(list, "moving");
    }

    /**
     * 新增流程流转处理意见记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流程流转处理意见记录
     */
    @RequiresPermissions("system:moving:add")
    @Log(title = "流程流转处理意见记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysFlowinfoMoving sysFlowinfoMoving)
    {
        return toAjax(sysFlowinfoMovingService.insertSysFlowinfoMoving(sysFlowinfoMoving));
    }

    /**
     * 修改流程流转处理意见记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysFlowinfoMoving sysFlowinfoMoving = sysFlowinfoMovingService.selectSysFlowinfoMovingById(id);
        mmap.put("sysFlowinfoMoving", sysFlowinfoMoving);
        return prefix + "/edit";
    }

    /**
     * 修改保存流程流转处理意见记录
     */
    @RequiresPermissions("system:moving:edit")
    @Log(title = "流程流转处理意见记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysFlowinfoMoving sysFlowinfoMoving)
    {
        return toAjax(sysFlowinfoMovingService.updateSysFlowinfoMoving(sysFlowinfoMoving));
    }

    /**
     * 删除流程流转处理意见记录
     */
    @RequiresPermissions("system:moving:remove")
    @Log(title = "流程流转处理意见记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysFlowinfoMovingService.deleteSysFlowinfoMovingByIds(ids));
    }
}
