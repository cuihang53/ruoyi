package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.service.ISysAttachmentService;
import com.ruoyi.system.domain.SysAttachment;
import com.ruoyi.system.mapper.SysAttachmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 附件Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Service
public class SysAttachmentServiceImpl implements ISysAttachmentService
{
    @Autowired
    private SysAttachmentMapper sysAttachmentMapper;

    /**
     * 查询附件
     * 
     * @param fileId 附件ID
     * @return 附件
     */
    @Override
    public SysAttachment selectSysAttachmentById(Long fileId)
    {
        return sysAttachmentMapper.selectSysAttachmentById(fileId);
    }

    /**
     * 查询附件列表
     * 
     * @param sysAttachment 附件
     * @return 附件
     */
    @Override
    public List<SysAttachment> selectSysAttachmentList(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.selectSysAttachmentList(sysAttachment);
    }

    /**
     * 新增附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    @Override
    public int insertSysAttachment(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.insertSysAttachment(sysAttachment);
    }

    /**
     * 修改附件
     * 
     * @param sysAttachment 附件
     * @return 结果
     */
    @Override
    public int updateSysAttachment(SysAttachment sysAttachment)
    {
        return sysAttachmentMapper.updateSysAttachment(sysAttachment);
    }

    /**
     * 删除附件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentByIds(String ids)
    {
        return sysAttachmentMapper.deleteSysAttachmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除附件信息
     * 
     * @param fileId 附件ID
     * @return 结果
     */
    @Override
    public int deleteSysAttachmentById(Long fileId)
    {
        return sysAttachmentMapper.deleteSysAttachmentById(fileId);
    }
}
