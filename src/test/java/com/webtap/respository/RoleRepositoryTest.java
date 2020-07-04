package com.webtap.respository;

import com.webtap.domain.entity.Role;
import com.webtap.repository.OrganizationRepository;
import com.webtap.repository.RoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleRepositoryTest {

	@Autowired
	private RoleRepository roleRepository;

	@Test
	public void queryRoleListWithSelected(){
		List<Role> list = roleRepository.queryRoleListWithSelected();
		list.size();
	}


}