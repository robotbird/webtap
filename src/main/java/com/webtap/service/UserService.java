package com.webtap.service;

import com.webtap.domain.User;

public interface UserService {


    /**
     * get user by username and password
     * @param username
     * @param password
     * @return
     */
    public User getUser(String username,String password);

    /**
     * 新增一个用户
     * @param name
     * @param age
     */
    void create(String name, Integer age);

    /**
     * 根据name删除一个用户高
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总量
     */
    Integer getAllUsers();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();

}