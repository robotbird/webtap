package com.webtap.web.api;

import com.webtap.domain.Organization;
import com.webtap.domain.User;
import com.webtap.domain.result.Response;
import com.webtap.service.OrganizationService;
import com.webtap.service.UserService;
import com.webtap.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class AuthorsController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getSigninUser(){
        String userName = getUserName();
        return userName;
    }

}