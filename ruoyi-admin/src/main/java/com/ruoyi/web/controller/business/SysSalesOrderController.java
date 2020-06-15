package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.*;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.ISysPostService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 销售信息录入Controller
 *
 * @author ruoyi
 * @date 2020-05-16
 */
@Controller
@RequestMapping("/system/detail")
public class SysSalesOrderController extends BaseController {
    private String prefix = "system/detail";

    @Autowired
    private ISysSalesOrderService sysSalesDetailService;


    @Autowired
    private ISysFlowinfoMovingService sysFlowinfoMovingService;

    @Autowired
    private ISysBuildingService sysBuildingService;

    @Autowired
    private ISysHouseUnitService houseUnitService;

    @Autowired
    private ISysPaymentDetailsService sysPaymentDetailsService;

    @Autowired
    private ISysCommissionService sysCommissionService;


    @Autowired
    private ISysFileUtilService sysFileUtilService;


    @Autowired
    private ISysSoParticularsService soParticularsService;

    @Autowired
    private ISysFileRecordService recordService;
    ;

    @Autowired
    private ISysFlowService flowService;

    @Autowired
    private ISysFlowinfoMovingService movingService;


    @Autowired
    private ISysHouseService sysHouseService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private ISysSoParticularsService particularsService;

    @RequiresPermissions("system:detail:view")
    @GetMapping()
    public String detail() {
        return prefix + "/detail";
    }

    /**
     * 查询销售信息录入列表
     */
    @RequiresPermissions("system:detail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSalesOrder sysSalesDetail, ModelMap mmap) {
        mmap.put("buldings", sysBuildingService.selectSysBuildingAll());
        mmap.put("user", ShiroUtils.getSysUser());
        startPage();
        List<SysSalesOrder> list = sysSalesDetailService.selectSysSalesDetailList(sysSalesDetail);
        return getDataTable(list);
    }


    /**
     * 导出销售信息录入列表
     */
    @RequiresPermissions("system:detail:downFile")
    @Log(title = "销售信息录入", businessType = BusinessType.EXPORT)
    @PostMapping("/downFile")
    @ResponseBody
    public AjaxResult downFile(SysSalesOrder sysSalesDetail) {
        List<SysSalesOrder> list = sysSalesDetailService.selectSysSalesDetailList(sysSalesDetail);

        ExcelUtil<SysSalesOrder> util = new ExcelUtil<SysSalesOrder>(SysSalesOrder.class);

        return util.exportExcel(list, "detail");
    }


    /**
     * 导出销售信息录入列表
     */
    @GetMapping("/export")
    public String export(SysSalesOrder sysSalesDetail) {

        return prefix + "/export";
    }

    /**
     * 导出销售信息录入列表
     */
    @RequiresPermissions("system:detail:exportSure")
    @PostMapping("/exportSure")
    @ResponseBody
    public AjaxResult exportSure(String checkedNum, SysSalesOrder salesOrder, HttpServletRequest request, HttpServletResponse response) {

        return sysSalesDetailService.importExcel(checkedNum, salesOrder, request, response);
    }

    /**
     * 新增销售信息录入
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<SysBuilding> buldings = sysBuildingService.selectSysBuildingAll();
        if (buldings != null && buldings.size() > 0) {
            SysHouseUnit unit = new SysHouseUnit();
            unit.setBuildingId(buldings.get(0).getBuildingId().toString());
            List<SysHouseUnit> units = houseUnitService.selectSysHouseUnitList(unit);
            mmap.put("units", units);
            if (units != null && units.size() > 0) {
                SysHouse house = new SysHouse();
                house.setHouseUtilId(units.get(0).getHouseUnitId().toString());
                List<SysHouse> houses = sysHouseService.selectSysHouseList(house);
                mmap.put("houses", houses);
            }
        }
        mmap.put("posts", postService.selectPostAll());
        mmap.put("buldings", buldings);
        return prefix + "/add";
    }


    /**
     * 本地资源通用下载
     */
    @GetMapping("/detail/download/resourceDownload/")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 本地资源路径
        String localPath = Global.getProfile();
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(resource, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(resource, response.getOutputStream());
    }

    /**
     * 所在楼盘所有房号获取
     */
    @RequiresPermissions("system:detail:selectHouses")
    @PostMapping("/selectHouses")
    @ResponseBody
    public AjaxResult selectHouses(String buldingId) {
        AjaxResult ajax = new AjaxResult();
        SysHouse house = new SysHouse();
        List<SysHouse> buildingList = sysHouseService.selectSysHouses(house);
        ajax.put("posts", buildingList);
        return ajax;
    }

