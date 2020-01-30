package com.webtap.respository;

import com.webtap.repository.AppsRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppsRepositoryTest {

	@Autowired
  private AppsRepository appsRepository;

	@Test
	public void testApps() throws Exception {
		long cnt = appsRepository.findAllByOrgId(1l).size();
		Assert.assertEquals(cnt,1);
	}

}