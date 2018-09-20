package com.k.seckill.service;


import javax.servlet.http.HttpServletRequest;

import com.k.seckill.base.result.Result;
import com.k.seckill.model.Course;
import com.k.seckill.model.Orders;
import com.k.seckill.model.User;

public interface ISeckillService {

    Result<Orders> seckillFlow(User user, String courseNo);

    void cacheAllCourse();

    public Orders seckill(User user, Course course);

    Result<Orders> seckillResult(User user, String courseNo);

    String getPath(User user, String courseNo);

    Result<Orders> seckillFlow(User user, String courseNo, String path);

    Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request);

}
