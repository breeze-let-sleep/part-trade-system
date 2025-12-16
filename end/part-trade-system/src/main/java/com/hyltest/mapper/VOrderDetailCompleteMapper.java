package com.hyltest.mapper;

import com.hyltest.pojo.entity.VOrderCompleteInfo;
import com.hyltest.pojo.entity.VOrderDetailComplete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-12-14
 */
@Mapper
public interface VOrderDetailCompleteMapper{

    /**
     * 分页获取当前供应商正在进行的订单详情
     *
     * @param curId      当前用户id
     * @param start      开始位置
     * @param pageSize   页大小
     * @return 订单列表
     */
    @Select("select order_id,is_deliver,updateTime,customer_id,customer_name,customer_address,total_price from v_order_detail_complete where is_take = 0 and merchant_id = #{curId} limit #{start},#{pageSize}")
    List<VOrderDetailComplete> getProcessingOrderDetailByMerchantId(Integer curId, int start, Integer pageSize);

    /**
     * 分页获取当前顾客正在进行的订单详情
     *
     * @param curId      当前用户id
     * @param start      开始位置
     * @param pageSize   页大小
     * @return 订单列表
     */
    @Select("select order_id,is_deliver,updateTime,merchant_id,merchant_name,merchant_address,part_name,total_price from v_order_detail_complete where is_take = 0 and customer_id = #{curId} limit #{start},#{pageSize}")
    List<VOrderDetailComplete> getProcessingOrderDetailByCustomerId(Integer curId, int start, Integer pageSize);

    /**
     * 获取所有已经完成的订单数量
     *
     * @return 订单数量
     */
    @Select("select count(*) from v_order_detail_complete where is_take = 1")
    Integer selectEndAllOrderCount();

    /**
     * 分页获取所有已经完成的订单详情
     *
     * @param partName  零件名称
     * @param orderId   订单id
     * @param star      评分
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param start     开始位置
     * @param pageSize  页大小
     * @return 订单列表
     */
    List<VOrderCompleteInfo> selectEndOrderList(String partName, Integer orderId, Integer star, LocalDateTime startTime, LocalDateTime endTime, Integer start, Integer pageSize);

}
