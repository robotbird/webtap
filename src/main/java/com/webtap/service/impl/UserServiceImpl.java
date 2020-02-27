package com.webtap.service.impl;

import com.webtap.domain.User;
import com.webtap.repository.UserRepository;
import com.webtap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Resource
    private JavaMailSender mailSender;


    @Override
    public User getUser(String username, String email) {
        return userRepository.findByUserNameOrEmail(username,email);
    }

    @Override
    public void create(String name, Integer age) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public Integer getAllUsers() {
        return null;
    }

    @Override
    public void deleteAllUsers() {

    }
}
