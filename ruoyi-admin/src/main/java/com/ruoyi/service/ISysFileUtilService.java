package com.ruoyi.service;

import com.ruoyi.system.domain.SysFileUtil;

import java.util.List;

/**
 * 附件Service接口
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public interface ISysFileUtilService 
{
    /**
     * 查询附件
     * 
     * @param fileUtilId 附件ID
     * @return 附件
     */
    public SysFileUtil selectSysFileUtilById(Long fileUtilId);

    /**
     * 查询附件列表
     * 
     * @param sysFileUtil 附件
     * @return 附件集合
     */
    public List<SysFileUtil> selectSysFileUtilList(SysFileUtil sysFileUtil);

    /**
     * 新增附件
     * 
     * @param sysFileUtil 附件
     * @return 结果
     */
    public int insertSysFileUtil(SysFileUtil sysFileUtil);

    /**
     * 修改附件
     * 
     * @param sysFileUtil 附件
     * @return 结果
     */
    public int updateSysFileUtil(SysFileUtil sysFileUtil);

    /**
     * 批量删除附件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysFileUtilByIds(String ids);

    /**
     * 删除附件信息
     * 
     * @param fileUtilId 附件ID
     * @return 结果
     */
    public int deleteSysFileUtilById(Long fileUtilId);
}
