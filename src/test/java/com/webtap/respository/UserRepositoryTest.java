package com.webtap.respository;

import com.webtap.domain.entity.App;
import com.webtap.domain.entity.AppCategory;
import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.User;
import com.webtap.domain.view.UserVO;
import com.webtap.repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

	@Autowired
  private UserRoleRepository userRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void  testRoles(){
	    List<Role> roles = roleRepository.findRolesByUserId(1L);
        List<Role> admins =  roles.stream().filter(role -> role.getName().equals("admin")).collect(Collectors.toList());
        if(admins.size()>0){
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testUsers(){
		List<User> list = userRepository.findAllByRoleId(1L);
		for(Object user:list){
			User user1 =(User)user;
		}
	}

	@Test
	public void testfindAllByOrgId(){
		List<User> list = userRepository.findAllByOrgId(1L);
		for(Object user:list){
			User user1 =(User)user;
		}
	}

//	public void testfindAllByOrgId2(){
//		List<User> list = userRepository.findAllByOrgId3(1L);
//
//		for(User user:list){
//			User user1 =user;
//		}
//	}

}