package com.webtap.web.api;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.Apps;
import com.webtap.domain.result.Response;
import com.webtap.service.AppsService;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AppsController extends BaseController{

    @Autowired
    private AppsService appsService;

	/**
	 * 根据短链接获取app
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/apps/{url}", method = RequestMethod.GET)
	public ResponseEntity<List<Apps>> getAppsByShortUrl(@PathVariable(value = "url") String url) {
		List<Apps> apps = appsService.getAppsByShortUrl(url);

		if (apps.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Apps>>(apps, HttpStatus.OK);
	}

	/**
	 * 添加应用
	 * @param apps
	 * @return
	 */
	@RequestMapping(value = "/apps/save", method = RequestMethod.POST)
	@LoggerManage(description = "添加应用")
	public Response saveApp(@RequestBody Apps apps) {
        Apps app = appsService.saveApp(apps);
		logger.debug("保存app成功");
		return result();
	}


}