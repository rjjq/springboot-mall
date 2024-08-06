package com.rjjq.springbootmall.dao;

import com.rjjq.springbootmall.dto.UserRegisterRequest;
import com.rjjq.springbootmall.model.User;

public interface UserDao {

    Integer createUser(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
