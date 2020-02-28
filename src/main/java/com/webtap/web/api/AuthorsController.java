package com.webtap.web.api;

import com.webtap.domain.Organization;
import com.webtap.domain.User;
import com.webtap.domain.result.Response;
import com.webtap.service.OrganizationService;
import com.webtap.service.UserService;
import com.webtap.web.BaseController;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class AuthorsController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUserProfile(){
        User user = getUser();
        Organization organization = null;
        if(user!=null&&user.getOrgId()!=null){
            organization = organizationService.getOrganizationById(user.getOrgId());
        }

        if(organization ==null){
            List<Organization> organizationList = organizationService.getOrganizations();
            organization = organizationList.get(0);
        }

        JSONObject json = new JSONObject();
        json.put("user",user);
        json.put("org",organization);

        return json.toJSONString();
    }

}