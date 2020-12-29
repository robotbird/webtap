package com.webtap.service.impl;

import com.github.pagehelper.PageInfo;
import com.webtap.domain.entity.Resource;
import com.webtap.repository.ResourceRepository;
import com.webtap.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * 获取用户资源
     *
     * @param userId
     * @return
     */
    @Override
    public List<Resource> findResourceByUserId(Long userId) {
        List<Resource> resources = resourceRepository.findResourceByUserId(userId);
        return resources;
    }

    @Override
    public Resource save(Resource entity) {
        Assert.notNull(entity, "Resource不可为空！");
        entity.setCreateTime(new Date().getTime());
        entity.setUpdateTime(new Date().getTime());
        resourceRepository.save(entity);
        return entity;
    }


    @Override
    public void delete(Long Id) {
        resourceRepository.delete(Id);
    }

    @Override
    public Resource update(Resource resource) {
        Assert.notNull(resource, "Resource不可为空！");
        resource.setUpdateTime(new Date().getTime());
        return resourceRepository.save(resource);
    }

    @Override
    public Resource getById(Long Id) {
        Assert.notNull(Id, "Id不可为空！");
        Resource sysResource = resourceRepository.findById(Id).get();
        return sysResource;
    }

    @Override
    public List<Resource> findAll() {
        List<Resource> resources = resourceRepository.findAll();
        return resources;
    }

    @Override
    public PageInfo<Resource> findPageBreakByCondition(Resource resource) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryResourcesListWithSelected(Long rid) {
        return null;
    }

    @Override
    public List<Resource> listUrlAndPermission() {
        return resourceRepository.findAll();
    }

    @Override
    public List<Resource> listAllAvailableMenu() {
        return null;
    }

    @Override
    public List<Resource> findByUserId(Long userId) {
        return resourceRepository.findResourceByUserId(userId);
    }
}
