package com.webtap.service;

import com.github.pagehelper.PageInfo;
import com.webtap.domain.entity.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author robotbird
 * @version 1.0
 * @website http://webtap.cn
 * @date 2020-04-25 20:23
 **/
public interface SysResourcesService   {

    /**
     * 分页查询
     *
     * @param resource
     * @return
     */
    PageInfo<Resource> findPageBreakByCondition(Resource resource);

    /**
     * 获取用户的资源列表
     *
     * @param map
     * @return
     */
    List<Resource> listUserResources(Map<String, Object> map);

    /**
     * 获取ztree使用的资源列表
     *
     * @param rid
     * @return
     */
    List<Map<String, Object>> queryResourcesListWithSelected(Long rid);

    /**
     * 获取资源的url和permission
     *
     * @return
     */
    List<Resource> listUrlAndPermission();

    /**
     * 获取所有可用的菜单资源
     *
     * @return
     */
    List<Resource> listAllAvailableMenu();

    /**
     * 获取用户关联的所有资源
     *
     * @param userId
     * @return
     */
    List<Resource> listByUserId(Long userId);
}
