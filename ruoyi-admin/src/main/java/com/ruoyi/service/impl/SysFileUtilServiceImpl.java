package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.service.ISysFileUtilService;
import com.ruoyi.system.domain.SysFileUtil;
import com.ruoyi.system.mapper.SysFileUtilMapper;
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
public class SysFileUtilServiceImpl implements ISysFileUtilService
{
    @Autowired
    private SysFileUtilMapper sysFileUtilMapper;

    /**
     * 查询附件
     * 
     * @param fileUtilId 附件ID
     * @return 附件
     */
    @Override
    public SysFileUtil selectSysFileUtilById(Long fileUtilId)
    {
        return sysFileUtilMapper.selectSysFileUtilById(fileUtilId);
    }

    /**
     * 查询附件列表
     * 
     * @param sysFileUtil 附件
     * @return 附件
     */
    @Override
    public List<SysFileUtil> selectSysFileUtilList(SysFileUtil sysFileUtil)
    {
        return sysFileUtilMapper.selectSysFileUtilList(sysFileUtil);
    }

    /**
     * 新增附件
     * 
     * @param sysFileUtil 附件
     * @return 结果
     */
    @Override
    public int insertSysFileUtil(SysFileUtil sysFileUtil)
    {
        return sysFileUtilMapper.insertSysFileUtil(sysFileUtil);
    }

    /**
     * 修改附件
     * 
     * @param sysFileUtil 附件
     * @return 结果
     */
    @Override
    public int updateSysFileUtil(SysFileUtil sysFileUtil)
    {
        return sysFileUtilMapper.updateSysFileUtil(sysFileUtil);
    }

    /**
     * 删除附件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFileUtilByIds(String ids)
    {
        return sysFileUtilMapper.deleteSysFileUtilByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除附件信息
     * 
     * @param fileUtilId 附件ID
     * @return 结果
     */
    @Override
    public int deleteSysFileUtilById(Long fileUtilId)
    {
        return sysFileUtilMapper.deleteSysFileUtilById(fileUtilId);
    }
}
