package com.webtap.service.impl;

import com.webtap.domain.entity.Role;
import com.webtap.domain.entity.User;
import com.webtap.repository.RoleRepository;
import com.webtap.repository.UserRepository;
import com.webtap.repository.UserRoleRepository;
import com.webtap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Resource
    private JavaMailSender mailSender;

    public User getUser(Long id){
        return userRepository.findById(id);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User getUser(String username, String email) {
        return userRepository.findByUserNameOrEmail(username,email);
    }

    @Override
    public void create(User user) {
        userRepository.save(user);
    }

    public void update(User user) {
        userRepository.save(user);
    }

    public void updatePwd(String password, String userName) {
        userRepository.updatePassword(password,userName);
    }


    public void delete(Long id){
        userRepository.delete(id);
    }

    @Override
    public List<User> getUsers(User user) {
        List<Object []> objects = userRepository.findObjectsByOrgId(user.getOrgId());
        List<User> userList = objects.stream().map(User::new).collect(Collectors.toList());
        return userList;
    }


    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        return userRepository.findAllByRoleId(roleId);
    }

    @Override
    public void deleteAllUsers() {

    }
}
