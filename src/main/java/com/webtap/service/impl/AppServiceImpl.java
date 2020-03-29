package com.webtap.service.impl;

import com.webtap.domain.*;
import com.webtap.repository.*;
import com.webtap.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("appsService")
public class AppServiceImpl implements AppService {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private AppRepository appRepository;

	@Autowired
	private AppCategoryRepository appCategoryRepository;

	@Autowired
	private OrganizationRepository organizationRepository;


	public List<App> getAllApps(){
	    Sort sort = new Sort("sortNum");
	    return appRepository.findAll(sort);
    }

    public List<App> getAppsByOrgShortUrl(String orgShortUrl) {
        Organization organization = organizationRepository.findByShortUrl(orgShortUrl);
        if(organization ==null){
            return null;
        }
        return  this.getAppsByOrgId(organization.getId());
    }

    public App getAppByShortUrl(String shortUrl){
	    return this.appRepository.findAppByShortUrl(shortUrl);
    }

    public App getAppById(Long Id) {
        App app = appRepository.findOne(Id);
        return app;
    }

    public Long getMaxId(){
	    return appRepository.findMaxId();
    }

    public List<App> getAppsByOrgId(Long orgId)
   {
   	    return appRepository.findAllByOrgId(orgId);
   }

   public List<App> getAppsByCategory(Long categoryId){
	    return appRepository.findAllByCategoryId(categoryId);
   }

    public List<App> getAppsByTitle(String title){
        return appRepository.findAllByTitleContains(title);
    }

    public List<App> getAppsByTitleAndCategoryId(String title,Long id){
        return appRepository.findAllByTitleContainingAndCategoryId(title,id);
    }

   public App saveApp(App app){
        App return_app = appRepository.save(app);
       if(app.getCategoryId()>0){
           appCategoryRepository.updateAppAmount(app.getCategoryId());
       }
        return return_app;
   }

   public void removeApp(Long id){
	    App app = appRepository.findOne(id);
        appRepository.delete(id);
       if(app.getCategoryId()>0){
           appCategoryRepository.updateAppAmount(app.getCategoryId());
       }
   }

}