package com.webtap.service;
import com.webtap.domain.entity.App;
import com.webtap.domain.view.AppVO;

import java.util.List;

public interface AppService {


	/**
	 * get all apps
	 * @return
	 */
    List<App> getAllApps();

	/**
	 * get app list info by short url
	 * @param shortUrl
	 * @return
	 */
    List<App> getAppsByOrgShortUrl(String shortUrl);

	/**
	 * get app by id
	 * @param Id
	 * @return
	 */
    App getAppById(Long Id);


    /**
     * get app by id and view password
     * @param id
     * @param password
     * @return
     */
    String getAppUrl(Long id, String password);

    /**
     * get app's max id
     * @return
     */
    Long getMaxId();


	/**
	 * get app by app's short url
	 * @param shortUrl
	 * @return
	 */
    App getAppByShortUrl(String shortUrl);

	/**
	 * get app info by orgId
	 * @param orgId
	 * @return
	 */
    List<App> getAppsByOrgId(Long orgId);

	/**
	 * get apps by categoryId
	 * @param categoryId
	 * @return
	 */
    List<App> getAppsByCategory(Long categoryId);

	/**
	 * get apps by app example
	 * @param app
	 * @return
	 */
    List<App> getApps(App app);
	/**
	 * get apps by title query
	 * @param title
	 * @return
	 */
    List<App> getAppsByTitle(String title);


	/**
	 * get apps by  title and categoryId
	 * @param title
	 * @param categoryId
	 * @return
	 */
    List<App> getAppsByTitleAndCategoryId(String title, Long categoryId);

	/**
	 * save app
	 * @param app
	 * @return
	 */
    App saveApp(App app);

    /**
     * update app's view password
     * @param id
     * @param password
     */
    void updatePassword(Long id, String password);

	/**
	 * delete app by id
	 * @param id
	 */
    void removeApp(Long id);

}
