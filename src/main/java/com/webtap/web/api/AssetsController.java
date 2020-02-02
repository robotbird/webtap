package com.webtap.web.api;

import com.webtap.domain.Asset;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.ResponseData;
import com.webtap.service.StorageService;
import com.webtap.utils.Pager;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class AssetsController extends BaseController{

	@Autowired
	private StorageService storageService;

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

}