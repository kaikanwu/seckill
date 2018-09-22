package com.k.seckill.service;

import com.k.seckill.model.User;
import com.k.seckill.vo.UserVO;

public interface IUserService {

     User register(User user);

     UserVO getUser(String username);

     void saveUserToRedisByToken(UserVO dbUser, String token);

     Object getUserFromRedisByToken(String token);

}
