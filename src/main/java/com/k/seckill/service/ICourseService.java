package com.k.seckill.service;

import java.util.List;

import com.k.seckill.model.Course;

public interface ICourseService {

     List<Course> findAllCourses();

     Course findCourseByCourseNo(String courseNo);

     int reduceStockByCourseNo(String courseNo);

}
