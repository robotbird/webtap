package com.webtap.respository;

import com.webtap.repository.AppsRepository;
import com.webtap.repository.GroupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupRepositoryTest {

	@Autowired
  private GroupRepository groupRepository;

	@Test
	public void testApps() throws Exception {
		String url = groupRepository.findByShortUrl("utry").getShortUrl();
		Assert.assertEquals(url,"utry");
	}

}