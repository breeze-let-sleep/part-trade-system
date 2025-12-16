package com.hyltest.service;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.OrderDetail;

/**
 * <p>
 * 订单详情表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IOrderDetailService{

    /**
     * 分页获取正在进行的订单详情（物流）列表
     * @param currentPage 当前页
     * @param pageSize 每页数量
     * @return 订单列表
     */
    PageResult getProcessingOrderPage(Integer currentPage, Integer pageSize);

    /**
     * 分页获取已经完成的订单详情列表
     * @param currentPage 当前页
     * @param pageSize 每页数量
     * @return 订单列表
     */
    PageResult getEndOrderPage(Integer currentPage, Integer pageSize);

    /**
     * 签收并评价订单
     * @param orderDetail 订单详情
     */
    void evaluateOrder(OrderDetail orderDetail);

    /**
     * 发货
     * @param id 订单id
     */
    void deliverById(Integer id);
}
