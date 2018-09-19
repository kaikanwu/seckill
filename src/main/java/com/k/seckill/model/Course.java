package com.k.seckill.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="course")
public class Course implements Serializable{
    private static final long serialVersionUID = 7936554690434155590L;

    @Id
    @Column(name="course_no")
    private String courseNo;

    @Column(name="course_name", nullable=false)
    private String courseName;

    @Column(name="teacher_name", nullable=false)
    private String teacherName;

    @Column(name="course_description")
    private String courseDescription;

    @Column(name="course_period")
    private String coursePeriod;

    @Column(name="start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;

    @Column(name="end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @Column(name="course_price")
    private BigDecimal coursePrice;

    @Column(name="stock_quantity")
    private Integer stockQuantity;

    @Column(name="course_type")
    private Integer courseType;

    @Column(name="course_pic")
    private String coursePic;

    public String getCoursePic() {
        return coursePic;
    }

    public void setCoursePic(String coursePic) {
        this.coursePic = coursePic;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCoursedescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(String coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(BigDecimal coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }
}
