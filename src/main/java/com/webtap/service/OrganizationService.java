package com.webtap.service;

import com.webtap.domain.Organization;

import java.util.List;

public interface OrganizationService {

	public Organization getOrganizationByShortUrl(String shortUrl);
	public Organization getOrganizationById(Long Id);
	public List<Organization> getOrganizations();
	public void saveOrg(Organization organization);
}
