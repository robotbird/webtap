package com.webtap.service;

import com.github.pagehelper.PageInfo;
import com.webtap.core.object.AbstractService;
import com.webtap.domain.entity.Role;
import com.webtap.domain.view.RoleVO;

import java.util.*;

/**
 * 角色
 *
 * @author robotbird
 * @version 1.0
 * @website https://webtap.cn
 * @date 2020-04-24
 * @since 1.0
 */
public interface RoleService extends AbstractService<Role,Long> {
    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    public PageInfo<Role> findPageBreakByCondition(RoleVO vo);

    /**
     * 获取用户的角色
     *
     * @param userId
     * @return
     */
    List<Role> findRolesByUserId(Long userId);


    /**
     * 获取角色列表
     *
     * @param userId
     * @return
     */
    public List<Map<String, Object>> queryRoleListWithSelected(Long userId);
}
