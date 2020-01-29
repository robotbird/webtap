package com.webtap.web.api;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.Apps;
import com.webtap.domain.Group;
import com.webtap.service.AppsService;
import com.webtap.service.GroupService;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GroupController extends BaseController{

    @Autowired
    private GroupService groupService;

	/**
	 * 根据id获取组织信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/group/{id}",method = RequestMethod.GET)
	public ResponseEntity<Group> getGroupById(@PathVariable(value = "id") Long id){
		Group group = groupService.getGroupById(id);
		if(group ==null){
			return  new ResponseEntity(HttpStatus.MULTI_STATUS);
		}
		return  new ResponseEntity<Group>(group,HttpStatus.OK);
	}

}