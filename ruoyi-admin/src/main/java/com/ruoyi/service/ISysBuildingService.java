package com.ruoyi.service;

import com.ruoyi.system.domain.SysBuilding;

import java.util.List;

/**
 * 楼盘基础信息Service接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface ISysBuildingService 
{
    /**
     * 查询楼盘基础信息
     * 
     * @param buildingId 楼盘基础信息ID
     * @return 楼盘基础信息
     */
    public SysBuilding selectSysBuildingById(Long buildingId);




    /**
     * 检查楼盘编号是否已经存在
     *
     * @param sysBuilding 楼盘基础信息
     * @return 楼盘基础信息集合
     */
    public SysBuilding isExistBuildingCode(SysBuilding sysBuilding);

    /**
     * 查询楼盘名称是否已经存在
     *
     * @param sysBuilding 楼盘基础信息ID
     * @return 楼盘基础信息
     */
    public List<SysBuilding> isExistBuildingName(SysBuilding sysBuilding);

    /**
     * 查询楼盘基础信息列表
     * 
     * @param sysBuilding 楼盘基础信息
     * @return 楼盘基础信息集合
     */
    public List<SysBuilding> selectSysBuildingList(SysBuilding sysBuilding);

    /**
     * 楼盘信息导入
     *
     * @return 楼盘基础信息集合
     */
    public String importBuilding(List<SysBuilding> buildingList, Boolean isUpdateSupport, String operName);

    /**
     * 查询楼盘基础信息列表
     *
     * @return 楼盘基础信息集合
     */
    public List<SysBuilding> selectSysBuildingAll();
    /**
     * 新增楼盘基础信息
     * 
     * @param sysBuilding 楼盘基础信息
     * @return 结果
     */
    public int insertSysBuilding(SysBuilding sysBuilding);

    /**
     * 修改楼盘基础信息
     * 
     * @param sysBuilding 楼盘基础信息
     * @return 结果
     */
    public int updateSysBuilding(SysBuilding sysBuilding);

    /**
     * 批量删除楼盘基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysBuildingByIds(String ids);

    /**
     * 删除楼盘基础信息信息
     * 
     * @param buildingId 楼盘基础信息ID
     * @return 结果
     */
    public int deleteSysBuildingById(Long buildingId);
}
