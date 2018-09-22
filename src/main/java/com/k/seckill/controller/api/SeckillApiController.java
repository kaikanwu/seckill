package com.k.seckill.controller.api;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.k.seckill.base.controller.BaseApiController;
import com.k.seckill.base.result.Result;
import com.k.seckill.model.Orders;
import com.k.seckill.model.User;
import com.k.seckill.service.ISeckillService;

@RestController
public class SeckillApiController extends BaseApiController implements InitializingBean{

    @Autowired
    public ISeckillService seckillService;

    @Override
    public void afterPropertiesSet() throws Exception {
        //缓存所有的课程
        seckillService.cacheAllCourse();
    }

    @RequestMapping(value="{path}/seckill/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckill(User user, @PathVariable String courseNo, @PathVariable String path, HttpServletRequest request){
        if(user == null){
            return Result.failure();
        }



        System.out.println("===============================================");
        System.out.println(" ① API里的username" + user.getUsername() );
        System.out.println("===============================================");
        return seckillService.seckillFlow(user, courseNo, path, request);
    }

    @RequestMapping(value="seckill/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckill(User user, @PathVariable String courseNo){
        if(user == null){
            return Result.failure();
        }
        return seckillService.seckillFlow(user, courseNo);
    }


    /**
     * 用于前端轮询， 用于给前端显示 订单是否完成： 1.排队中， 2. 已完成
     * @param user
     * @param courseNo
     * @return
     */
    @RequestMapping(value="seckillResult/{courseNo}",method=RequestMethod.GET)
    public Result<Orders> seckillResult(User user, @PathVariable String courseNo){
        if(user == null){
            return Result.failure();
        }
        return seckillService.seckillResult(user, courseNo);
    }

    @RequestMapping(value="getPath/{courseNo}",method=RequestMethod.GET)
    public String getPath(User user, @PathVariable String courseNo){
        if(user == null){

        }
        return seckillService.getPath(user, courseNo);
    }
}
