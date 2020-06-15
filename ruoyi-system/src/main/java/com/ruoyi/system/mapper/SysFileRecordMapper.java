package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysFileRecord;

import java.util.List;

/**
 * 资料领取记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-08
 */
public interface SysFileRecordMapper 
{
    /**
     * 查询资料领取记录
     * 
     * @param fileRecordId 资料领取记录ID
     * @return 资料领取记录
     */
    public SysFileRecord selectSysFileRecordById(Long fileRecordId);

    /**
     * 查询资料领取记录列表
     * 
     * @param sysFileRecord 资料领取记录
     * @return 资料领取记录集合
     */
    public List<SysFileRecord> selectSysFileRecordList(SysFileRecord sysFileRecord);

    /**
     * 新增资料领取记录
     * 
     * @param sysFileRecord 资料领取记录
     * @return 结果
     */
    public int insertSysFileRecord(SysFileRecord sysFileRecord);

    /**
     * 修改资料领取记录
     * 
     * @param sysFileRecord 资料领取记录
     * @return 结果
     */
    public int updateSysFileRecord(SysFileRecord sysFileRecord);

    /**
     * 删除资料领取记录
     * 
     * @param fileRecordId 资料领取记录ID
     * @return 结果
     */
    public int deleteSysFileRecordById(Long fileRecordId);

    /**
     * 批量删除资料领取记录
     * 
     * @param fileRecordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFileRecordByIds(String[] fileRecordIds);
}
