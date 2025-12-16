package com.hyltest.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * <p>
 * 订单合同表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface ContractMapper {
    /**
     * 对供商进行签字
     */
    @Update("update contract set merchant_signature = #{value} where order_id = #{orderId}")
    void signatureToMerchant(Integer orderId, String value);


    /**
     * 对顾客进行签字
     */
    @Update("update contract set customer_signature = #{value} where order_id = #{orderId}")
    void signatureToCustomer(Integer orderId, String value);

    /**
     * 获取订单合同状态
     */
    @Select("select merchant_signature,customer_signature from contract where order_id = #{orderId}")
    Map<String, String> getContractStatus(Integer orderId);
}
