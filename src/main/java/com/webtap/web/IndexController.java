package com.webtap.web;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.*;

import com.webtap.service.AppsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{


    @Autowired
    private AppsService appsService;

	@RequestMapping(value="/{url}",method=RequestMethod.GET)
	@LoggerManage(description="首页")
	public String index(@PathVariable String url,Model model){
		List<Apps> appsList = appsService.getAppsByShortUrl(url);
		if(appsList!=null){
			model.addAttribute("appList",appsList);
		}
		return "index";
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	@LoggerManage(description="登陆页面")
	public String login() {
		return "login";
	}

	@RequestMapping(value="/reset-password",method=RequestMethod.GET)
	@LoggerManage(description = "重置密码页面")
	public String newPassword(String email) {
		return "user/reset-password";
	}

	@RequestMapping(value = "/admin/setting/organization",method = RequestMethod.GET)
	@LoggerManage(description = "管理后台")
	public String admin(){
		return  "admin/setting/organization";
	}

	@RequestMapping(value = "/admin/app/list",method = RequestMethod.GET)
	@LoggerManage(description = "应用管理")
	public String apps(){return  "admin/app/list";}

	@RequestMapping(value = "/admin/app/edit",method = RequestMethod.GET)
	@LoggerManage(description = "编辑应用")
	public String appEdit(){return  "admin/app/edit";}

}