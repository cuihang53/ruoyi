package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.service.ISysHouseService;
import com.ruoyi.system.domain.SysBuilding;
import com.ruoyi.system.domain.SysHouse;
import com.ruoyi.system.domain.SysHouseUnit;
import com.ruoyi.system.mapper.SysHouseMapper;
import com.ruoyi.system.mapper.SysHouseUnitMapper;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 楼房基础信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-05-09
 */
@Service
public class SysHouseServiceImpl implements ISysHouseService {

    private static final Logger log = LoggerFactory.getLogger(SysHouseServiceImpl.class);

    @Autowired
    private SysHouseMapper sysHouseMapper;

    @Autowired
    private com.ruoyi.system.mapper.SysBuildingMapper SysBuildingMapper;

    @Autowired
    private SysHouseUnitMapper houseUnitMapper;



    /**
     * 查询楼房基础信息
     *
     * @param houseId 楼房基础信息ID
     * @return 楼房基础信息
     */
    @Override
    public SysHouse selectSysHouseById(Long houseId) {
        return sysHouseMapper.selectSysHouseById(houseId);
    }


    /**
     * 查询楼房基础信息
     *
     * @param sysHouse 楼房基础信息ID
     * @return 楼房基础信息
     */
    public SysHouse selectSysHouse(SysHouse sysHouse) {

        return sysHouseMapper.selectSysHouse(sysHouse);
    }


    /**
     * 查询楼房基础信息列表
     *
     * @param sysHouse 楼房基础信息
     * @return 楼房基础信息
     */
    @Override
    public List<SysHouse> selectSysHouseList(SysHouse sysHouse) {

        List<SysBuilding> sysBuildings =  SysBuildingMapper.selectSysBuildingAll();
        Map<String,String> map = new HashedMap();
        for(SysBuilding sysBuilding:sysBuildings){
            map.put(sysBuilding.getBuildingId().toString(),sysBuilding.getBuildingName());
        }
        List<SysHouse> sysHouses = sysHouseMapper.selectSysHouseList(sysHouse);
        List<SysHouse> SysHouseList = new ArrayList<SysHouse>();
        for(SysHouse house:sysHouses){
            //house.setBuildingId(map.get(house.getBuildingId()));
            SysHouseList.add(house);
        }
        return SysHouseList;
    }

    @Override
    public List<SysHouse> selectSysHouseAll() {
        return sysHouseMapper.selectSysHouseAll();
    }


    /**
     * 查询楼房基础信息列表
     *
     * @param sysHouse 楼房基础信息
     * @return 楼房基础信息
     */
    @Override
    public List<SysHouse> selectSysHouses(SysHouse sysHouse) {
        List<SysHouse> sysHouses = sysHouseMapper.selectSysHouseList(sysHouse);
        return sysHouses;
    }


    /**
     * 新增楼房基础信息
     *
     * @param sysHouse 楼房基础信息
     * @return 结果
     */
    @Override
    public int insertSysHouse(SysHouse sysHouse) {
        return sysHouseMapper.insertSysHouse(sysHouse);
    }

    @Override
    public List<SysHouse> isExistHouseNum(SysHouse sysHouse) {
        return sysHouseMapper.isExistHouseNum(sysHouse);
    }

    /**
     * 修改楼房基础信息
     *
     * @param sysHouse 楼房基础信息
     * @return 结果
     */
    @Override
    public int updateSysHouse(SysHouse sysHouse) {
        return sysHouseMapper.updateSysHouse(sysHouse);
    }

    /**
     * 删除楼房基础信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysHouseByIds(String ids) {
        return sysHouseMapper.deleteSysHouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除楼房基础信息信息
     *
     * @param houseId 楼房基础信息ID
     * @return 结果
     */
    @Override
    public int deleteSysHouseById(Long houseId) {
        return sysHouseMapper.deleteSysHouseById(houseId);
    }


    /**
     * 导入楼房数据
     *
     * @param houseList       用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importData(List<SysHouse> houseList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(houseList) || houseList.size() == 0) {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysHouse house : houseList) {
            try {
                SysHouse h = new SysHouse();
                h.setHouseNumber(house.getHouseNumber());
                // 验证是否存在这个楼盘
                SysBuilding building = SysBuildingMapper.selectSysBuildingByname(house.getBuldingName().replace(" ", ""));
                //验证楼盘下面是否存在此单元
                List<SysHouseUnit> units = null;
                if (building != null && building.getBuildingId() != null) {
                    SysHouseUnit houseUnit = new SysHouseUnit();
                    houseUnit.setBuildingId(building.getBuildingId().toString());
                    houseUnit.setHouseUnitName(house.getHouseUtilName().replace(" ", ""));
                    units = houseUnitMapper.selectSysHouseUnitList(houseUnit);
                    if (units != null && units.size()>0) {
                        h.setHouseUtilId(units.get(0).getHouseUnitId().toString());
                        house.setHouseUtilId(units.get(0).getHouseUnitId().toString());
                    }
                    h.setBuldingId(building.getBuildingId().toString());
                    house.setBuldingId(building.getBuildingId().toString());
                }
                //验证此楼盘+此单元下是否已经存在此房号
                List<SysHouse> sysHouses = sysHouseMapper.selectSysHouseList(h);

                //判断面积是不是数值格式
                if (StringUtils.isNull(building)) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、数据 " + house.getBuldingName() + "楼盘不存在，请先维护楼盘基础数据");
                } else if (StringUtils.isNull(units) || units.size()==0) {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、数据 " + house.getBuldingName() +"_" +   house.getHouseUtilName() + "不存在，请先维护楼盘单元数据");
                } else if (StringUtils.isNull(sysHouses) || sysHouses.size()==0) {
                    house.setCreateBy(operName);
                    this.sysHouseMapper.insertSysHouse(house);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、数据 " + building.getBuildingName() +"_" +  houseList.get(0).getHouseNumber() +"_" +  house.getHouseNumber() + " 新增成功");
                } else if (isUpdateSupport) {
                    house.setUpdateBy(operName);
                    house.setHouseId(sysHouses.get(0).getHouseId());
                    this.sysHouseMapper.updateSysHouse(house);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、数据 " + building.getBuildingName()+"_"+ houseList.get(0).getHouseNumber() +"_" + house.getHouseNumber() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、数据 " + building.getBuildingName() +"_"+ houseList.get(0).getHouseNumber()+"_" + house.getHouseNumber() + " 已存在");
                }
            } catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、数据导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
