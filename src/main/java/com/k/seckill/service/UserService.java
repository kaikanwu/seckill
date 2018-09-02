package com.k.seckill.service;

import com.k.seckill.model.User;

public interface UserService {

    public User register(User user);

    public User getUser(String username);

}
