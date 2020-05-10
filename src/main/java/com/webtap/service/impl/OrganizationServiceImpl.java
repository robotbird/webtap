package com.webtap.service.impl;

import com.webtap.domain.entity.Organization;
import com.webtap.repository.AppCategoryRepository;
import com.webtap.repository.AppRepository;
import com.webtap.repository.OrganizationRepository;
import com.webtap.repository.UserRepository;
import com.webtap.service.AppService;
import com.webtap.service.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	protected Logger logger =  LoggerFactory.getLogger(this.getClass());

	@Autowired
   private OrganizationRepository organizationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AppRepository appRepository;

	@Autowired
	private AppCategoryRepository appCategoryRepository;


    public Organization getOrganizationByShortUrl(String shortUrl) {
        Organization organization = organizationRepository.findByShortUrl(shortUrl);
        return organization;
    }

    public Organization getOrganizationById(Long Id){
        return organizationRepository.findById(Id);
    }

    public List<Organization> getOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization saveOrg(Organization organization) {
       Organization org = organizationRepository.save(organization);
       return org;
    }

    public void deleteOrg(Long Id) {
        organizationRepository.deleteById(Id);
        userRepository.deleteAllByOrOrgId(Id);
        appRepository.deleteAllByOrgId(Id);
        appCategoryRepository.deleteAllByOrgId(Id);
    }


}