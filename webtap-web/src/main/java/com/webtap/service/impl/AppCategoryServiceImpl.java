package com.webtap.service.impl;

import com.webtap.domain.entity.AppCategory;
import com.webtap.repository.AppCategoryRepository;
import com.webtap.repository.AppRepository;
import com.webtap.repository.OrganizationRepository;
import com.webtap.service.AppCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import java.util.List;


@Service("appCategoryService")
public class AppCategoryServiceImpl implements AppCategoryService {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private AppRepository appRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired
	private AppCategoryRepository appCategoryRepository;

	public List<AppCategory> getAppCategories(AppCategory category) {

		ExampleMatcher matcher = ExampleMatcher.matching()
				.withMatcher("orgId", ExampleMatcher.GenericPropertyMatchers.contains())
				.withMatcher("userId",ExampleMatcher.GenericPropertyMatchers.contains());
		Example<AppCategory> example = Example.of(category ,matcher);
        return appCategoryRepository.findAll(example);
	}

	public List<AppCategory> getAppCategories(Long orgid) {
		return appCategoryRepository.findAllByOrgId(orgid);
	}

	public AppCategory save(AppCategory appCategory) {
		return appCategoryRepository.save(appCategory);
	}

	public AppCategory getAppCategory(Long id) {
		return appCategoryRepository.findById(id).get();
	}

	public void removeCategory(Long id) {
		appCategoryRepository.delete(id);
	}
}