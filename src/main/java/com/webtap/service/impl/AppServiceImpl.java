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

    public List<App> getAppsByShortUrl(String shortUrl) {
        Organization organization = organizationRepository.findByShortUrl(shortUrl);
        if(organization ==null){
            return null;
        }
        return  this.getAppsByOrgId(organization.getId());
    }

    public App getAppById(Long Id) {
        App app = appRepository.findOne(Id);
        return app;
    }

    public List<App> getAppsByOrgId(Long orgId)
   {
   	    return appRepository.findAllByOrgId(orgId);
   }

   public App saveApp(App app){
	    if(app.getCategoryId()>0){
            appCategoryRepository.updateAppAmount(app.getCategoryId(),1L);
        }
        return appRepository.save(app);
   }

   public void removeApp(Long id){
	    App app = appRepository.findOne(id);
       if(app.getCategoryId()>0){
           appCategoryRepository.updateAppAmount(app.getCategoryId(),-1L);
       }
        appRepository.delete(id);
   }

}