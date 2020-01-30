package com.webtap.service;

import com.webtap.domain.Apps;

import java.util.List;

public interface AppsService {

	public  List<Apps> getAppsByShortUrl(String shortUrl);
	public List<Apps> getAppsByOrgId(Long groupId);

}
