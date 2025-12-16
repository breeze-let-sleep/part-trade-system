package com.hyltest.mapper;


import com.hyltest.pojo.VO.ContractVO;
import com.hyltest.pojo.entity.VOrderCompleteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface VOrderCompleteInfoMapper {


    /**
     * 获取当前供应商未签名的订单合同数量
     *
     * @return 订单数量
     */
    Integer getTotalNumToMerchant(Integer curId);

    /**
     * 获取当前顾客未签名的订单合同数量
     *
     * @return 订单数量
     */
    Integer getTotalNumToCustomer(Integer curId);


    /**
     * 分页获取当前供应商未签字的订单（is_success=0 and signature is null)
     *
     * @param curId      当前用户id
     * @param start      开始位置
     * @param pageSize   页大小
     * @return 订单列表
     */
    List<ContractVO> getContractsByPageToMerchant(Integer curId, int start, Integer pageSize);

    /**
     * 分页获取当前顾客未签字的订单（is_success=0 and signature is null)
     *
     * @param curId      当前用户id
     * @param start      开始位置
     * @param pageSize   页大小
     * @return 订单列表
     */
    List<ContractVO> getContractsByPageToCustomer(Integer curId, int start, Integer pageSize);

    /**
     * 获取订单详情
     *
     * @param orderId 订单id
     * @return 订单详情
     */
    @Select("select order_id,customer_name,merchant_name,part_name,color,weight,single_price,amount,total_price,create_time from v_order_complete_info where order_id = #{orderId}")
    VOrderCompleteInfo getOrderDetailByOrderId(Integer orderId);

    /**
     * 获取待付款的订单列表
     *
     * @param curId     用户 id
     * @param start    开始位置
     * @param pageSize 页大小
     * @return 订单列表
     */
    @Select("select order_id,part_name,amount,total_price,create_time from v_order_complete_info where is_success = 1 and is_paid = 0 and customer_id = #{curId} limit #{start},#{pageSize}")
    List<VOrderCompleteInfo> selectPaidMessage(Integer curId, Integer start, Integer pageSize);
}
