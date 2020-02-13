package com.webtap.web.api;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.App;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.Response;
import com.webtap.service.AppService;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")
public class AppsController extends BaseController{

    @Autowired
    private AppService appService;


	/**
	 * 列出所有app
	 * @return
	 */
	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public ResponseEntity<List<App>> getAllApps() {
		List<App> apps = appService.getAllApps();

		if (apps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<App>>(apps, HttpStatus.OK);
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
			 appService.saveApp(app);
			 logger.info("保存app成功");

		} catch (Exception ex){
			return result(ex.getMessage());
		}
		return result(ExceptionMsg.SUCCESS);
	}

	/**
	 * 删除 app
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "apps/remove/{id}",method = RequestMethod.DELETE)
	public Response deleteApp(@PathVariable(value = "id") Long id){
		appService.removeApp(id);
		logger.info("删除成功");
		return result(ExceptionMsg.SUCCESS);
	}

}