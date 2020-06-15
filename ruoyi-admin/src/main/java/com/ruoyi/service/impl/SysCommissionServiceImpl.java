package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.service.ISysCommissionService;
import com.ruoyi.system.domain.SysCommission;
import com.ruoyi.system.mapper.SysCommissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提成明细Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-17
 */
@Service
public class SysCommissionServiceImpl implements ISysCommissionService
{
    @Autowired
    private SysCommissionMapper sysCommissionMapper;

    /**
     * 查询提成明细
     * 
     * @param id 提成明细ID
     * @return 提成明细
     */
    @Override
    public SysCommission selectSysCommissionById(Long id)
    {
        return sysCommissionMapper.selectSysCommissionById(id);
    }

    /**
     * 查询提成明细列表
     * 
     * @param sysCommission 提成明细
     * @return 提成明细
     */
    @Override
    public List<SysCommission> selectSysCommissionList(SysCommission sysCommission)
    {
        return sysCommissionMapper.selectSysCommissionList(sysCommission);
    }

    /**
     * 根据销售单ID查询提成明细列表
     *
     * @param salesDetailId 提成明细
     * @return 提成明细集合
     */
    @Override
    public List<SysCommission> selectByorderId(Long salesDetailId) {

        return sysCommissionMapper.selectByorderId(salesDetailId);
    }

    /**
     * 新增提成明细
     * 
     * @param sysCommission 提成明细
     * @return 结果
     */
    @Override
    public int insertSysCommission(SysCommission sysCommission)
    {
  /**      if(StringUtils.isEmpty(sysCommission.getPostId())){
            throw new BusinessException("岗位不能为空");
        }
        else if(StringUtils.isEmpty(sysCommission.getRemind())){
            throw new BusinessException("提点不能为空");
        }
        else if(StringUtils.isEmpty(sysCommission.getUserId())){
            throw new BusinessException("对应人员不能为空");
        }
   **/
        return sysCommissionMapper.insertSysCommission(sysCommission);
    }

    /**
     * 修改提成明细
     * 
     * @param sysCommission 提成明细
     * @return 结果
     */
    @Override
    public int updateSysCommission(SysCommission sysCommission)
    {
        sysCommission.setUpdateTime(DateUtils.getNowDate());
        return sysCommissionMapper.updateSysCommission(sysCommission);
    }

    /**
     * 删除提成明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysCommissionByIds(String ids)
    {
        return sysCommissionMapper.deleteSysCommissionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除提成明细信息
     * 
     * @param id 提成明细ID
     * @return 结果
     */
    @Override
    public int deleteSysCommissionById(Long id)
    {
        return sysCommissionMapper.deleteSysCommissionById(id);
    }
}
