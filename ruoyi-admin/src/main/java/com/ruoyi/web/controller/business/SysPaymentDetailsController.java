package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.service.ISysPaymentDetailsService;
import com.ruoyi.system.domain.SysPaymentDetails;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收款明细Controller
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Controller
@RequestMapping("/system/details")
public class SysPaymentDetailsController extends BaseController
{
    private String prefix = "system/details";

    @Autowired
    private ISysPaymentDetailsService sysPaymentDetailsService;

    @RequiresPermissions("system:details:view")
    @GetMapping()
    public String details()
    {
        return prefix + "/details";
    }

    /**
     * 查询收款明细列表
     */
    @RequiresPermissions("system:details:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysPaymentDetails sysPaymentDetails)
    {
        startPage();
        List<SysPaymentDetails> list = sysPaymentDetailsService.selectSysPaymentDetailsList(sysPaymentDetails);
        return getDataTable(list);
    }

    /**
     * 导出收款明细列表
     */
    @RequiresPermissions("system:details:export")
    @Log(title = "收款明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysPaymentDetails sysPaymentDetails)
    {
        List<SysPaymentDetails> list = sysPaymentDetailsService.selectSysPaymentDetailsList(sysPaymentDetails);
        ExcelUtil<SysPaymentDetails> util = new ExcelUtil<SysPaymentDetails>(SysPaymentDetails.class);
        return util.exportExcel(list, "details");
    }

    /**
     * 新增收款明细
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存收款明细
     */
    @RequiresPermissions("system:details:add")
    @Log(title = "收款明细", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysPaymentDetails sysPaymentDetails)
    {
        AjaxResult ajax = new AjaxResult();
        int res = sysPaymentDetailsService.insertSysPaymentDetails(sysPaymentDetails);
        ajax.put("sysPaymentDetails", sysPaymentDetails);
        return ajax;
    }

    /**
     * 修改收款明细
     */
    @GetMapping("/edit/{paymentId}")
    public String edit(@PathVariable("paymentId") Long paymentId, ModelMap mmap)
    {
        SysPaymentDetails sysPaymentDetails = sysPaymentDetailsService.selectSysPaymentDetailsById(paymentId);
        mmap.put("sysPaymentDetails", sysPaymentDetails);
        return prefix + "/edit";
    }

    /**
     * 修改保存收款明细
     */
    @RequiresPermissions("system:details:edit")
    @Log(title = "收款明细", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysPaymentDetails sysPaymentDetails)
    {
        return toAjax(sysPaymentDetailsService.updateSysPaymentDetails(sysPaymentDetails));
    }

    /**
     * 根据销售详细表id查询提成明细列表
     */
    @RequiresPermissions("system:details:getListBySaleID")
    @PostMapping("/getListBySaleID")
    @ResponseBody
    public AjaxResult getListBySaleID(String delId) {
        AjaxResult ajax = new AjaxResult();
        SysPaymentDetails details = new SysPaymentDetails();
        details.setSalesDetailId(delId);
        List<SysPaymentDetails> detailsList = sysPaymentDetailsService.selectSysPaymentDetailsList(details);
        ajax.put("detailsList", detailsList);
        return ajax;
    }

    /**
     * 删除收款明细
     */
    @RequiresPermissions("system:details:remove")
    @Log(title = "收款明细", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysPaymentDetailsService.deleteSysPaymentDetailsByIds(ids));
    }
}
