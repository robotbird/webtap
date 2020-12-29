package com.webtap.web.api;

import com.webtap.domain.entity.Resource;
import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.User;
import com.webtap.domain.enums.RoleTypeEnum;
import com.webtap.service.*;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class RolesController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;


    @Autowired
    private ResourceService resourceService;

    @Autowired
    private RoleResourceService roleResourceService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleService roleService;



    /**
     * get all roles
     * @return
     */
    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> getRoles() {

        List<Role> roles = roleService.findAll();

        if (roles.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }

    /**
     * get all menu
     * @return
     */
    @RequestMapping(value = "/menu/list", method = RequestMethod.GET)
    public ResponseEntity<List<Resource>> getMenu() {
        List<Resource> menus = resourceService.findAll();

        //获取用户角色
        User user = getUser();
        List<Role> roles = roleService.findRolesByUserId(user.getId());

        // 判断是否是管理员
        if(roles!=null){
            List<Role> admins =  roles.stream().filter(role -> role.getName().equals(RoleTypeEnum.ADMIN.getDesc())).collect(Collectors.toList());
            if(admins.size()>0){
                //如果是管理员则显示全部
                return new ResponseEntity<List<Resource>>(menus, HttpStatus.OK);
            }else {
                //否则只显示个人信息
                List<Resource> list = menus.stream().filter(m->m.getPermission().equals("1")).collect(Collectors.toList());
                return new ResponseEntity<List<Resource>>(list, HttpStatus.OK);
            }
        }

        if (menus.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Resource>>(menus, HttpStatus.OK);
    }
}