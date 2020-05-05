package com.webtap.web.api;

import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.entity.Organization;
import com.webtap.domain.entity.User;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.Response;
import com.webtap.service.OrganizationService;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class OrganizationController extends BaseController{

    @Autowired
    private OrganizationService organizationService;


	/**
	 * return all organizations
	 * @return
	 */
	@RequestMapping(value = "/organizations", method = RequestMethod.GET)
	public ResponseEntity<List<Organization>> getOrganizations() {
		List<Organization> organizations = organizationService.getOrganizations();

		if (organizations.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Organization>>(organizations, HttpStatus.OK);
	}

	/**
	 * 根据id获取组织信息
	 * @return
	 */
	@RequestMapping(value = "/organization",method = RequestMethod.GET)
	public ResponseEntity<Organization> getOrganization(){

        Organization organization = null;
	    User user = getUser();

	    if(user!=null&&user.getOrgId()!=null){
            organization = organizationService.getOrganizationById(user.getOrgId());
        }else {
	        organization = organizationService.getOrganizations().get(0);
        }

		if(organization ==null){
			return  new ResponseEntity(HttpStatus.MULTI_STATUS);
		}
		return  new ResponseEntity<Organization>(organization,HttpStatus.OK);
	}

	@RequestMapping(value = "/organization/save",method = RequestMethod.POST)
	@LoggerManage(description = "save org")
	public Response saveOrg(@RequestBody Organization organization){
	    try {
            organizationService.saveOrg(organization);
        }catch (Exception ex){
	        logger.error(ex.getMessage());
        }
        return result(ExceptionMsg.SUCCESS);
    }

}