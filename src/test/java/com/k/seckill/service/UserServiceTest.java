package com.k.seckill.service;

import com.k.seckill.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Test
    public void test() {

        User user = new User("k", "1234");


        Assert.assertNotNull(user);

//        fail("not implement yet");
    }

}