package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.CodeFactoryUtil;
import com.ruoyi.common.utils.poi.ExcelTemplate;
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
 * 楼盘单元楼号中间Controller
 *
 * @author ruoyi
 * @date 2020-05-16
 */
@Controller
@RequestMapping("/system/unit")
public class SysHouseUnitController extends BaseController {
    private String prefix = "system/unit";

    @Autowired
    private ISysHouseUnitService sysHouseUnitService;

    @Autowired
    private ISysBuildingService sysBuildingService;



    @RequiresPermissions("system:unit:view")
    @GetMapping()
    public String unit() {
        return prefix + "/unit";
    }

    /**
     * 查询楼盘单元楼号中间列表
     */
    @RequiresPermissions("system:unit:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysHouseUnit sysHouseUnit) {
        startPage();
        List<SysHouseUnit> list = sysHouseUnitService.selectSysHouseUnitList(sysHouseUnit);
        return getDataTable(list);
    }

    /**
     * 根据楼盘id查询对应单元和楼号信息
     */
    @RequiresPermissions("system:unit:selectHouseUtil")
    @Log(title = "根据楼盘id查询对应单元和楼号信息", businessType = BusinessType.EXPORT)
    @PostMapping("/selectHouseUtil")
    @ResponseBody
    public AjaxResult selectHouseUtil(String buldingId) {
        AjaxResult ajax = new AjaxResult();
        SysHouseUnit sysHouseUnit = new SysHouseUnit();
        sysHouseUnit.setBuildingId(buldingId);
        List<SysHouseUnit> sysHouseUnits = sysHouseUnitService.selectSysHouseUnitList(sysHouseUnit);
        ajax.put("posts", sysHouseUnits);
        return ajax;
    }

    /**
     * 导入模板下载
     */
    @RequiresPermissions("system:unit:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() throws Exception {
        List<SysBuilding> buildingList = sysBuildingService.selectSysBuildingAll();
        String[] buldingStr = null;
        if (buildingList != null && buildingList.size() > 0) {
            buldingStr = new String[buildingList.size()];
            int i = 0;
            for (SysBuilding building : buildingList) {
                buldingStr[i] = building.getBuildingName();
                i++;
            }
        }
        ExcelTemplate<SysHouseUnit> util = new ExcelTemplate<>(SysHouseUnit.class);
        return util.importTemplateExcel("houseTemplate", buldingStr, 1, 10000);
    }


    @Log(title = "楼盘单元管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:unit:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception {
        ExcelUtil<SysHouseUnit> util = new ExcelUtil<SysHouseUnit>(SysHouseUnit.class);
        List<SysHouseUnit> sysHouseUnitList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = sysHouseUnitService.importHouseUnit(sysHouseUnitList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 导出楼盘单元楼号中间列表
     */
    @RequiresPermissions("system:unit:export")
    @Log(title = "楼盘单元楼号中间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysHouseUnit sysHouseUnit) {
        List<SysHouseUnit> list = sysHouseUnitService.selectSysHouseUnitList(sysHouseUnit);
        ExcelUtil<SysHouseUnit> util = new ExcelUtil<SysHouseUnit>(SysHouseUnit.class);
        return util.exportExcel(list, "unit");
    }

    /**
     * 新增楼盘单元楼号中间
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("buldings", sysBuildingService.selectSysBuildingAll());
        return prefix + "/add";
    }

    /**
     * 新增保存楼盘单元楼号中间
     */
    @RequiresPermissions("system:unit:add")
    @Log(title = "楼盘单元楼号中间", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysHouseUnit sysHouseUnit) {
        sysHouseUnit.setCreateBy(ShiroUtils.getLoginName());
        sysHouseUnit.setUnitCode(CodeFactoryUtil.getHouseUnitCode(ShiroUtils.getUserId()));
        return toAjax(sysHouseUnitService.insertSysHouseUnit(sysHouseUnit));
    }

    /**
     * 修改楼盘单元楼号中间
     */
    @GetMapping("/edit/{houseUnitId}")
    public String edit(@PathVariable("houseUnitId") String houseUnitId, ModelMap mmap) {
        List<SysBuilding> buildingList =  sysBuildingService.selectSysBuildingAll();
        SysHouseUnit sysHouseUnit = sysHouseUnitService.selectSysHouseUnitById(Long.parseLong(houseUnitId));
        for(SysBuilding building:  buildingList){
            if(building.getBuildingId() == Long.parseLong(sysHouseUnit.getBuildingId())){
                building.setFlag(true);
            }
        }
        mmap.put("buldings", buildingList);
        mmap.put("sysHouseUnit", sysHouseUnit);
        return prefix + "/edit";
    }

    /**
     * 修改保存楼盘单元楼号中间
     */
    @RequiresPermissions("system:unit:edit")
    @Log(title = "楼盘单元楼号中间", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysHouseUnit sysHouseUnit, ModelMap mmap) {
        mmap.put("buldings", sysBuildingService.selectSysBuildingAll());
        return toAjax(sysHouseUnitService.updateSysHouseUnit(sysHouseUnit));
    }

    /**
     * 删除楼盘单元楼号中间
     */
    @RequiresPermissions("system:unit:remove")
    @Log(title = "楼盘单元楼号中间", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysHouseUnitService.deleteSysHouseUnitByIds(ids));
    }
}
