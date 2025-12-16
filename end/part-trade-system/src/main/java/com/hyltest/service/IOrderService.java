package com.hyltest.service;

import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.Order;

import java.time.LocalDateTime;

public interface IOrderService {
    void addOrder(Order order);

    PageResult getPaidMessagePage(Integer currentPage, Integer pageSize);

    boolean payOrder(Integer orderId);

    void deleteOrderById(Integer orderId);

    PageResult getFinishedOrderPage(String partName, Integer orderId, Integer star,
                                    LocalDateTime startTime, LocalDateTime endTime,
                                    Integer currentPage, Integer pageSize);
}