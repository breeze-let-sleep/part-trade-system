package com.hyltest.service;



/**
 * <p>
 * 订单合同表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IContractService{

    /**
     * 签名订单
     */
    String signature(Integer orderId, String value);
}
