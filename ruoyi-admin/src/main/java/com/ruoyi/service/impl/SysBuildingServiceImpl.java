package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.CodeFactoryUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.service.ISysBuildingService;
import com.ruoyi.system.domain.SysBuilding;
import com.ruoyi.system.domain.SysHouseUnit;
import com.ruoyi.system.mapper.SysBuildingMapper;
import com.ruoyi.system.mapper.SysHouseUnitMapper;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 楼盘基础信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-09
 */
@Service("building")
public class SysBuildingServiceImpl implements ISysBuildingService
{

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

    @Autowired
    private SysBuildingMapper sysBuildingMapper;

    @Autowired
    private SysHouseUnitMapper houseUnitMapper;



    /**
     * 查询楼盘基础信息
     * 
     * @param buildingId 楼盘基础信息ID
     * @return 楼盘基础信息
     */
    @Override
    public SysBuilding selectSysBuildingById(Long buildingId)
    {


        return sysBuildingMapper.selectSysBuildingById(buildingId);
    }

    /**
     * 查询楼盘是否已经存在或编号已经被使用
     *
     * @param sysBuilding 楼盘基础信息ID
     * @return 楼盘基础信息
     */
    @Override
    public SysBuilding isExistBuildingCode(SysBuilding sysBuilding) {

        return sysBuildingMapper.isExistBuildingCode(sysBuilding);
    }


    /**
     * 查询楼盘名称是否已经存在
     *
     * @param sysBuilding 楼盘基础信息ID
     * @return 楼盘基础信息
     */
    @Override
    public List<SysBuilding> isExistBuildingName(SysBuilding sysBuilding) {
        return sysBuildingMapper.isExistBuildingName(sysBuilding);
    }


    /**
     * 查询楼盘基础信息列表
     * 
     * @param sysBuilding 楼盘基础信息
     * @return 楼盘基础信息
     */
    @Override
    public List<SysBuilding> selectSysBuildingList(SysBuilding sysBuilding)
    {
        return sysBuildingMapper.selectSysBuildingList(sysBuilding);
    }

    @Override
    public String importBuilding(List<SysBuilding> buildingList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(buildingList) || buildingList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (SysBuilding building : buildingList)
        {
            try
            {
                // 验证是否存在这个楼盘
                SysBuilding u = sysBuildingMapper.selectSysBuildingByname(building.getBuildingName());
                if (StringUtils.isNull(u))
                {
                    building.setCreateBy(operName);
                    building.setBuildingCode(CodeFactoryUtil.getBuldingCode(ShiroUtils.getUserId()));
                    this.insertSysBuilding(building);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、楼盘 " + building.getBuildingName() + " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    building.setUpdateBy(operName);
                    this.updateSysBuilding(building);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、楼盘 " +  building.getBuildingName() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、楼盘 " +  building.getBuildingName() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、楼盘 " +  building.getBuildingName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    public List<SysBuilding> selectSysBuildingAll() {

        return sysBuildingMapper.selectSysBuildingAll();
    }

    /**
     * 新增楼盘基础信息
     * 
     * @param sysBuilding 楼盘基础信息
     * @return 结果
     */
    @Override
    public int insertSysBuilding(SysBuilding sysBuilding)
    {
        return sysBuildingMapper.insertSysBuilding(sysBuilding);
    }

    /**
     * 修改楼盘基础信息
     * 
     * @param sysBuilding 楼盘基础信息
     * @return 结果
     */
    @Override
    public int updateSysBuilding(SysBuilding sysBuilding)
    {
        return sysBuildingMapper.updateSysBuilding(sysBuilding);
    }

    /**
     * 删除楼盘基础信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysBuildingByIds(String ids)
    {

        Long[] buldingIds = Convert.toLongArray(ids);
        for (Long buldingId : buldingIds)
        {
            SysHouseUnit unit = new SysHouseUnit();
            unit.setBuildingId(buldingId.toString());
            List<SysHouseUnit> units =  houseUnitMapper.selectSysHouseUnitList(unit);
            if (units != null &&  units.size()>0)
            {
                SysBuilding post = sysBuildingMapper.selectSysBuildingById(buldingId);
                throw new BusinessException(String.format("%1$s已被使用,不能删除", post.getBuildingName()));
            }
        }
        return sysBuildingMapper.deleteSysBuildingByIds(buldingIds);
    }

    /**
     * 删除楼盘基础信息信息
     * 
     * @param buildingId 楼盘基础信息ID
     * @return 结果
     */
    @Override
    public int deleteSysBuildingById(Long buildingId)
    {
        return sysBuildingMapper.deleteSysBuildingById(buildingId);
    }
}
