package com.rjjq.springbootmall.service.impl;

import com.rjjq.springbootmall.dao.UserDao;
import com.rjjq.springbootmall.dto.UserRegisterRequest;
import com.rjjq.springbootmall.model.User;
import com.rjjq.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
