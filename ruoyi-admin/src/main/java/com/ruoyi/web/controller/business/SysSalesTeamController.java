package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.service.ISysSalesTeamPersonService;
import com.ruoyi.service.ISysSalesTeamService;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysSalesTeam;
import com.ruoyi.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 销售小组管理Controller
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Controller
@RequestMapping("/system/salesTeam")
public class SysSalesTeamController extends BaseController
{
    private String prefix = "system/salesTeam";

    @Autowired
    private ISysSalesTeamService sysSalesTeamService;


    @Autowired
    private ISysSalesTeamPersonService salesTeamPersonService;


    @Autowired
    private ISysPostService postService;

    @RequiresPermissions("system:salesTeam:view")
    @GetMapping()
    public String salesTeam()
    {
        return prefix + "/salesTeam";
    }

    /**
     * 查询销售小组管理列表
     */
    @RequiresPermissions("system:salesTeam:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSalesTeam sysSalesTeam)
    {
        startPage();
        List<SysSalesTeam> list = sysSalesTeamService.selectSysSalesTeamList(sysSalesTeam);
        return getDataTable(list);
    }

    /**
     * 查询小组成员信息
     */
    @RequiresPermissions("system:salesTeam:list")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        mmap.put("teamPersons",salesTeamPersonService.selectByTeamId(Long.parseLong(id)) );
        mmap.put("salesTeam", sysSalesTeamService.selectSysSalesTeamById(Long.parseLong(id)));
        return "/system/teamperson/teamperson";
    }

    /**
     * 导出销售小组管理列表
     */
    @RequiresPermissions("system:salesTeam:export")
    @Log(title = "销售小组管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysSalesTeam sysSalesTeam)
    {
        List<SysSalesTeam> list = sysSalesTeamService.selectSysSalesTeamList(sysSalesTeam);
        ExcelUtil<SysSalesTeam> util = new ExcelUtil<SysSalesTeam>(SysSalesTeam.class);
        return util.exportExcel(list, "salesTeam");
    }

    /**
     * 新增销售小组管理
     */
    @GetMapping("/add")
    public String add()
    {

        return prefix + "/add";
    }

    /**
     * 新增保存销售小组管理
     */
    @RequiresPermissions("system:salesTeam:add")
    @Log(title = "销售小组管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysSalesTeam sysSalesTeam)
    {
        return toAjax(sysSalesTeamService.insertSysSalesTeam(sysSalesTeam));
    }

    /**
     * 修改销售小组管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysSalesTeam sysSalesTeam = sysSalesTeamService.selectSysSalesTeamById(id);
        mmap.put("sysSalesTeam", sysSalesTeam);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售小组管理
     */
    @RequiresPermissions("system:salesTeam:edit")
    @Log(title = "销售小组管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysSalesTeam sysSalesTeam)
    {
        return toAjax(sysSalesTeamService.updateSysSalesTeam(sysSalesTeam));
    }

    /**
     * 删除销售小组管理
     */
    @RequiresPermissions("system:salesTeam:remove")
    @Log(title = "销售小组管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysSalesTeamService.deleteSysSalesTeamByIds(ids));
    }



    /**
     * 岗位信息获取
     */
    @RequiresPermissions("system:salesTeam:getUerPost")
    @PostMapping("/getUerPost")
    @ResponseBody
    public AjaxResult getUerPost(Long userId)
    {
        AjaxResult ajax = new AjaxResult();
        List<SysPost> posts =  postService.selectPostsByUserId(userId);
        ajax.put("posts",posts);
        return  ajax;
    }


}
