package me.zj22.gudao.server.web.service.impl;

import me.zj22.gudao.server.web.dao.db.RoleMapper;
import me.zj22.gudao.server.web.pojo.dto.Role;
import me.zj22.gudao.server.web.pojo.vo.Page;
import me.zj22.gudao.server.web.service.RoleService;
import me.zj22.gudao.server.web.utils.TimeParse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * daogu
 * Created by 袁鹏 on 2018/3/17.
 */
@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Integer save(Role role) {
        role.setAvailable((byte)1); //默认可用
        role.setCreateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        role.setUpdateTime(TimeParse.Time2NUIX(TimeParse.NUIX2Time(new Date())));
        return roleMapper.insert(role);
    }

    @Override
    public Page<Role> findAll(Page<Role> page) {
        page.setList(roleMapper.findPageList(page));
        page.setTotalRecord(roleMapper.findCount(page));
        return page;
    }

    @Override
    public List<Role> findRole() {
        return roleMapper.findRole();
    }
}
