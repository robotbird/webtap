package com.webtap.service.impl;


import com.webtap.domain.entity.RoleResource;
import com.webtap.repository.RoleResourceRepository;
import com.webtap.service.RoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class RoleResourceServiceImpl implements RoleResourceService {
    @Autowired
    private RoleResourceRepository roleResourceRepository;



        /**
     * 添加角色资源
     * @param roleId
     * @param resourcesId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void addRoleResource(Long roleId, String resourcesId) {
        //删除
        removeByRoleId(roleId);
        //添加
        if (!StringUtils.isEmpty(resourcesId)) {
            String[] resourcesArr = resourcesId.split(",");
            RoleResource r = null;
            for (String ri : resourcesArr) {
                r = new RoleResource();
                r.setRoleId(roleId);
                r.setResourcesId(Long.parseLong(ri));
                r.setCreateTime(new Date().getTime());
                r.setUpdateTime(new Date().getTime());
                //roleResources.add(r);
                save(r);
            }
        }
    }


    /**
     * 通过角色id批量删除
     * @param roleId
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = {Exception.class})
    public void removeByRoleId(Long roleId) {
        //删除

    }

    @Override
    public RoleResource save(RoleResource entity) {
        Assert.notNull(entity, "RoleResource不可为空！");
        entity.setCreateTime(new Date().getTime());
        entity.setUpdateTime(new Date().getTime());
        roleResourceRepository.save(entity);
        return entity;
    }


    @Override
    public void delete(Long Id) {
        roleResourceRepository.delete(Id);
    }

    @Override
    public RoleResource update(RoleResource role) {
        Assert.notNull(role, "RoleResource不可为空！");
        role.setUpdateTime(new Date().getTime());
        return roleResourceRepository.save(role);
    }

    @Override
    public RoleResource getById(Long Id) {
        Assert.notNull(Id, "Id不可为空！");
        Optional<RoleResource> sysRoleResource = roleResourceRepository.findById(Id);
        return sysRoleResource.get();
    }

    @Override
    public List<RoleResource> findAll() {
        List<RoleResource> roles = roleResourceRepository.findAll();
        return roles;
    }
}
