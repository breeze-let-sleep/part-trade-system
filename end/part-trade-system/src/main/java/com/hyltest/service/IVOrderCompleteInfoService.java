package com.hyltest.service;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.VOrderCompleteInfo;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IVOrderCompleteInfoService{

    /**
     * 分页获取订单合同列表
     * @param currentPage 当前页
     * @param pageSize 每页数量
     * @return 订单列表
     */
    PageResult getProcessingContractPage(Integer currentPage, Integer pageSize);

    /**
     * 获取处理中的订单详情（is_success = 0）
     * @param orderId 订单id
     * @return 订单详情
     */
    VOrderCompleteInfo getProcessingOrderDetail(Integer orderId);
}
