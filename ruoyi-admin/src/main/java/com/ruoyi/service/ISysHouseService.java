package com.ruoyi.service;

import com.ruoyi.system.domain.SysHouse;

import java.util.List;

/**
 * 楼房基础信息Service接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface ISysHouseService 
{
    /**
     * 查询楼房基础信息
     * 
     * @param houseId 楼房基础信息ID
     * @return 楼房基础信息
     */
    public SysHouse selectSysHouseById(Long houseId);

    /**
     * 查询楼房基础
     *
     * @param sysHouse 楼房基础信息ID
     * @return 楼房基础信息
     */
    public SysHouse selectSysHouse(SysHouse sysHouse);


    /**
     * 查询楼房基础信息列表
     * 
     * @param sysHouse 楼房基础信息
     * @return 楼房基础信息集合
     */
    public List<SysHouse> selectSysHouseList(SysHouse sysHouse);

    /**
     * 查询楼房基础信息列表
     *
     * @return 楼房基础信息集合
     */
    public List<SysHouse> selectSysHouseAll();



    /**
     * 查询楼房基础信息列表
     *
     * @param sysHouse 楼房基础信息
     * @return 楼房基础信息
     */
    public List<SysHouse> selectSysHouses(SysHouse sysHouse);

    /**
     * 新增楼房基础信息
     * 
     * @param sysHouse 楼房基础信息
     * @return 结果
     */
    public int insertSysHouse(SysHouse sysHouse);

    /**
     * 檢查房号是否已经存在
     *
     * @param sysHouse 楼房基础信息ID
     * @return 楼房基础信息
     */
    public List<SysHouse> isExistHouseNum(SysHouse sysHouse);

    /**
     * 修改楼房基础信息
     * 
     * @param sysHouse 楼房基础信息
     * @return 结果
     */
    public int updateSysHouse(SysHouse sysHouse);

    /**
     * 批量删除楼房基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysHouseByIds(String ids);

    /**
     * 删除楼房基础信息信息
     * 
     * @param houseId 楼房基础信息ID
     * @return 结果
     */
    public int deleteSysHouseById(Long houseId);


    /**
     * 导入楼房数据
     *
     * @param houseList 用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importData(List<SysHouse> houseList, Boolean isUpdateSupport, String operName);

}
