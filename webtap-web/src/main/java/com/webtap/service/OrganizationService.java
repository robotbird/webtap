package com.webtap.service;

import com.webtap.domain.entity.Organization;

import java.util.List;
import java.util.Optional;

public interface OrganizationService {

	Organization getOrganizationByShortUrl(String shortUrl);
	Optional<Organization> getOrganizationById(Long Id);
	List<Organization> getOrganizations();
	Organization saveOrg(Organization organization);
	void deleteOrg(Long Id);
}
