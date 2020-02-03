package com.webtap.service;

import com.webtap.domain.Apps;

import java.util.List;

public interface AppsService {

	/**
	 * get app info by short url
	 * @param shortUrl
	 * @return
	 */
	public  List<Apps> getAppsByShortUrl(String shortUrl);

	/**
	 * get app info by orgId
	 * @param groupId
	 * @return
	 */
	public List<Apps> getAppsByOrgId(Long groupId);

	/**
	 * save app
	 * @param apps
	 * @return
	 */
	public Apps saveApp(Apps apps);

	/**
	 * delete app by id
	 * @param id
	 */
	public void removeApp(Long id);

}
