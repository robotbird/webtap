package com.webtap.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.webtap.domain.entity.Role;
import com.webtap.domain.view.RoleVO;
import com.webtap.repository.RoleRepository;
import com.webtap.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;


@Service
public class RoleServiceImpl implements RoleService {


    @Autowired
    private RoleRepository roleRepository;

    /**
     * 获取角色列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryRoleListWithSelected(Long userId) {
        List<Role> sysRole = roleRepository.queryRoleListWithSelected();
        if (CollectionUtils.isEmpty(sysRole)) {
            return null;
        }
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        for (Role role : sysRole) {
            map = new HashMap<String, Object>(3);
            map.put("id", role.getId());
            map.put("pId", 0);
            map.put("checked", role.getSelected() != null && role.getSelected() == 1);
            map.put("name", role.getDescription());
            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public PageInfo<Role> findPageBreakByCondition(RoleVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<Role> roles = roleRepository.findAll();
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        PageInfo bean = new PageInfo<Role>(roles);
        bean.setList(roles);
        return bean;
    }

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRolesByUserId(Long userId) {
        List<Role> roles = roleRepository.findRolesByUserId(userId);
        return roles;
    }

    @Override
    public Role save(Role entity) {
        Assert.notNull(entity, "Role不可为空！");
        entity.setCreateTime(new Date().getTime());
        entity.setUpdateTime(new Date().getTime());
        roleRepository.save(entity);
        return entity;
    }


    @Override
    public void delete(Long Id) {
         roleRepository.delete(Id);
    }

    @Override
    public Role update(Role role) {
        Assert.notNull(role, "Role不可为空！");
        role.setUpdateTime(new Date().getTime());
        return roleRepository.save(role);
    }

    @Override
    public Role getById(Long Id) {
        Assert.notNull(Id, "Id不可为空！");
        Role sysRole = roleRepository.findById(Id).get();
        return sysRole;
    }

    @Override
    public List<Role> findAll() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

}
