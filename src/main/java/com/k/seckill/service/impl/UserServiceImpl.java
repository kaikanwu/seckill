package com.k.seckill.service.impl;

import com.k.seckill.model.User;
import com.k.seckill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        return userRepository.saveAndFlush(user);
    }

}
