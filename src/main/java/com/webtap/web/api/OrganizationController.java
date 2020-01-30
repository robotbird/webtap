package com.webtap.web.api;

import com.webtap.domain.Organization;
import com.webtap.service.OrganizationService;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class OrganizationController extends BaseController{

    @Autowired
    private OrganizationService organizationService;

	/**
	 * 根据id获取组织信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/organization/{id}",method = RequestMethod.GET)
	public ResponseEntity<Organization> getOrganizationById(@PathVariable(value = "id") Long id){
		Organization organization = organizationService.getOrganizationById(id);
		if(organization ==null){
			return  new ResponseEntity(HttpStatus.MULTI_STATUS);
		}
		return  new ResponseEntity<Organization>(organization,HttpStatus.OK);
	}

}