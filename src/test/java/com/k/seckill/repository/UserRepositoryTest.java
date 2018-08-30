package com.k.seckill.repository;

import com.k.seckill.model.User;
import com.k.seckill.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {


    @Autowired
    private UserService userService;


    @Test
    public void testRegister() {


        Assert.assertNotNull(userService.register(new User("kk","123456",123456)));

    }

}