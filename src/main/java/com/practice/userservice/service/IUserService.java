package com.practice.userservice.service;

import com.practice.userservice.domain.Role;
import com.practice.userservice.domain.User;

import java.util.List;

public interface IUserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String userName, String roleName);

    User getUser(String userName);

    List<User> getAllUsers();

}
