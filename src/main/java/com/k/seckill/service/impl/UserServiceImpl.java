package com.k.seckill.service.impl;

import com.k.seckill.model.User;
import com.k.seckill.repository.UserRepository;
import com.k.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User register(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.getOne(username);
    }
}
