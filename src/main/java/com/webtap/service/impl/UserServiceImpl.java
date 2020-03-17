package com.webtap.service.impl;

import com.webtap.domain.User;
import com.webtap.repository.UserRepository;
import com.webtap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public void update(User user) {
        userRepository.save(user);
    }

    public void updatePwd(String password, String userName) {
        userRepository.updatePassword(password,userName);
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAllUsers() {

    }
}
