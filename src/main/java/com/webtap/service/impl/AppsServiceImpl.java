package com.webtap.service.impl;

import com.webtap.domain.*;
import com.webtap.repository.*;
import com.webtap.service.AppsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service("appsService")
public class AppsServiceImpl  implements AppsService{
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private AppsRepository appsRepository;

	@Autowired
	private OrganizationRepository organizationRepository;


    public List<Apps> getAppsByShortUrl(String shortUrl) {
        Organization organization = organizationRepository.findByShortUrl(shortUrl);
        if(organization ==null){
            return null;
        }
        return  this.getAppsByOrgId(organization.getId());
    }

    public List<Apps> getAppsByOrgId(Long orgId)
   {
   	    return appsRepository.findAllByOrgId(orgId);
   }

   public Apps saveApp(Apps apps){
        return appsRepository.save(apps);
   }

   public void removeApp(Long id){
        appsRepository.deleteById(id);
   }

}