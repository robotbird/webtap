package com.webtap.web.api;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.entity.App;
import com.webtap.domain.entity.AppCategory;
import com.webtap.domain.entity.Organization;
import com.webtap.domain.entity.User;
import com.webtap.domain.enums.ViewPermission;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.Response;
import com.webtap.service.AppCategoryService;
import com.webtap.service.AppService;
import com.webtap.service.OrganizationService;
import com.webtap.utils.StringUtil;
import com.webtap.web.BaseController;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class 	AppsController extends BaseController{

    @Autowired
    private AppService appService;

    @Autowired
    private AppCategoryService appCategoryService;

    @Autowired
    private OrganizationService  organizationService;



	/**
	 * 列出所有 app 和分类
	 * @return
	 */
	@RequestMapping(value = "/apps",  method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getAppsAndCats() {

		List<App> appList = appService.getAllApps();

		List<AppCategory> categoryList = appCategoryService.getAppCategories();

        JSONObject result = appListJson(appList,categoryList);

		return result.toJSONString();
	}


	/**
	 * 根据短链接获取app
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/apps/{url}", method = RequestMethod.GET)
	public String getAppsByShortUrl(@PathVariable(value = "url") String url) {

        Organization organization = organizationService.getOrganizationByShortUrl(url);
        if(organization ==null){
            return null;
        }

		List<App> appList = appService.getAppsByOrgShortUrl(url);

        List<AppCategory> categoryList = appCategoryService.getAppCategories(organization.getId());

        JSONObject result = appListJson(appList,categoryList);

        return result.toJSONString();
	}


    /**
     * 组合app和category
     * @param appList
     * @param categoryList
     * @return
     */
	private JSONObject appListJson(List<App> appList, List<AppCategory> categoryList){
        User user = getUser();
        //viewPermission 查看权限0全部,1登录,2自己,3指定角色
        // 默认先加载 全部可见的数据
        List<App> apps = null;

        //全部可见
        Long publicValue = ViewPermission.PUBLIC.getValue();
        apps = appList.stream().filter(a->a.getViewPermission()==null||publicValue.equals(a.getViewPermission())).collect(Collectors.toList());

        if(user != null){
            // 登录权限 1
            Long login = ViewPermission.LOGIN.getValue();
            List<App>  apps4login = appList.stream().filter(a->login.equals(a.getViewPermission())).collect(Collectors.toList());
            apps.addAll(apps4login);// 合并List


            // 自己可见 2
            Long my = ViewPermission.PRIVATE.getValue();
            List<App>  apps4My = appList.stream().filter(a->my.equals(a.getViewPermission())).collect(Collectors.toList());
            apps.addAll(apps4My);
        }


        List<App> appsAll = null;
        try {
            appsAll = StringUtil.deepCopy(apps);
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        } catch (ClassNotFoundException e) {
            logger.error(e.getLocalizedMessage());
        }

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
        return result;
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

    @RequestMapping(value = "/app/geturl", method = RequestMethod.GET)
    public String getAppsByIdAndPwd(@RequestParam(value = "id") Long id,@RequestParam(value = "password") String password) {

        App app = appService.getAppById(id);
        if(app.getViewPassword().equals(password)){
            return app.getUrl();
        }else {
            return "";
        }
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
            // 暂时不启用短链服务
           // if(app.getShortUrl()==null||app.getShortUrl()==""){
//                Long maxId=null;
//                Long id =app.getId();
//                if(id==null){
//                    maxId = appService.getMaxId()+1;
//                }else {
//                    maxId = id;
//                }
//
//                String url = maxId.toString();
//
//                String[] arrurl = URLUtil.getShortUrl(url);
//
//                for (String shortUrl: arrurl){
//                    App app1 = appService.getAppByShortUrl(shortUrl);
//                    if(app1 == null){
//                        app.setShortUrl(shortUrl);
//                        break;
//                    }
//                }
            //}

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

        User user = getUser();
        AppCategory appCategory =null;
        try{
            category.setOrgId(user.getOrgId());
            category.setUserId(user.getId());

            if(category.getAppAmount()==null){
                category.setAppAmount(0L);
            }

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