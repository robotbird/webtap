package com.webtap.service.impl;

import com.webtap.domain.App;
import com.webtap.domain.AppCategory;
import com.webtap.domain.Organization;
import com.webtap.repository.AppCategoryRepository;
import com.webtap.repository.AppRepository;
import com.webtap.repository.OrganizationRepository;
import com.webtap.service.AppCategoryService;
import com.webtap.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

	public List<AppCategory> getAppCategories() {
		return appCategoryRepository.findAll();
	}

	public AppCategory save(AppCategory appCategory) {
		return appCategoryRepository.save(appCategory);
	}

	public AppCategory getAppCategory(Long id) {
		return appCategoryRepository.findOne(id);
	}

	public void removeCategory(Long id) {
		appCategoryRepository.delete(id);
	}
}