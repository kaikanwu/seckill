package com.k.seckill.redis;

import com.k.seckill.model.Course;
import org.springframework.stereotype.Repository;


@Repository
public class CourseRedis extends BaseRedis<Course> {
    private static final String REDIS_KEY = "com.k.seckill.redis.CourseRedis";

    @Override
    protected String getRedisKey(){
        return REDIS_KEY;
    }
}
