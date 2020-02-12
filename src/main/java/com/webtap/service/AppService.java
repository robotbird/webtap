package com.webtap.service;

import com.webtap.domain.App;

import java.util.List;

public interface AppService {


	/**
	 * get all apps
	 * @return
	 */
	public List<App> getAllApps();

	/**
	 * get app list info by short url
	 * @param shortUrl
	 * @return
	 */
	public  List<App> getAppsByShortUrl(String shortUrl);

	/**
	 * get app by id
	 * @param Id
	 * @return
	 */
	public App getAppById(Long Id);

	/**
	 * get app info by orgId
	 * @param groupId
	 * @return
	 */
	public List<App> getAppsByOrgId(Long groupId);

	/**
	 * save app
	 * @param app
	 * @return
	 */
	public App saveApp(App app);

	/**
	 * delete app by id
	 * @param id
	 */
	public void removeApp(Long id);

}
