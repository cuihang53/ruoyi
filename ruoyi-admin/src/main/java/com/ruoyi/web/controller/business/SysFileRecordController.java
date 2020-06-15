package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysFileRecordService;
import com.ruoyi.system.domain.SysFileRecord;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 资料领取记录Controller
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
@Controller
@RequestMapping("/system/record")
public class SysFileRecordController extends BaseController
{
    private String prefix = "system/record";

    @Autowired
    private ISysFileRecordService sysFileRecordService;

    @RequiresPermissions("system:record:view")
    @GetMapping()
    public String record()
    {
        return prefix + "/record";
    }

    /**
     * 查询资料领取记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFileRecord sysFileRecord)
    {
        startPage();
        List<SysFileRecord> list = sysFileRecordService.selectSysFileRecordList(sysFileRecord);
        return getDataTable(list);
    }

    /**
     * 导出资料领取记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "资料领取记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysFileRecord sysFileRecord)
    {
        List<SysFileRecord> list = sysFileRecordService.selectSysFileRecordList(sysFileRecord);
        ExcelUtil<SysFileRecord> util = new ExcelUtil<SysFileRecord>(SysFileRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增资料领取记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存资料领取记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "资料领取记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysFileRecord sysFileRecord, ModelMap mmap)
    {
        AjaxResult ajax = new AjaxResult();
        sysFileRecord.setCreateBy(ShiroUtils.getLoginName());
        sysFileRecord.setRelevancyType(SysFileRecord.saleFileRecord);
        sysFileRecordService.insertSysFileRecord(sysFileRecord);
        ajax.put("sysFileRecord",sysFileRecord);
        return ajax;
    }

    /**
     * 修改资料领取记录
     */
    @GetMapping("/edit/{fileRecordId}")
    public String edit(@PathVariable("fileRecordId") Long fileRecordId, ModelMap mmap)
    {
        SysFileRecord sysFileRecord = sysFileRecordService.selectSysFileRecordById(fileRecordId);
        mmap.put("sysFileRecord", sysFileRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存资料领取记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "资料领取记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysFileRecord sysFileRecord)
    {
        sysFileRecord.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(sysFileRecordService.updateSysFileRecord(sysFileRecord));
    }

    /**
     * 删除资料领取记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "资料领取记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {

        return toAjax(sysFileRecordService.deleteSysFileRecordByIds(ids));
    }
}
