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
	public  List<App> getAppsByOrgShortUrl(String shortUrl);

	/**
	 * get app by id
	 * @param Id
	 * @return
	 */
	public App getAppById(Long Id);


    /**
     * get app by id and view password
     * @param id
     * @param password
     * @return
     */
	public String getAppUrl(Long id,String password);

    /**
     * get app's max id
     * @return
     */
	public Long getMaxId();


	/**
	 * get app by app's short url
	 * @param shortUrl
	 * @return
	 */
	public App getAppByShortUrl(String shortUrl);

	/**
	 * get app info by orgId
	 * @param groupId
	 * @return
	 */
	public List<App> getAppsByOrgId(Long groupId);

	/**
	 * get apps by categoryId
	 * @param categoryId
	 * @return
	 */
	public List<App> getAppsByCategory(Long categoryId);

	/**
	 * get apps by title query
	 * @param title
	 * @return
	 */
	public List<App> getAppsByTitle(String title);


	/**
	 * get apps by  title and categoryId
	 * @param title
	 * @param categoryId
	 * @return
	 */
	public List<App> getAppsByTitleAndCategoryId(String title,Long categoryId);

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
