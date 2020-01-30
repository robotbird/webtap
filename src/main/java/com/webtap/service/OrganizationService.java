package com.webtap.service;

import com.webtap.domain.Organization;

public interface OrganizationService {

	public Organization getGroupByShortUrl(String shortUrl);
	public Organization getGroupById(Long Id);
}
