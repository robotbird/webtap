package com.webtap.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppsServiceTest {

	@Autowired
  private AppsService appsService;

	@Test
	public void testApps() throws Exception {
		long cnt = appsService.getAppsByOrgId(1l).size();
		Assert.assertEquals(cnt,1);
	}

}