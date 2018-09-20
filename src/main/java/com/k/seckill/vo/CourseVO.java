package com.k.seckill.vo;


import java.io.Serializable;

import com.k.seckill.model.Course;

public class CourseVO implements Serializable{
    private static final long serialVersionUID = 2637546614806439774L;

    private Course course;
    private int courseStatus = 0;
    private int remainTime = 0;//距离选课还有多久

    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public int getCourseStatus() {
        return courseStatus;
    }
    public void setCourseStatus(int courseStatus) {
        this.courseStatus = courseStatus;
    }
    public int getRemainTime() {
        return remainTime;
    }
    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

}