    /**
     * 新增保存销售信息录入
     */
    @RequiresPermissions("system:detail:add")
    @Log(title = "销售信息录入", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysSalesOrder sysSalesDetail) {

        AjaxResult ajax = new AjaxResult();
        sysSalesDetail.setCreateBy(ShiroUtils.getUserId().toString());
        sysSalesDetail.setCollection("0");
        if (StringUtils.isNotEmpty(sysSalesDetail.getSurplusPrice()) && Double.parseDouble(sysSalesDetail.getSurplusPrice()) == 0) {
            sysSalesDetail.setPriceEndTime(DateUtils.getNowDate());
        }
        sysSalesDetailService.insertSysSalesDetail(sysSalesDetail);
        ajax.put("sysSalesDetail", sysSalesDetail);
        //处理提成明细
        if (sysSalesDetail.getCids() != null && sysSalesDetail.getCids().length() > 0) {
            String[] strs = sysSalesDetail.getCids().split(",");
            for (String id : strs) {
                SysCommission commission = sysCommissionService.selectSysCommissionById(Long.parseLong(id));
                commission.setSalesDetailId(sysSalesDetail.getSalesDetailId());
                commission.setUpdateBy(ShiroUtils.getLoginName());
                sysCommissionService.updateSysCommission(commission);
            }
        }
        //处理付款明细
        if (sysSalesDetail.getPayids() != null && sysSalesDetail.getPayids().length() > 0) {
            String[] strs = sysSalesDetail.getPayids().split(",");
            for (String id : strs) {
                SysPaymentDetails paymentDetails = sysPaymentDetailsService.selectSysPaymentDetailsById(Long.parseLong(id));
                paymentDetails.setSalesDetailId(sysSalesDetail.getSalesDetailId().toString());
                paymentDetails.setUpdateBy(ShiroUtils.getLoginName());
                sysPaymentDetailsService.updateSysPaymentDetails(paymentDetails);
            }
        }
        //处理附件
        if (sysSalesDetail.getFileUtilIds() != null && sysSalesDetail.getFileUtilIds().length() > 0) {
            String[] strs = sysSalesDetail.getFileUtilIds().split(",");
            for (String id : strs) {
                SysFileUtil fileUtil = sysFileUtilService.selectSysFileUtilById(Long.parseLong(id));
                fileUtil.setFileDomainId(sysSalesDetail.getSalesDetailId());
                fileUtil.setUpdateBy(ShiroUtils.getLoginName());
                sysFileUtilService.updateSysFileUtil(fileUtil);
            }
        }
        //处理进度明细
        if (sysSalesDetail.getSoParticularsIds() != null && sysSalesDetail.getSoParticularsIds().length() > 0) {
            String[] strs = sysSalesDetail.getSoParticularsIds().split(",");
            for (String id : strs) {
                SysSoParticulars soParticulars = soParticularsService.selectSysSoParticularsById(Long.parseLong(id));
                soParticulars.setSalesDetailId(sysSalesDetail.getSalesDetailId().toString());
                soParticulars.setUpdateBy(ShiroUtils.getLoginName());
                soParticularsService.updateSysSoParticulars(soParticulars);
            }
        }
        //处理资料领取明细
        if (sysSalesDetail.getRecordids() != null && sysSalesDetail.getRecordids().length() > 0) {
            String[] strs = sysSalesDetail.getRecordids().split(",");
            for (String id : strs) {
                SysFileRecord record = recordService.selectSysFileRecordById(Long.parseLong(id));
                record.setSaleOrderId(sysSalesDetail.getSalesDetailId());
                record.setUpdateBy(ShiroUtils.getLoginName());
                recordService.updateSysFileRecord(record);
            }
        }
        return ajax;
    }

