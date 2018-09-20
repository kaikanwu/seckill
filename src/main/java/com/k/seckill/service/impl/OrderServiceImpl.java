package com.k.seckill.service.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.k.seckill.model.Orders;
import com.k.seckill.repository.OrderRepository;
import com.k.seckill.service.IOrderService;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    public Orders getOrderByUsernameAndCourseNo(String username, String courseNo) {
        return orderRepository.findByUsernameAndCourseNo(username, courseNo);
    }

    @Override
    public Orders saveAndFlush(Orders orders) {
        return orderRepository.saveAndFlush(orders);
    }

    @Override
    public List<Orders> findAllByUsername(String username, Sort sort) {
        return orderRepository.findByUsername(username, sort);
    }

}