package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysSoParticularsService;
import com.ruoyi.system.domain.SysSoParticulars;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售单当前状态明细Controller
 * 
 * @author ruoyi
 * @date 2020-05-30
 */
@Controller
@RequestMapping("/system/particulars")
public class SysSoParticularsController extends BaseController
{
    private String prefix = "system/particulars";

    @Autowired
    private ISysSoParticularsService sysSoParticularsService;

    @RequiresPermissions("system:particulars:view")
    @GetMapping()
    public String particulars()
    {
        return prefix + "/particulars";
    }

    /**
     * 查询销售单当前状态明细列表
     */
    @RequiresPermissions("system:particulars:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSoParticulars sysSoParticulars)
    {
        startPage();
        List<SysSoParticulars> list = sysSoParticularsService.selectSysSoParticularsList(sysSoParticulars);
        return getDataTable(list);
    }

    /**
     * 导出销售单当前状态明细列表
     */
    @RequiresPermissions("system:particulars:export")
    @Log(title = "销售单当前状态明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSoParticulars sysSoParticulars)
    {
        List<SysSoParticulars> list = sysSoParticularsService.selectSysSoParticularsList(sysSoParticulars);
        ExcelUtil<SysSoParticulars> util = new ExcelUtil<SysSoParticulars>(SysSoParticulars.class);
        return util.exportExcel(list, "particulars");
    }

    /**
     * 新增销售单当前状态明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售单当前状态明细
     */
    @RequiresPermissions("system:particulars:add")
    @Log(title = "销售单当前状态明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysSoParticulars sysSoParticulars, ModelMap mmap)
    {
        AjaxResult ajax = new AjaxResult();
        sysSoParticulars.setCreateBy(ShiroUtils.getLoginName());
        sysSoParticularsService.insertSysSoParticulars(sysSoParticulars);
        ajax.put("sysSoParticulars",sysSoParticulars);
        return ajax;
    }

    /**
     * 修改销售单当前状态明细
     */
    @GetMapping("/edit/{sysSoParticularsId}")
    public String edit(@PathVariable("sysSoParticularsId") Long sysSoParticularsId, ModelMap mmap)
    {
        SysSoParticulars sysSoParticulars = sysSoParticularsService.selectSysSoParticularsById(sysSoParticularsId);
        mmap.put("sysSoParticulars", sysSoParticulars);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售单当前状态明细
     */
    @RequiresPermissions("system:particulars:edit")
    @Log(title = "销售单当前状态明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysSoParticulars sysSoParticulars)
    {
        return toAjax(sysSoParticularsService.updateSysSoParticulars(sysSoParticulars));
    }

    /**
     * 删除销售单当前状态明细
     */
    @RequiresPermissions("system:particulars:remove")
    @Log(title = "销售单当前状态明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysSoParticularsService.deleteSysSoParticularsByIds(ids));
    }
}