    /**
     * 修改销售信息录入
     */
    @GetMapping("/edit/{salesDetailId}")
    public String edit(@PathVariable("salesDetailId") Long salesDetailId, ModelMap mmap) {
        SysSalesOrder sysSalesDetail = sysSalesDetailService.selectSysSalesDetailById(salesDetailId);
        List<SysBuilding> buldings = sysBuildingService.selectSysBuildingAll();
        for (SysBuilding building : buldings) {
            if (building.getBuildingId() == sysSalesDetail.getBuldingid()) {
                building.setFlag(true);
            }
        }
        SysHouseUnit unit = new SysHouseUnit();
        unit.setBuildingId(sysSalesDetail.getBuldingid().toString());
        List<SysHouseUnit> units = houseUnitService.selectSysHouseUnitList(unit);
        for (SysHouseUnit houseUnit : units) {
            if (houseUnit.getHouseUnitId() == Long.parseLong(sysSalesDetail.getHouse().getHouseUtilId())) {
                houseUnit.setFlag(true);
            }
        }
        SysHouse house = new SysHouse();
        house.setHouseUtilId(sysSalesDetail.getHouse().getHouseUtilId());
        List<SysHouse> houses = sysHouseService.selectSysHouseList(house);
        for (SysHouse sysHouse : houses) {
            if (sysHouse.getHouseId() == sysSalesDetail.getHouseid()) {
                sysHouse.setFlag(true);
            }
        }
        SysFileUtil fileUtil = new SysFileUtil();
        fileUtil.setFileDomainId(sysSalesDetail.getSalesDetailId());
        List<SysFileUtil> fileUtils = sysFileUtilService.selectSysFileUtilList(fileUtil);

        SysSoParticulars po = new SysSoParticulars();
        po.setSalesDetailId(sysSalesDetail.getSalesDetailId().toString());
        List<SysSoParticulars> particulars = particularsService.selectSysSoParticularsList(po);

        SysFileRecord record = new SysFileRecord();
        record.setSaleOrderId(sysSalesDetail.getSalesDetailId());
        List<SysFileRecord> recordList = recordService.selectSysFileRecordList(record);

        SysFlow  flow = flowService.selectSysFlowByTypeId(sysSalesDetail.getSalesDetailId());
        List<SysFlowinfoMoving>  movingList =  movingService.selectByFlowId(flow.getId());
        SysFlowinfoMoving flowinfoMoving =  movingService.selectSysLastNumber(movingList.get(0));
        mmap.put("sysSalesDetail", sysSalesDetail);
        mmap.put("buldings", buldings);
        mmap.put("units", units);
        mmap.put("houses", houses);
        mmap.put("fileUtils", fileUtils);
        mmap.put("particulars", particulars);
        mmap.put("recordList", recordList);
        mmap.put("movingList", movingList);
        mmap.put("flowinfoMoving",flowinfoMoving);
        return prefix + "/edit";
    }


    /**
     * 修改销售信息录入
     */
    @RequiresPermissions("system:detail:view")
    @GetMapping("/viewDetail/{id}")
    public String viewDetail(@PathVariable("id") Long flowinfoMovingId, ModelMap mmap) {

        SysFlowinfoMoving sysFlowinfoMoving = sysFlowinfoMovingService.selectSysFlowinfoMovingById(flowinfoMovingId);

        SysSalesOrder sysSalesDetail = sysSalesDetailService.selectSysSalesDetailById(sysFlowinfoMoving.getFlow().getTypeId());

        //查询收款明细信息
        List<SysPaymentDetails> paymentDetails = sysPaymentDetailsService.selectPaymentByOrderId(sysSalesDetail.getSalesDetailId().toString());

        //查询提成明细
        List<SysCommission> commissions = sysCommissionService.selectByorderId(sysSalesDetail.getSalesDetailId());

        //查询关联附件
        SysFileUtil fileUtil = new SysFileUtil();
        fileUtil.setFileDomainId(sysSalesDetail.getSalesDetailId());
        List<SysFileUtil> fileUtils = sysFileUtilService.selectSysFileUtilList(fileUtil);

        //查询销售单进度明细
        SysSoParticulars po = new SysSoParticulars();
        po.setSalesDetailId(sysSalesDetail.getSalesDetailId().toString());
        List<SysSoParticulars> particulars = particularsService.selectSysSoParticularsList(po);

        //查询销售单资料领导记录明细
        SysFileRecord record = new SysFileRecord();
        record.setSaleOrderId(sysSalesDetail.getSalesDetailId());
        List<SysFileRecord> recordList = recordService.selectSysFileRecordList(record);

        //查询该流程流转明细
        SysFlow flow = flowService.selectSysFlowByTypeId(sysSalesDetail.getSalesDetailId());

        mmap.put("sysSalesDetail", sysSalesDetail);
        mmap.put("commissions", commissions);
        mmap.put("paymentDetails", paymentDetails);
        mmap.put("fileUtils", fileUtils);
        mmap.put("particulars", particulars);
        mmap.put("recordList", recordList);
        mmap.put("flow", flow);
        return prefix + "/view";
    }


    /**
     * 修改保存销售信息录入
     */
    @RequiresPermissions("system:detail:editSave")
    @Log(title = "销售信息录入", businessType = BusinessType.UPDATE)
    @PostMapping("/editSave")
    @ResponseBody
    public AjaxResult editSave(SysSalesOrder sysSalesDetail) {
        AjaxResult ajax = new AjaxResult();
        sysSalesDetail.setUpdateBy(ShiroUtils.getLoginName());
        if (StringUtils.isNotEmpty(sysSalesDetail.getSurplusPrice()) && Double.parseDouble(sysSalesDetail.getSurplusPrice()) == 0) {
            sysSalesDetail.setPriceEndTime(DateUtils.getNowDate());
        } else {
            sysSalesDetail.setPriceEndTime(null);
        }
        sysSalesDetailService.updateSysSalesDetail(sysSalesDetail);
        ajax.put("sysSalesDetail", sysSalesDetail);
        return ajax;
    }

    /**
     * 删除销售信息录入
     */
    @RequiresPermissions("system:detail:remove")
    @Log(title = "销售信息录入", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {


        return toAjax(sysSalesDetailService.deleteSysSalesDetailByIds(ids));
    }
}
