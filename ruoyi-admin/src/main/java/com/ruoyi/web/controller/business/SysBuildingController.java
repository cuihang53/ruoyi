package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.CodeFactoryUtil;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysBuildingService;
import com.ruoyi.service.ISysHouseUnitService;
import com.ruoyi.system.domain.SysBuilding;
import com.ruoyi.system.domain.SysHouseUnit;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 楼盘基础信息Controller
 *
 * @author ruoyi
 * @date 2020-05-09
 */
@Controller
@RequestMapping("/system/building")
public class SysBuildingController extends BaseController {
    private String prefix = "system/building";

    @Autowired
    private ISysBuildingService sysBuildingService;

    @Autowired
    private ISysHouseUnitService sysHouseUnitService;

    @RequiresPermissions("system:building:view")
    @GetMapping()
    public String building() {
        return prefix + "/building";
    }

    /**
     * 查询楼盘基础信息列表
     */
    @RequiresPermissions("system:building:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBuilding sysBuilding) {
        startPage();
        List<SysBuilding> list = sysBuildingService.selectSysBuildingList(sysBuilding);
        return getDataTable(list);
    }


    /**
     * 导出楼盘基础信息列表
     */
    @RequiresPermissions("system:building:export")
    @Log(title = "楼盘基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBuilding sysBuilding) {
        List<SysBuilding> list = sysBuildingService.selectSysBuildingList(sysBuilding);
        ExcelUtil<SysBuilding> util = new ExcelUtil<SysBuilding>(SysBuilding.class);
        return util.exportExcel(list, "building");
    }

    /**
     * 新增楼盘基础信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("buildingCode", CodeFactoryUtil.getBuldingCode(ShiroUtils.getUserId()));
        return prefix + "/add";
    }

    /**
     * 新增保存楼盘基础信息
     */
    @RequiresPermissions("system:building:add")
    @Log(title = "楼盘基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBuilding sysBuilding) {
        List<SysBuilding> buildingList = sysBuildingService.isExistBuildingName(sysBuilding);
        AjaxResult ajax = new AjaxResult();
        if (buildingList != null && buildingList.size() > 0) {
            ajax.put("msg", "该楼盘已录入");
            return ajax;
        } else {
            sysBuilding.setCreateBy(ShiroUtils.getLoginName());
            return toAjax(sysBuildingService.insertSysBuilding(sysBuilding));
        }
    }

    /**
     * 导入模板下载
     */
    @RequiresPermissions("system:building:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() throws Exception {
        ExcelUtil<SysBuilding> util = new ExcelUtil<>(SysBuilding.class);
        return util.importTemplateExcel("SysHouseTemplate");
    }

    @Log(title = "楼盘管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:building:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysBuilding> util = new ExcelUtil<SysBuilding>(SysBuilding.class);
        List<SysBuilding> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = sysBuildingService.importBuilding(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 修改楼盘基础信息
     */
    @GetMapping("/edit/{buildingId}")
    public String edit(@PathVariable("buildingId") Long buildingId, ModelMap mmap) {
        SysBuilding sysBuilding = sysBuildingService.selectSysBuildingById(buildingId);
        mmap.put("sysBuilding", sysBuilding);
        return prefix + "/edit";
    }

    /**
     * 修改保存楼盘基础信息
     */
    @RequiresPermissions("system:building:edit")
    @Log(title = "楼盘基础信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBuilding sysBuilding) {
        return toAjax(sysBuildingService.updateSysBuilding(sysBuilding));
    }

    /**
     * 删除楼盘基础信息
     */
    @RequiresPermissions("system:building:remove")
    @Log(title = "楼盘基础信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        String errMsg = "";
        try {
            for(String id:ids.split(",")){
                SysHouseUnit unit  =  new  SysHouseUnit();
                unit.setBuildingId(id);
                List<SysHouseUnit> units =  sysHouseUnitService.selectSysHouseUnitList(unit);
                if(units!=null && units.size()>0){
                    errMsg = errMsg+"<br>"+units.get(0).getBulding().getBuildingName()+"已经被使用，不能删除";
                    return error(errMsg);
                }
            }
            return toAjax(sysBuildingService.deleteSysBuildingByIds(ids));
        } catch (Exception e) {
            return error(e.getMessage());
        }
    }

    ;
}
