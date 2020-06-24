package com.webtap.service;


import com.webtap.core.object.AbstractService;
import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.UserRole;

import java.util.List;

/**
 * @author robotbird
 * @version 1.0
 * @website http://webtap.cn
 * @date 2020-04-25 20:23
 **/
public interface UserRoleService extends AbstractService<UserRole,Long> {

    /**
     * 添加用户角色
     *
     * @param userId
     * @param roleIds
     */
    void addUserRole(Long userId, String roleIds);

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     */
    void removeByUserId(Long userId);

}
