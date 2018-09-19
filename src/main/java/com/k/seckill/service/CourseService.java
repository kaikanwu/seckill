package com.k.seckill.service;

import java.util.List;

import com.k.seckill.model.Course;

public interface CourseService {

    public List<Course> findAllCourses();

    public Course findCourseByCourseNo(String courseNo);

    public int reduceStockByCourseNo(String courseNo);

}
