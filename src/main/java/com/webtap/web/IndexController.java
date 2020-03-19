package com.webtap.web;

import com.webtap.comm.Const;
import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.*;

import com.webtap.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController extends BaseController{



	@RequestMapping(value="/",method=RequestMethod.GET)
	@LoggerManage(description="首页")
	public String index(Model model){
		return "index";
	}

	@RequestMapping(value="/{url}",method=RequestMethod.GET)
	@LoggerManage(description="短地址首页")
	public String index(@PathVariable String url,Model model){
		return "index";
	}

	@RequestMapping(value="/login",method=RequestMethod.GET)
	@LoggerManage(description="登陆页面")
	public String signin() {
		return "user/login";
	}

	@RequestMapping(value="/reset-password",method=RequestMethod.GET)
	@LoggerManage(description = "重置密码页面")
	public String newPassword(String email) {
		return "user/reset-password";
	}

	@RequestMapping(value = "/admin/settings/organization",method = RequestMethod.GET)
	@LoggerManage(description = "管理后台")
	public String admin(){
		return  "admin/settings/organization";
	}

    @RequestMapping(value = "/admin/settings/profile",method = RequestMethod.GET)
    @LoggerManage(description = "个人资料")
    public String profile(){
        return  "admin/settings/profile";
    }

	@RequestMapping(value = "/admin/app/list",method = RequestMethod.GET)
	@LoggerManage(description = "应用管理")
	public String apps(){return  "admin/app/list";}

	@RequestMapping(value = "/admin/app/edit",method = RequestMethod.GET)
	@LoggerManage(description = "新增应用")
	public String appAdd(){return  "admin/app/edit";}

	@RequestMapping(value = "/admin/app/category",method = RequestMethod.GET)
	@LoggerManage(description = "应用分类")
	public String categories(){return  "admin/app/category";}

	@RequestMapping(value = "/admin/settings/users",method = RequestMethod.GET)
	@LoggerManage(description = "用户列表")
	public String users(){return  "admin/settings/users";}


	@RequestMapping(value="/signout",method=RequestMethod.GET)
	@LoggerManage(description="sign out")
	public void logout(HttpServletResponse response, Model model){
	    try {
            getSession().removeAttribute(Const.LOGIN_SESSION_KEY);
            getSession().removeAttribute(Const.LAST_REFERER);
            Cookie cookie = new Cookie(Const.LOGIN_SESSION_KEY, "");
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect("/");
        } catch (Exception ex){
	        logger.error(ex.getStackTrace().toString());
        }

	}
}