package com.webtap.web.api;

import com.webtap.comm.Const;
import com.webtap.comm.aop.LoggerManage;
import com.webtap.domain.Organization;
import com.webtap.domain.User;
import com.webtap.domain.result.ExceptionMsg;
import com.webtap.domain.result.Response;
import com.webtap.domain.view.ChangePwd;
import com.webtap.service.OrganizationService;
import com.webtap.service.UserService;
import com.webtap.web.BaseController;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * return all users
     * @return
     */
    @RequestMapping(value = "/authors/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/authors/update",method = RequestMethod.POST)
    @LoggerManage(description = "update user")
    public Response saveUser(@RequestBody User user){
        try {
            User hasuser = userService.getUser(user.getUserName(),user.getEmail());
            if(user.getId()!=hasuser.getId()){
                return result("用户名或者邮箱重复");
            }
             userService.update(user);
            getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
        }catch (Exception ex){
            logger.error(ex.getMessage());
        }
        return result(ExceptionMsg.SUCCESS);
    }

    @RequestMapping(value = "/authors/changepwd",method = RequestMethod.PUT)
    @LoggerManage(description = "change password")
    public Response changepwd(@RequestBody ChangePwd changePwd){

        try {
            User user = getUser();
            String password = user.getPassWord();
            String newpwd = getPwd(changePwd.getNewPassword());
            if(password.equals(getPwd(changePwd.getOldPassword()))){
                userService.updatePwd(newpwd,user.getUserName());
                user.setPassWord(newpwd);
                getSession().setAttribute(Const.LOGIN_SESSION_KEY, user);
            }else{
                return result(ExceptionMsg.PassWordError);
            }
        } catch (Exception e) {
            logger.error("updatePassword failed, ", e);
            return result(ExceptionMsg.FAILED);
        }


        return result(ExceptionMsg.SUCCESS);
    }

}