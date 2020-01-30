package com.webtap.service;

import com.webtap.domain.Organization;

public interface OrganizationService {

	public Organization getOrganizationByShortUrl(String shortUrl);
	public Organization getOrganizationById(Long Id);
}
