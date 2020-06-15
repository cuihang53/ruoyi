package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelTemplate;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysBuildingService;
import com.ruoyi.service.ISysHouseService;
import com.ruoyi.service.ISysHouseUnitService;
import com.ruoyi.system.domain.SysBuilding;
import com.ruoyi.system.domain.SysHouse;
import com.ruoyi.system.domain.SysHouseUnit;
import com.ruoyi.system.service.ISysDictTypeService;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * 楼房基础信息Controller
 *
 * @author ruoyi
 * @date 2020-05-14
 */
@Controller
@RequestMapping("/system/house")
public class SysHouseController extends BaseController {
    private String prefix = "system/house";

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;


    @Autowired
    private ISysHouseService sysHouseService;

    @Autowired
    private ISysBuildingService sysBuildingService;

    @Autowired
    private ISysHouseUnitService sysHouseUnitService;

    @Autowired
    private ISysDictTypeService dictTypeService;

    @RequiresPermissions("system:house:view")
    @GetMapping()
    public String house() {
        return prefix + "/house";
    }

    /**
     * 查询楼房基础信息列表
     */
    @RequiresPermissions("system:house:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysHouse sysHouse) {
        startPage();
        List<SysHouse> houselist = sysHouseService.selectSysHouseList(sysHouse);
        return getDataTable(houselist);
    }
    /**
     * 导出楼房基础信息列表
     */
    @RequiresPermissions("system:house:export")
    @Log(title = "楼房基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysHouse sysHouse) {
        List<SysHouse> list = sysHouseService.selectSysHouseList(sysHouse);
        ExcelUtil<SysHouse> util = new ExcelUtil<SysHouse>(SysHouse.class);
        return util.exportExcel(list, "house");
    }

    /**
     * 导入模板下载
     */
    @RequiresPermissions("system:house:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate() throws Exception {


        List<SysBuilding> list =  sysBuildingService.selectSysBuildingAll();

        String[] buldingStr = null;
        if (list != null && list.size() > 0) {
            buldingStr = new String[list.size()];
            int i = 0;
            for (SysBuilding building : list) {
                    buldingStr[i] = building.getBuildingName();
                    i++;
            }
        }
        ExcelTemplate<SysHouse> util = new ExcelTemplate<>(SysHouse.class);
        return util.importTemplateExcel("houseTemplate",buldingStr,1,10000);
    }


    @Log(title = "楼房管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:house:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysHouse> util = new ExcelUtil<SysHouse>(SysHouse.class);
        List<SysHouse> houseList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = sysHouseService.importData(houseList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 新增楼房基础信息
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<SysBuilding> sysBuildings = sysBuildingService.selectSysBuildingAll();
        mmap.put("buldings",sysBuildingService.selectSysBuildingAll());
        if(sysBuildings!=null && sysBuildings.size()>0){
            SysHouseUnit sysHouseUnit = new SysHouseUnit();
            sysHouseUnit.setBuildingId(sysBuildings.get(0).getBuildingId().toString());
            List<SysHouseUnit>  sysHouseUnits = sysHouseUnitService.selectSysHouseUnitList(sysHouseUnit);
            mmap.put("sysHouseUnits",sysHouseUnits);
        }
        return prefix + "/add";
    }

    /**
     * 新增保存楼房基础信息
     */
    @RequiresPermissions("system:house:add")
    @Log(title = "楼房基础信息", businessType = BusinessType.INSERT)


    
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysHouse sysHouse) {
        List<SysHouse> houses =  sysHouseService.isExistHouseNum(sysHouse);
        if(houses!=null && houses.size()>0){
            return error("新增失败,"+sysHouse.getBulding().getBuildingName() + " " +sysHouse.getHouseUnit().getHouseUnitName()+
                    " "+sysHouse.getHouseNumber() +"已存在");
        }
        sysHouse.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(sysHouseService.insertSysHouse(sysHouse));
    }


    /**
     * 根据单元数据获取下面所有房号数据
     */
    @RequiresPermissions("system:house:selectHouseByUnit")
    @Log(title = "楼房基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/selectHouseByUnit")
    @ResponseBody
    public AjaxResult selectHouseByUnit(Long unitId) {
        AjaxResult ajax =  new AjaxResult();
        SysHouse house = new SysHouse();
        house.setHouseUtilId(unitId.toString());
        List<SysHouse>  houseList =  sysHouseService.selectSysHouseList(house);
        ajax.put("posts",houseList);
        return  ajax;
    }

    /**
     * 根据单元数据获取下面所有房号数据
     */
    @RequiresPermissions("system:house:selectById")
    @Log(title = "楼房基础信息", businessType = BusinessType.INSERT)
    @PostMapping("/selectById")
    @ResponseBody
    public AjaxResult selectById(Long houseId) {
        AjaxResult ajax =  new AjaxResult();
        SysHouse house =  sysHouseService.selectSysHouseById(houseId);
        ajax.put("posts",house);
        return  ajax;
    }

    /**
     * 修改楼房基础信息
     */
    @GetMapping("/edit/{houseId}")
    public String edit(@PathVariable("houseId") Long houseId, ModelMap mmap) {
        SysHouse sysHouse = sysHouseService.selectSysHouseById(houseId);
        List<SysBuilding> buildingList = sysBuildingService.selectSysBuildingAll();
        for(SysBuilding building: buildingList){
            if(Long.parseLong(sysHouse.getBuldingId())== building.getBuildingId()){
                building.setFlag(true);
            }
        }
        SysHouseUnit houseUnit = new SysHouseUnit();
        houseUnit.setBuildingId(sysHouse.getBuldingId());
        List<SysHouseUnit>  houseUnits =  sysHouseUnitService.selectSysHouseUnitList(houseUnit);
        for(SysHouseUnit unit:houseUnits){
            if(Long.parseLong(sysHouse.getHouseUtilId()) == unit.getHouseUnitId()){
                unit.setFlag(true);
            }
        }
        mmap.put("sysHouse", sysHouse);
        mmap.put("buldings",buildingList);
        mmap.put("houseUnits",houseUnits);
        return prefix + "/edit";
    }

    /**
     * 修改保存楼房基础信息
     */
    @RequiresPermissions("system:house:edit")
    @Log(title = "楼房基础信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysHouse sysHouse) {
        sysHouse.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(sysHouseService.updateSysHouse(sysHouse));
    }

    /**
     * 删除楼房基础信息
     */
    @RequiresPermissions("system:house:remove")
    @Log(title = "楼房基础信息", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysHouseService.deleteSysHouseByIds(ids));
    }
}
