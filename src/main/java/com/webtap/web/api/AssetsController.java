package com.webtap.web.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.webtap.domain.entity.*;
import com.webtap.domain.enums.RoleTypeEnum;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.ResponseData;
import com.webtap.service.*;
import com.webtap.utils.Pager;
import com.webtap.utils.URLUtil;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api")
public class AssetsController extends BaseController{

	@Autowired
	private StorageService storageService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private RoleResourceService roleResourceService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private RoleService roleService;
	/**
	 * 返回文件列表
	 * @param page
	 * @param filter
	 * @param search
	 * @return
	 */
	@RequestMapping(value = "/assets")
	public Map<String,Object> getFiles(@RequestParam(value = "page",defaultValue = "0") Integer page,
										   @RequestParam(value = "filter",defaultValue = "") String  filter,
										   @RequestParam(value = "search",defaultValue = "") String search){
		Pager pager = new Pager(page,10);
		List<Asset> assetList = storageService.Find(pager,"upload/logo",search);

  			Map<String,Object> maps = new HashMap<>();
  			maps.put("assets",assetList);
  			maps.put("pager", pager);
  			return maps;
		}

    /**
     * 文件上传
     * @param request
     * @return
     */
	@RequestMapping(value = "/assets/upload", method = RequestMethod.POST)
	public ResponseData fileUpload(HttpServletRequest request) {
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
		MultipartFile file = null;
		for (int i = 0; i < files.size(); ++i) {
			file = files.get(i);
			if (!file.isEmpty()) {
                storageService.UploadFormFile(file,"upload/logo");
			}
		}
		return new ResponseData(ExceptionMsg.SUCCESS);
	}

	/**
	 * delete file
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/assets/remove",method = RequestMethod.DELETE)
	public ResponseData removeFile(@RequestParam(value = "url") String url){

		storageService.DeleteFile(url);
        return new ResponseData(ExceptionMsg.SUCCESS);
	}

	/**
	 * get background-img
	 * @return
	 */
	@RequestMapping(value = "/assets/background",method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public String getBackground(){

		String bing = "https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=zh-cn";
		String imgurl="";

		try {
            String content = URLUtil.getURLContent(bing);

            JSONObject jsonObject = JSONObject.parseObject(content);
            Object o = jsonObject.get("images");
            JSONArray array = (JSONArray)o;
            JSONObject object = (JSONObject)array.get(0);
            Object url = object.get("url");
            imgurl = "https://cn.bing.com/"+url;
        } catch (Exception ex){
		    logger.error("fail to connect bing");
        }

		return imgurl;
	}

	/**
	 * get all menu
	 * @return
	 */
	@RequestMapping(value = "/menu/list", method = RequestMethod.GET)
	public ResponseEntity<List<Resource>> getMenu() {
		List<Resource> menus = resourceService.findAll();

		//获取用户角色
        User user = getUser();
        List<Role> roles = roleService.findRolesByUserId(user.getId());

        // 判断是否是管理员
        if(roles!=null){
          List<Role> admins =  roles.stream().filter(role -> role.getName().equals(RoleTypeEnum.ADMIN.getDesc())).collect(Collectors.toList());
          if(admins.size()>0){
              //如果是管理员则显示全部
              return new ResponseEntity<List<Resource>>(menus, HttpStatus.OK);
          }else {
              //否则只显示个人信息
              List<Resource> list = menus.stream().filter(m->m.getPermission().equals("1")).collect(Collectors.toList());
              return new ResponseEntity<List<Resource>>(list, HttpStatus.OK);
          }
        }
      //TODO:个人只显示自己的添加或者收藏的应用


		if (menus.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Resource>>(menus, HttpStatus.OK);
	}

}