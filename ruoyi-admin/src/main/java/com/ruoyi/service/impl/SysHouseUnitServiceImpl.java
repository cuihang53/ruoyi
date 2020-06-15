package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.CodeFactoryUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysHouseUnitService;
import com.ruoyi.system.domain.SysBuilding;
import com.ruoyi.system.domain.SysHouse;
import com.ruoyi.system.domain.SysHouseUnit;
import com.ruoyi.system.mapper.SysBuildingMapper;
import com.ruoyi.system.mapper.SysHouseMapper;
import com.ruoyi.system.mapper.SysHouseUnitMapper;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 楼盘单元楼号中间Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-16
 */
@Service
public class SysHouseUnitServiceImpl implements ISysHouseUnitService
{
    private static final Logger log = LoggerFactory.getLogger(SysHouseUnitServiceImpl.class);

    @Autowired
    private SysHouseUnitMapper sysHouseUnitMapper;

    @Autowired
    private SysHouseMapper houseMapper;

    @Autowired
    private SysBuildingMapper buildingMapper;



    /**
     * 查询楼盘单元楼号中间
     * 
     * @param id 楼盘单元楼号中间ID
     * @return 楼盘单元楼号中间
     */
    @Override
    public SysHouseUnit selectSysHouseUnitById(Long id)
    {
        return sysHouseUnitMapper.selectSysHouseUnitById(id);
    }

    /**
     * 查询楼盘单元楼号中间列表
     * 
     * @param sysHouseUnit 楼盘单元楼号中间
     * @return 楼盘单元楼号中间
     */
    @Override
    public List<SysHouseUnit> selectSysHouseUnitList(SysHouseUnit sysHouseUnit)
    {
        return sysHouseUnitMapper.selectSysHouseUnitList(sysHouseUnit);
    }

    @Override
    public String importHouseUnit(List<SysHouseUnit> houseUnitList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(houseUnitList) || houseUnitList.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<SysBuilding> buildings =  buildingMapper.selectSysBuildingAll();
        Map<String,String> map = new HashedMap();
        for (SysBuilding building  : buildings){
            map.put(building.getBuildingName(),building.getBuildingId().toString());
        }
        for (SysHouseUnit houseUnit  : houseUnitList)
        {
            try
            {
                // 验证是否存在这个单元
                houseUnit.setBuildingId(map.get(houseUnit.getBuildingName()));
                SysHouseUnit u = sysHouseUnitMapper.selectSysHouseUnitByName(houseUnit);
                if (StringUtils.isNull(u))
                {
                    houseUnit.setCreateBy(operName);
                    houseUnit.setUnitCode(CodeFactoryUtil.getHouseUnitCode(ShiroUtils.getUserId()));
                    this.insertSysHouseUnit(houseUnit);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、楼盘 " + houseUnit.getHouseUnitName()+ " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    houseUnit.setUpdateBy(operName);
                    houseUnit.setHouseUnitId(u.getHouseUnitId());
                    this.updateSysHouseUnit(houseUnit);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、楼盘  " + houseUnit.getHouseUnitName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、楼盘  " +  houseUnit.getHouseUnitName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " +  houseUnit.getHouseUnitName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "成功"+successNum+"条;失败 " + failureNum + " 条，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }


    /**
     * 新增楼盘单元楼号中间
     * 
     * @param sysHouseUnit 楼盘单元楼号中间
     * @return 结果
     */
    @Override
    public int insertSysHouseUnit(SysHouseUnit sysHouseUnit)
    {
        return sysHouseUnitMapper.insertSysHouseUnit(sysHouseUnit);
    }

    /**
     * 修改楼盘单元楼号中间
     * 
     * @param sysHouseUnit 楼盘单元楼号中间
     * @return 结果
     */
    @Override
    public int updateSysHouseUnit(SysHouseUnit sysHouseUnit)
    {
        return sysHouseUnitMapper.updateSysHouseUnit(sysHouseUnit);
    }

    /**
     * 删除楼盘单元楼号中间对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysHouseUnitByIds(String ids)
    {
        Long[] idstr = Convert.toLongArray(ids);
        for (Long id : idstr)
        {
            SysHouse house = new SysHouse();
            house.setHouseUtilId(id.toString());
            List<SysHouse> houses =  houseMapper.selectSysHouseList(house);
            if (houses != null &&  houses.size()>0)
            {
                SysHouseUnit h = sysHouseUnitMapper.selectSysHouseUnitById(id);
                throw new BusinessException(String.format("%1$s已被使用,不能删除", h.getHouseUnitName()));
            }
        }
        return sysHouseUnitMapper.deleteSysHouseUnitByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除楼盘单元楼号中间信息
     * 
     * @param id 楼盘单元楼号中间ID
     * @return 结果
     */
    @Override
    public int deleteSysHouseUnitById(Long id)
    {

        return sysHouseUnitMapper.deleteSysHouseUnitById(id);
    }
}
