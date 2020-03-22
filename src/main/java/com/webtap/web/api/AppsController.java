package com.webtap.web.api;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.App;
import com.webtap.domain.AppCategory;
import com.webtap.domain.User;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.Response;
import com.webtap.service.AppCategoryService;
import com.webtap.service.AppService;
import com.webtap.utils.StringUtil;
import com.webtap.web.BaseController;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/api")
public class 	AppsController extends BaseController{

    @Autowired
    private AppService appService;

    @Autowired
    private AppCategoryService appCategoryService;



	/**
	 * 列出所有 app 和分类
	 * @return
	 */
	@RequestMapping(value = "/apps",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAppsAndCats() {
		List<App> apps = appService.getAllApps();
		List<App> appsAll = null;
		try {
			appsAll = StringUtil.deepCopy(apps);
		} catch (IOException e) {
			logger.error(e.getLocalizedMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getLocalizedMessage());
		}
		List<AppCategory> categoryList = appCategoryService.getAppCategories();

        Iterator<App> iteratorApp = apps.iterator();
        while(iteratorApp.hasNext()){
            App app = iteratorApp.next();
            for(AppCategory category:categoryList){
                if (app.getCategoryId()==category.getId()){
                    iteratorApp.remove();
                }
            }
        }
		JSONObject result = new JSONObject();
        result.put("appsAll",appsAll);
		result.put("apps", apps);
		result.put("categories", categoryList);
		return result.toJSONString();
	}


	/**
	 * 根据短链接获取app
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/apps/{url}", method = RequestMethod.GET)
	public ResponseEntity<List<App>> getAppsByShortUrl(@PathVariable(value = "url") String url) {
		List<App> apps = appService.getAppsByShortUrl(url);

		if (apps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<App>>(apps, HttpStatus.OK);
	}


	/**
	 * 列出所有app
	 * @return
	 */
	@RequestMapping(value = "/app/list", method = RequestMethod.GET)
	public ResponseEntity<List<App>> getAllApps(
			@RequestParam(value = "categoryId",required = false,defaultValue = "0") Long categoryId,
			@RequestParam(value = "title",required = false,defaultValue = "") String title) {

        List<App> apps = null;

        // get all app
        if(categoryId ==0&&title==""){
			apps =  appService.getAllApps();
		}

		// filter by categoryId
        if(categoryId>0&&title==""){
            apps = appService.getAppsByCategory(categoryId);
        }

        // search by title
        if(title!=null&&title!=""&&categoryId ==0){
            apps = appService.getAppsByTitle(title);
		}

		// search by title and filter by categoryId
		if(categoryId>0&&title!=""){
			apps = appService.getAppsByTitleAndCategoryId(title,categoryId);
		}

		if (apps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<App>>(apps, HttpStatus.OK);
	}
	/**
	 * 根据id接获取app
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/app/{id}", method = RequestMethod.GET)
	public ResponseEntity<App> getAppsById(@PathVariable(value = "id") Long id) {
		App app = appService.getAppById(id);
		if (app ==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return  new ResponseEntity<App>(app,HttpStatus.OK);
	}


	/**
	 * 添加应用
	 * @param app
	 * @return
	 */
	@RequestMapping(value = "/apps/save", method = RequestMethod.POST)
	@LoggerManage(description = "添加应用")
	public Response saveApp(@RequestBody App app) {
		try{
			User user = getUser();

			if(app.getUserId()==null){
                app.setUserId(user.getId());
            }

            if(app.getOrgId()==null){
                app.setOrgId(user.getOrgId());
            }

			Date date = new Date();


			if(app.getId()==null){
                app.setCreateTime(date.getTime());
            }

			app.setLastModifyTime(date.getTime());


			if(app.getIsDelete()==null){
                app.setIsDelete(0L);
            }

            if(app.getSortNum()==null){
                app.setSortNum(100000L);
            }

            if(app.getCategoryId()==null){
                app.setCategoryId(0L);
            }


			 appService.saveApp(app);
			 logger.info("保存app成功");

		} catch (Exception ex){
			return result(ex.getMessage());
		}
		return result(ExceptionMsg.SUCCESS);
	}

	/**
	 * delete app
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "apps/remove/{id}",method = RequestMethod.DELETE)
	public Response deleteApp(@PathVariable(value = "id") Long id){
		appService.removeApp(id);
		logger.info("删除成功");
		return result(ExceptionMsg.SUCCESS);
	}

	/**
	 * return all app category
	 * @return
	 */
	@RequestMapping(value = "/app/categories", method = RequestMethod.GET)
	public ResponseEntity<List<AppCategory>> getAllAppCategories() {
		List<AppCategory> categoryList = appCategoryService.getAppCategories();

		if (categoryList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AppCategory>>(categoryList, HttpStatus.OK);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<AppCategory> getCatById(@PathVariable(value = "id") Long id) {
		AppCategory category = appCategoryService.getAppCategory(id);
		if (category ==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return  new ResponseEntity<AppCategory>(category,HttpStatus.OK);
	}


    /**
     * add category
     * @param category
     * @return
     */
    @RequestMapping(value = "/category/save", method = RequestMethod.POST)
    @LoggerManage(description = "add category")
    public ResponseEntity saveAppCategory(@RequestBody AppCategory category) {
        AppCategory appCategory =null;
        try{
           appCategory = appCategoryService.save(category);
            logger.info("save app category success!");
        } catch (Exception ex){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<AppCategory>(appCategory,HttpStatus.OK);
    }


	@RequestMapping(value = "category/remove/{id}",method = RequestMethod.DELETE)
	public Response deleteCat(@PathVariable(value = "id") Long id){
		appCategoryService.removeCategory(id);
		return result(ExceptionMsg.SUCCESS);
	}

}