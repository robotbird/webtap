package com.webtap.service.impl;

import com.webtap.domain.Organization;
import com.webtap.repository.OrganizationRepository;
import com.webtap.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("groupService")
public class OrganizationServiceImpl implements OrganizationService {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private OrganizationRepository organizationRepository;

    public Organization getGroupByShortUrl(String shortUrl) {
        Organization organization = organizationRepository.findByShortUrl(shortUrl);
        return organization;
    }

    public Organization getGroupById(Long Id){
        return organizationRepository.findById(Id);
    }


}