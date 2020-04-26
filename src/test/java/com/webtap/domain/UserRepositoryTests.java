package com.webtap.domain;

import com.webtap.domain.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.webtap.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() throws Exception {

		userRepository.save(new User("aa", "aa@126.com", "aa", "aa123456"));
		userRepository.save(new User("bb", "bb@126.com", "bb", "bb123456"));
		userRepository.save(new User("cc", "cc@126.com", "cc", "cc123456"));
		Assert.assertEquals(9, userRepository.findAll().size());

		Assert.assertEquals("aa", userRepository.findByUserName("aa").getUserName());

	}


}