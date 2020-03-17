package com.webtap.service;

import com.webtap.domain.User;

import java.util.List;

public interface UserService {


    /**
     * get user by username and email
     * @param username
     * @param email
     * @return
     */
    public User getUser(String username,String email);

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);

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
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * return all user
     */
    public List<User>  getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

}