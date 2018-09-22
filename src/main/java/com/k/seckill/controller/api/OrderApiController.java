package com.k.seckill.controller.api;

import com.k.seckill.base.controller.BaseApiController;
import com.k.seckill.base.result.Result;
import com.k.seckill.model.Orders;
import com.k.seckill.model.User;
import com.k.seckill.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderApiController extends BaseApiController {

    @Autowired
    IOrderService iOrderService;


    @RequestMapping(value = "orderList", method = RequestMethod.GET)
    public Result<List<Orders>> orderList(User user) {



        //按照订单时间的创建时间的降序来排列
        List<Orders> ordersList = iOrderService.findAllByUsername(user.getUsername(), new Sort(Sort.Direction.DESC,"createDate"));


        return Result.success(ordersList);

    }
 }
