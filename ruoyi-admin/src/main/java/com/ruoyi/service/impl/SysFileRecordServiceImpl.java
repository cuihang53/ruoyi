package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.service.ISysFileRecordService;
import com.ruoyi.system.domain.SysFileRecord;
import com.ruoyi.system.mapper.SysFileRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资料领取记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
@Service
public class SysFileRecordServiceImpl implements ISysFileRecordService
{
    @Autowired
    private SysFileRecordMapper sysFileRecordMapper;

    /**
     * 查询资料领取记录
     * 
     * @param fileRecordId 资料领取记录ID
     * @return 资料领取记录
     */
    @Override
    public SysFileRecord selectSysFileRecordById(Long fileRecordId)
    {
        return sysFileRecordMapper.selectSysFileRecordById(fileRecordId);
    }

    /**
     * 查询资料领取记录列表
     * 
     * @param sysFileRecord 资料领取记录
     * @return 资料领取记录
     */
    @Override
    public List<SysFileRecord> selectSysFileRecordList(SysFileRecord sysFileRecord)
    {
        return sysFileRecordMapper.selectSysFileRecordList(sysFileRecord);
    }

    /**
     * 新增资料领取记录
     * 
     * @param sysFileRecord 资料领取记录
     * @return 结果
     */
    @Override
    public int insertSysFileRecord(SysFileRecord sysFileRecord)
    {
        sysFileRecord.setCreateTime(DateUtils.getNowDate());
        return sysFileRecordMapper.insertSysFileRecord(sysFileRecord);
    }

    /**
     * 修改资料领取记录
     * 
     * @param sysFileRecord 资料领取记录
     * @return 结果
     */
    @Override
    public int updateSysFileRecord(SysFileRecord sysFileRecord)
    {
        sysFileRecord.setUpdateTime(DateUtils.getNowDate());
        return sysFileRecordMapper.updateSysFileRecord(sysFileRecord);
    }

    /**
     * 删除资料领取记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysFileRecordByIds(String ids)
    {
        return sysFileRecordMapper.deleteSysFileRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资料领取记录信息
     * 
     * @param fileRecordId 资料领取记录ID
     * @return 结果
     */
    @Override
    public int deleteSysFileRecordById(Long fileRecordId)
    {
        return sysFileRecordMapper.deleteSysFileRecordById(fileRecordId);
    }
}
