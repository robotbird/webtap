package com.webtap.respository;

import com.webtap.domain.entity.AppCategory;
import com.webtap.repository.AppCategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppCategoryRepositoryTest {

	@Autowired
  private AppCategoryRepository appCategoryRepository;

	@Test
	public void testUpdateAppAmount() throws Exception {
           //appCategoryRepository.updateAppAmount(1L,-2L);
	}

	@Test
	public void testAppCategories(){
		List<AppCategory> categories = appCategoryRepository.findAllByOrgId(1L);
		for(AppCategory appCategory:categories){

		}
	}

}