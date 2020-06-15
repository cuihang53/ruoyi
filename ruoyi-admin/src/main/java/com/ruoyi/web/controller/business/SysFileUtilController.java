package com.ruoyi.web.controller.business;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.service.ISysAttachmentService;
import com.ruoyi.service.ISysFileUtilService;
import com.ruoyi.system.domain.SysAttachment;
import com.ruoyi.system.domain.SysFileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 附件Controller
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Controller
@RequestMapping("/system/util")
public class SysFileUtilController extends BaseController
{
    private String prefix = "system/util";

    @Autowired
    private ISysFileUtilService sysFileUtilService;


    @Autowired
    private ISysAttachmentService sysAttachmentService;

    @RequiresPermissions("system:util:view")
    @GetMapping()
    public String util()
    {
        return prefix + "/util";
    }

    /**
     * 查询附件列表
     */
    @RequiresPermissions("system:util:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFileUtil sysFileUtil)
    {
        startPage();
        List<SysFileUtil> list = sysFileUtilService.selectSysFileUtilList(sysFileUtil);
        return getDataTable(list);
    }

    /**
     * 导出附件列表
     */
    @RequiresPermissions("system:util:export")
    @Log(title = "附件", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysFileUtil sysFileUtil)
    {
        List<SysFileUtil> list = sysFileUtilService.selectSysFileUtilList(sysFileUtil);
        ExcelUtil<SysFileUtil> util = new ExcelUtil<SysFileUtil>(SysFileUtil.class);
        return util.exportExcel(list, "util");
    }

    /**
     * 新增附件
     */
    @GetMapping("/add")
    public String add()
    {

        return prefix + "/add";
    }

    /**
     * 新增保存附件
     */
    @RequiresPermissions("system:util:add")
    @Log(title = "附件", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@RequestParam("file") MultipartFile file[], SysFileUtil sysFileUtil) throws IOException {
        AjaxResult ajax = new AjaxResult();
        // 上传文件路径
        String filePath = Global.getUploadPath();

        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath,file[0]);
        SysAttachment fileInfo = new SysAttachment();
        fileInfo.setFilePath(fileName);
        fileInfo.setFileName(sysFileUtil.getFileName());

        sysAttachmentService.insertSysAttachment(fileInfo);

        sysFileUtil.setFileAttachmentId(fileInfo.getFileId());
        sysFileUtilService.insertSysFileUtil(sysFileUtil);
        ajax.put("fileUtilId",sysFileUtil.getFileUtilId());
        ajax.put("fileName",sysFileUtil.getFileName());
        ajax.put("filePaths",fileInfo.getFilePath());
        String str[] = fileInfo.getFilePath().split("\\.");
        ajax.put("houzhui",str[str.length-1].toLowerCase());
        return ajax;
    }

    /**
     * 修改附件
     */
    @GetMapping("/edit/{fileUtilId}")
    public String edit(@PathVariable("fileUtilId") Long fileUtilId, ModelMap mmap)
    {
        SysFileUtil sysFileUtil = sysFileUtilService.selectSysFileUtilById(fileUtilId);
        mmap.put("sysFileUtil", sysFileUtil);
        return prefix + "/edit";
    }

    /**
     * 修改保存附件
     */
    @RequiresPermissions("system:util:edit")
    @Log(title = "附件", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysFileUtil sysFileUtil)
    {
        return toAjax(sysFileUtilService.updateSysFileUtil(sysFileUtil));
    }

    /**
     * 删除附件
     */
    @RequiresPermissions("system:util:remove")
    @Log(title = "附件", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        for(String id: ids.split(",")){
            SysFileUtil util = sysFileUtilService.selectSysFileUtilById(Long.parseLong(id));
            FileUtils.deleteFile(util.getAttachment().getFilePath());//删除附件
            sysAttachmentService.deleteSysAttachmentById(util.getFileAttachmentId());//删除附件表数据
            sysFileUtilService.deleteSysFileUtilById(Long.parseLong(id));//删除中间表数据
        }
        return success();
    }


    /**
     * 本地资源下载
     */
    @RequiresPermissions("system:util:dowLoad")
    @GetMapping("dowLoad")
    public void resourceDownload(String id, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        SysFileUtil util = sysFileUtilService.selectSysFileUtilById(Long.parseLong(id));
        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(util.getAttachment().getFilePath(), Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));
        FileUtils.writeBytes(downloadPath, response.getOutputStream());
    }


}
