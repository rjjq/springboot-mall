package com.rjjq.springbootmall.service;

import com.rjjq.springbootmall.dto.UserRegisterRequest;
import com.rjjq.springbootmall.model.User;

public interface UserService {
    Integer register(UserRegisterRequest userRegisterRequest);

    User getUserById(Integer userId);
}
