package com.webtap.service;

import com.webtap.domain.entity.User;

import java.util.List;

public interface UserService {


    /**
     * 获取用户
     * @param id
     * @return
     */
    User getUser(Long id);

    /**
     * user name
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);


    /**
     * email
     * @param email
     * @return
     */
    User getUserByEmail(String email);

    /**
     * get user by username and email
     * @param username
     * @param email
     * @return
     */
    User getUser(String username, String email);

    /**
     * 新增一个用户
     * @param user
     */
    void create(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * update user's password
     * @param password
     * @param userName
     */
    void updatePwd(String password,String userName);
    /**
     * delete by user id
     * @param id
     */
    void delete(Long id);

     /**
     * return all user
     */
     List<User> getUsers(User user);

    /**
     * return users by roleId
     * @param roleId
     * @return
     */
    List<User> getUsersByRoleId(Long roleId);

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

}