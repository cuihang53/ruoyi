package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.service.ISysSalesTeamPersonService;
import com.ruoyi.service.ISysSalesTeamService;
import com.ruoyi.system.domain.SysSalesTeamPerson;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售小组成员信息Controller
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Controller
@RequestMapping("/system/teamperson")
public class SysSalesTeamPersonController extends BaseController
{
    private String prefix = "system/teamperson";

    @Autowired
    private ISysSalesTeamPersonService sysSalesTeampersonService;

    @Autowired
    private ISysSalesTeamService sysSalesTeamService;

    @RequiresPermissions("system:teamperson:view")
    @GetMapping()
    public String teamperson()
    {
        return prefix + "/teamperson";
    }

    /**
     * 查询销售小组成员信息列表
     */
    @RequiresPermissions("system:teamperson:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSalesTeamPerson sysSalesTeamperson)
    {
        startPage();
        List<SysSalesTeamPerson> list = sysSalesTeampersonService.selectSysSalesTeampersonList(sysSalesTeamperson);
        return getDataTable(list);
    }

    /**
     * 导出销售小组成员信息列表
     */
    @RequiresPermissions("system:teamperson:export")
    @Log(title = "销售小组成员信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSalesTeamPerson sysSalesTeamperson)
    {
        List<SysSalesTeamPerson> list = sysSalesTeampersonService.selectSysSalesTeampersonList(sysSalesTeamperson);
        ExcelUtil<SysSalesTeamPerson> util = new ExcelUtil<SysSalesTeamPerson>(SysSalesTeamPerson.class);
        return util.exportExcel(list, "teamperson");
    }

    /**
     * 新增销售小组成员信息
     */
    @GetMapping("/add/{teamId}")
    public String add(@PathVariable("teamId") String teamId, ModelMap mmap)
    {
        mmap.put("team",sysSalesTeamService.selectSysSalesTeamById(Long.parseLong(teamId)));
        return prefix + "/add";
    }

    /**
     * 新增保存销售小组成员信息
     */
    @RequiresPermissions("system:teamperson:add")
    @Log(title = "销售小组成员信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysSalesTeamPerson sysSalesTeamperson)
    {
        return toAjax(sysSalesTeampersonService.insertSysSalesTeamperson(sysSalesTeamperson));
    }

    /**
     * 修改销售小组成员信息
     */
    @GetMapping("/edit/{salesId}")
    public String edit(@PathVariable("salesId") Long salesId, ModelMap mmap)
    {
        SysSalesTeamPerson sysSalesTeamperson = sysSalesTeampersonService.selectSysSalesTeampersonById(salesId);
        mmap.put("sysSalesTeamperson", sysSalesTeamperson);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售小组成员信息
     */
    @RequiresPermissions("system:teamperson:edit")
    @Log(title = "销售小组成员信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysSalesTeamPerson sysSalesTeamperson)
    {
        return toAjax(sysSalesTeampersonService.updateSysSalesTeamperson(sysSalesTeamperson));
    }

    /**
     * 删除销售小组成员信息
     */
    @RequiresPermissions("system:teamperson:remove")
    @Log(title = "销售小组成员信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysSalesTeampersonService.deleteSysSalesTeampersonByIds(ids));
    }
}
