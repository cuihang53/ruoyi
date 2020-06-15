package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysHouse;

import java.util.List;

/**
 * 楼房基础信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
public interface SysHouseMapper 
{
    /**
     * 查询楼房基础信息
     * 
     * @param houseId 楼房基础信息ID
     * @return 楼房基础信息
     */
    public SysHouse selectSysHouseById(Long houseId);

    /**
     * 查询楼房基础信息
     *
     * @param sysHouse 楼房基础信息ID
     * @return 楼房基础信息
     */
    public SysHouse selectSysHouse(SysHouse sysHouse);


    /**
     * 檢查房号是否已经存在
     *
     * @param sysHouse 楼房基础信息ID
     * @return 楼房基础信息
     */
    public List<SysHouse> isExistHouseNum(SysHouse sysHouse);


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
     * 新增楼房基础信息
     * 
     * @param sysHouse 楼房基础信息
     * @return 结果
     */
    public int insertSysHouse(SysHouse sysHouse);

    /**
     * 修改楼房基础信息
     * 
     * @param sysHouse 楼房基础信息
     * @return 结果
     */
    public int updateSysHouse(SysHouse sysHouse);

    /**
     * 删除楼房基础信息
     * 
     * @param houseId 楼房基础信息ID
     * @return 结果
     */
    public int deleteSysHouseById(Long houseId);

    /**
     * 批量删除楼房基础信息
     * 
     * @param houseIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysHouseByIds(String[] houseIds);
}
