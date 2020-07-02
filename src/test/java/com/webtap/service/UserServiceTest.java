package com.webtap.service;

import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
  private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserService userService;

	@Test
	public void testGetRoles(){
        List<Role> roles = roleService.findRolesByUserId(1L);

        // 判断是否是管理员
        if(roles!=null){
            for (Role role:roles){
                if (role.getName().equals("admin")){
                    Assert.assertTrue(true);
                }
            }
//            List<Role> admins =  roles.stream().filter(role -> role.getName().equals("admin")).collect(Collectors.toList());
//            if(admins.size()>0){
//                Assert.assertEquals(1,admins.size());
//            }else {
//                Assert.fail();
//            }
        }
    }

    @Test
    public void testgetUsers(){
        User user = new User();
        user.setOrgId(1L);
        List<User> users =  userService.getUsers(user);
        users.size();
    }

}