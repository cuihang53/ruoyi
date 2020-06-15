package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysCommissionService;
import com.ruoyi.system.domain.SysCommission;
import com.ruoyi.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 提成明细Controller
 * @author ruoyi
 * @date 2020-05-17
 */
@Controller
@RequestMapping("/system/commission")
public class SysCommissionController extends BaseController {
    private String prefix = "system/commission";

    @Autowired
    private ISysCommissionService sysCommissionService;

    @Autowired
    private ISysPostService postService;

    @RequiresPermissions("system:commission:view")
    @GetMapping()
    public String commission() {
        return prefix + "/commission";
    }

    /**
     * 查询提成明细列表
     */
    @RequiresPermissions("system:commission:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCommission sysCommission) {
        startPage();
        List<SysCommission> list = sysCommissionService.selectSysCommissionList(sysCommission);
        return getDataTable(list);
    }

    /**
     * 根据销售详细表id查询提成明细列表
     */
    @RequiresPermissions("system:commission:getListBySaleID")
    @PostMapping("/getListBySaleID")
    @ResponseBody
    public AjaxResult getListBySaleID(String delId) {
        AjaxResult ajax = new AjaxResult();
        SysCommission commission = new SysCommission();
        commission.setSalesDetailId(Long.parseLong(delId));
        List<SysCommission> commissions = sysCommissionService.selectSysCommissionList(commission);
        ajax.put("commissions", commissions);
        return ajax;
    }

    /**
     * 导出提成明细列表
     */
    @RequiresPermissions("system:commission:export")
    @Log(title = "提成明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCommission sysCommission) {
        List<SysCommission> list = sysCommissionService.selectSysCommissionList(sysCommission);
        ExcelUtil<SysCommission> util = new ExcelUtil<SysCommission>(SysCommission.class);
        return util.exportExcel(list, "commission");
    }

    /**
     * 新增提成明细
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("posts", postService.selectPostAll());
        return prefix + "/add";
    }

    /**
     * 新增保存提成明细
     */
    @RequiresPermissions("system:commission:addSave")
    @Log(title = "提成明细", businessType = BusinessType.INSERT)
    @PostMapping("/addSave")
    @ResponseBody
    public AjaxResult addSave(SysCommission sysCommission) {
        AjaxResult ajax = new AjaxResult();
        sysCommission.setCreateBy(ShiroUtils.getLoginName());
        int res = sysCommissionService.insertSysCommission(sysCommission);
        ajax.put("sysCommission", sysCommission);
        return ajax;
    }

    /**
     * 修改提成明细
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        SysCommission sysCommission = sysCommissionService.selectSysCommissionById(id);
        mmap.put("sysCommission", sysCommission);
        return prefix + "/edit";
    }

    /**
     * 修改保存提成明细
     */
    @RequiresPermissions("system:commission:edit")
    @Log(title = "提成明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCommission sysCommission) {
        return toAjax(sysCommissionService.updateSysCommission(sysCommission));
    }

    /**
     * 删除提成明细
     */
    @RequiresPermissions("system:commission:remove")
    @Log(title = "提成明细", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysCommissionService.deleteSysCommissionByIds(ids));
    }
}
