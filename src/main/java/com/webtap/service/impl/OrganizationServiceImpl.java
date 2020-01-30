package com.webtap.service.impl;

import com.webtap.domain.Organization;
import com.webtap.repository.OrganizationRepository;
import com.webtap.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private OrganizationRepository organizationRepository;

    public Organization getOrganizationByShortUrl(String shortUrl) {
        Organization organization = organizationRepository.findByShortUrl(shortUrl);
        return organization;
    }

    public Organization getOrganizationById(Long Id){
        return organizationRepository.findById(Id);
    }


}