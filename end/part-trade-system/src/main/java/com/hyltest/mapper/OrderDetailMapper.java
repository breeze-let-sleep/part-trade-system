package com.hyltest.mapper;


import com.hyltest.pojo.entity.OrderDetail;
import com.hyltest.pojo.entity.VOrderCompleteInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单详情表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface OrderDetailMapper {

    /**
     * 获取已经完成的订单数量
     * @return 已经完成的订单数量
     */
    @Select("select count(*) as hasDoneOrders from order_detail where is_take=1 group by is_take")
    Integer getNum();

    /**
     * 获取好评订单数量
     * @return 好评订单数量
     */
    @Select("select count(*) goodComments from order_detail where star >= 4")
    Integer getGoodComments();

    /**
     * 获取当前供应商待处理的订单数量
     * @param curId 当前用户id
     * @return 订单数量
     */
    Integer getProcessingTotalNumToMerchant(Integer curId);

    /**
     * 获取当前顾客待处理的订单数量
     * @param curId 当前用户id
     * @return 订单数量
     */
    Integer getProcessingTotalNumToCustomer(Integer curId);

    /**
     * 获取当前供应商已完成的订单数量
     * @param curId 当前用户id
     * @return 订单数量
     */
    Integer getEndTotalNumToMerchant(Integer curId);

    /**
     * 分页获取当前供应商已完成的订单
     * @param curId 当前用户id
     * @param start 开始位置
     * @param pageSize 每页数量
     * @return 订单
     */
    List<OrderDetail> getEndOrderDetailByMerchantId(Integer curId, int start, Integer pageSize);

    /**
     * 获取当前顾客已完成的订单数量
     * @param curId 当前用户id
     * @return 订单数量
     */
    Integer getEndTotalNumToCustomer(Integer curId);

    /**
     * 分页获取当前顾客已完成的订单
     * @param curId 当前用户id
     * @param start 开始位置
     * @param pageSize 每页数量
     * @return 订单
     */
    List<OrderDetail> getEndOrderDetailByCustomerId(Integer curId, int start, Integer pageSize);

    /**
     * 修改订单为已收货
     * @param orderDetail 订单
     */
    @Update("update order_detail set is_take = 1,star=#{star},evaluate=#{evaluate},update_time=#{updateTime} where order_id = #{orderId}")
    void updateOrderHasTake(OrderDetail orderDetail);

    /**
     * 修改订单为已发货
     * @param id 订单流程id
     * @param now 当前时间
     */
    @Update("update order_detail set is_deliver = 1,update_time= #{now} where order_id = #{id}")
    void updateOrderDeliver(Integer id, LocalDateTime now);

}
