package com.webtap.service;


/**
 * @author robotbird
 * @version 1.0
 * @website http://webtap.cn
 * @date 2020-04-25 20:23
 **/
public interface SysUserRoleService   {

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
