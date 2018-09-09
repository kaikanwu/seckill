package com.k.seckill.service;

import com.k.seckill.model.User;
import com.k.seckill.vo.UserVO;

public interface UserService {

    public User register(User user);

    public UserVO getUser(String username);

    public void saveUserToRedisByToken(UserVO dbUser, String token);

    public Object getUserFromRedisByToken(String token);

}
