package com.k.seckill.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.k.seckill.base.result.Result;
import com.k.seckill.base.result.ResultCode;
import com.k.seckill.model.Course;
import com.k.seckill.model.Orders;
import com.k.seckill.model.User;
import com.k.seckill.redis.CourseRedis;
import com.k.seckill.redis.SeckillRedis;
import com.k.seckill.service.ICourseService;
import com.k.seckill.service.IOrderService;
import com.k.seckill.service.ISeckillService;
import com.k.seckill.util.IpUtil;
import com.k.seckill.util.UUIDUtil;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
@Transactional
public class SeckillServiceImpl implements ISeckillService{

    @Autowired
    public ICourseService courseService;

    @Autowired
    public IOrderService orderService;

    @Autowired
    public CourseRedis courseRedis;

    @Autowired
    public SeckillRedis seckillRedis;


    //使用KafkaTempalte进行发送消息
    @Autowired
    public KafkaTemplate<String, String > kafkaTempalte;

    /**
     * 通过redis中的数据变化，来初始化这个变量
     * 这是在本地内存中加一个标志位flag, 判断是否被秒杀，若没有库存为true
     */
    private static Map<String, Boolean> isSeckill = new HashMap<String, Boolean>();



    public Orders seckill(User user, Course course){
        //减库存
        int success = courseService.reduceStockByCourseNo(course.getCourseNo());
        //下订单
        if(success > 0){
            Orders orders = new Orders();
            BeanUtils.copyProperties(course, orders);
            orders.setUsername(user.getUsername());
            orders.setCreateBy(user.getUsername());
            orders.setCreateDate(new Date());
            orders.setPayPrice(course.getCoursePrice());
            orders.setPayStatus("0");
            return orderService.saveAndFlush(orders);
        }
        return null;
    }



    @Override
    public Result<Orders> seckillFlow(User user, String courseNo) {
        System.out.println(" user = "+user.getUsername());

        boolean isPass = isSeckill.get(courseNo);
        if(isPass){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断库存redis 预减库存
        double stockQuantity = courseRedis.decr(courseNo, -1);
        if(stockQuantity <= 0){
            //当库存没有时， 为true. 并且直接返回错误信息
            isSeckill.put(courseNo, true);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断是否已经购买
        Orders order = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if(order != null){
            return Result.failure(ResultCode.SECKILL_BOUGHT);
        }
        //进入队列，减库存 下订单
        kafkaTempalte.send("test",courseNo+","+user.getUsername());
        //Orders newOrder = seckill(user, course);
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }


    /**
     * 用Redis缓存所有的课程
     */
    @Override
    public void cacheAllCourse() {
        //从数据库中读出来
        List<Course> courseList = courseService.findAllCourses();
        if(courseList == null){
            return;
        }
        for(Course course : courseList){
            //60秒过期
            courseRedis.putString(course.getCourseNo(), course.getStockQuantity(), 60, true);
            //为了方便，再把course 这个对象直接存进来
            courseRedis.put(course.getCourseNo(), course, -1);
            //这里相当于初始化这个变量，所以要在这里给这个flag赋值
            isSeckill.put(course.getCourseNo(), false);
        }

    }



    @Override
    public Result<Orders> seckillResult(User user, String courseNo) {
        Orders orders = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if(orders == null){
            return Result.failure(ResultCode.SECKILL_LINE_UP);
        }

        return Result.success(orders);
    }



    @Override
    public String getPath(User user, String courseNo) {
        String path = UUIDUtil.getUUID();
        seckillRedis.putString("path"+"_"+courseNo+"_"+user.getUsername(), path, 60);
        return path;
    }


    /**
     * 第三版: 增加动态地址
     * @param user
     * @param courseNo
     * @param path
     * @return
     */
    @Override
    public Result<Orders> seckillFlow(User user, String courseNo, String path) {
        //验证path
        String redisPath = (String) seckillRedis.getString("path"+"_"+courseNo+"_"+user.getUsername());
        //如果地址不相同。返回错误信息
        if(!path.equals(redisPath)){
            return Result.failure(ResultCode.SECKILL_PATH_ERROR);
        }

        boolean isPass = isSeckill.get(courseNo);
        if(isPass){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断库存redis 预减库存
        double stockQuantity = courseRedis.decr(courseNo, -1);
        if(stockQuantity <= 0){
            isSeckill.put(courseNo, true);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断是否已经购买
        Orders order = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if(order != null){
            return Result.failure(ResultCode.SECKILL_BOUGHT);
        }
        //减库存 下订单
        kafkaTempalte.send("test",courseNo+","+user.getUsername());
        //Orders newOrder = seckill(user, course);
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }


    /**
     * 第四版
     * @param user
     * @param courseNo
     * @param path
     * @param request
     * @return
     */
    @Override
    public Result<Orders> seckillFlow(User user, String courseNo, String path, HttpServletRequest request) {
        //ip验证
        String ip = IpUtil.getIpAddr(request);

        System.out.println(ip);
        if(seckillRedis.incr(ip, 1) >= 3){
            return Result.failure(ResultCode.SECKILL_IP_OUTMAX);
        }


        //验证path
        String redisPath = (String) seckillRedis.getString("path"+"_"+courseNo+"_"+user.getUsername());
        if(!path.equals(redisPath)){
            return Result.failure(ResultCode.SECKILL_PATH_ERROR);
        }

        boolean isPass = isSeckill.get(courseNo);
        if(isPass){
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断库存redis 预减库存
        double stockQuantity = courseRedis.decr(courseNo, -1);
        if(stockQuantity <= 0){
            isSeckill.put(courseNo, true);
            return Result.failure(ResultCode.SECKILL_NO_QUOTE);
        }
        //判断是否已经购买
        Orders order = orderService.getOrderByUsernameAndCourseNo(user.getUsername(), courseNo);
        if(order != null){
            return Result.failure(ResultCode.SECKILL_BOUGHT);
        }
        //减库存 下订单
        kafkaTempalte.send("test",courseNo+","+user.getUsername());
        //Orders newOrder = seckill(user, course);
        return Result.failure(ResultCode.SECKILL_LINE_UP);
    }

}
