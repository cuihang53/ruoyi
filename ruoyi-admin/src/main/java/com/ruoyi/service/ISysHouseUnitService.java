package com.ruoyi.service;

import com.ruoyi.system.domain.SysHouseUnit;

import java.util.List;

/**
 * 楼盘单元楼号中间Service接口
 * 
 * @author ruoyi
 * @date 2020-05-16
 */
public interface ISysHouseUnitService 
{
    /**
     * 查询楼盘单元楼号中间
     * 
     * @param id 楼盘单元楼号中间ID
     * @return 楼盘单元楼号中间
     */
    public SysHouseUnit selectSysHouseUnitById(Long id);

    /**
     * 查询楼盘单元楼号中间列表
     * 
     * @param sysHouseUnit 楼盘单元楼号中间
     * @return 楼盘单元楼号中间集合
     */
    public List<SysHouseUnit> selectSysHouseUnitList(SysHouseUnit sysHouseUnit);


    /**
     * 楼盘单元信息导入
     *
     * @return 楼盘基础信息集合
     */
    public String importHouseUnit(List<SysHouseUnit> houseUnitList, Boolean isUpdateSupport, String operName);



    /**
     * 新增楼盘单元楼号中间
     * 
     * @param sysHouseUnit 楼盘单元楼号中间
     * @return 结果
     */
    public int insertSysHouseUnit(SysHouseUnit sysHouseUnit);

    /**
     * 修改楼盘单元楼号中间
     * 
     * @param sysHouseUnit 楼盘单元楼号中间
     * @return 结果
     */
    public int updateSysHouseUnit(SysHouseUnit sysHouseUnit);

    /**
     * 批量删除楼盘单元楼号中间
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysHouseUnitByIds(String ids);

    /**
     * 删除楼盘单元楼号中间信息
     * 
     * @param id 楼盘单元楼号中间ID
     * @return 结果
     */
    public int deleteSysHouseUnitById(Long id);
}
