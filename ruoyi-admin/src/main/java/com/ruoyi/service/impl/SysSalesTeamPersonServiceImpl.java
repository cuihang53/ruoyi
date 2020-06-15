package com.ruoyi.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.service.ISysSalesTeamPersonService;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysSalesTeamPerson;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysSalesTeampersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 销售小组成员信息Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
public class SysSalesTeamPersonServiceImpl implements ISysSalesTeamPersonService {
    @Autowired
    private SysSalesTeampersonMapper sysSalesTeampersonMapper;

    @Autowired
    private SysPostMapper postMapper;


    /**
     * 查询销售小组成员信息
     *
     * @param salesId 销售小组成员信息ID
     * @return 销售小组成员信息
     */
    @Override
    public SysSalesTeamPerson selectSysSalesTeampersonById(Long salesId) {
        SysSalesTeamPerson person = sysSalesTeampersonMapper.selectSysSalesTeampersonById(salesId);
        List<SysPost> posts = postMapper.selectPostsByUserId(person.getUserId());
        String position = null;
        for (SysPost post : posts) {
            if (position == null) {
                position = post.getPostName();
            } else {
                position += "," + post.getPostName();
            }
        }
        person.setPosition(position);
        return person;
    }

    @Override
    public List<SysSalesTeamPerson> selectByTeamId(Long teamId) {
        return sysSalesTeampersonMapper.selectByTeamId(teamId);
    }

    /**
     * 查询销售小组成员信息列表
     *
     * @param sysSalesTeamperson 销售小组成员信息
     * @return 销售小组成员信息
     */
    @Override
    public List<SysSalesTeamPerson> selectSysSalesTeampersonList(SysSalesTeamPerson sysSalesTeamperson) {
        List<SysSalesTeamPerson> teamPersonList = sysSalesTeampersonMapper.selectSysSalesTeampersonList(sysSalesTeamperson);
        for (SysSalesTeamPerson teamPerson : teamPersonList) {
            List<SysPost> posts = postMapper.selectPostsByUserId(teamPerson.getUserId());
            String position = null;
            for (SysPost post : posts) {
                if (position == null) {
                    position = post.getPostName();
                } else {
                    position += "," + post.getPostName();
                }
            }
            teamPerson.setPosition(position);
        }
        return teamPersonList;
    }

    /**
     * 新增销售小组成员信息
     *
     * @param sysSalesTeamperson 销售小组成员信息
     * @return 结果
     */
    @Override
    public int insertSysSalesTeamperson(SysSalesTeamPerson sysSalesTeamperson) {
        sysSalesTeamperson.setCreateTime(DateUtils.getNowDate());
        return sysSalesTeampersonMapper.insertSysSalesTeamperson(sysSalesTeamperson);
    }

    /**
     * 修改销售小组成员信息
     *
     * @param sysSalesTeamperson 销售小组成员信息
     * @return 结果
     */
    @Override
    public int updateSysSalesTeamperson(SysSalesTeamPerson sysSalesTeamperson) {
        sysSalesTeamperson.setUpdateTime(DateUtils.getNowDate());
        return sysSalesTeampersonMapper.updateSysSalesTeamperson(sysSalesTeamperson);
    }

    /**
     * 删除销售小组成员信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysSalesTeampersonByIds(String ids) {
        return sysSalesTeampersonMapper.deleteSysSalesTeampersonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售小组成员信息信息
     *
     * @param salesId 销售小组成员信息ID
     * @return 结果
     */
    @Override
    public int deleteSysSalesTeampersonById(Long salesId) {
        return sysSalesTeampersonMapper.deleteSysSalesTeampersonById(salesId);
    }
}
