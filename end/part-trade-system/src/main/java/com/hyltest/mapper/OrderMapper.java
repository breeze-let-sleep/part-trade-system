package com.hyltest.mapper;


import com.hyltest.pojo.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface OrderMapper {


    /**
     * 获取订单数量
     * @return 订单数量
     */
    @Select("select count(*) from `order`")
    Integer getNum();

    /**
     * 获取所有订单交易总金额
     * @return 订单交易总金额
     */
    @Select("select sum(total_price) totalMoney from `order`")
    Integer getTotalMoney();

    /**
     * 获取订单销售量前四的零件
     *
     * @return 订单销售量前四的零件
     */
    @MapKey("part_name")      //随便起一个防止报错
    List<Map<String, Object>> getSellPartFour();

    /**
     * 获取最近四个月订单
     *
     * @return 最近四个月订单
     */
    @MapKey("month")
    List<Map<String, Object>> getRecentFourMonthOrder();

    /**
     * 添加订单
     * （使用主键返回，并把获得的主键设置给newOrder的id属性
     * useGeneratedKeys = true,keyProperty = "id"）
     * @param newOrder 新订单
     */
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into `order` (merchant_id, customer_id, part_id, amount, total_price, create_time) values (#{merchantId}, #{customerId}, #{partId}, #{amount}, #{totalPrice}, #{createTime})")
    void addOrder(Order newOrder);

    /**
     * 修改订单为成功
     * @param orderId 订单id
     */
    @Update("update `order` set is_success = 1 where id = #{orderId}")
    void updateOrderSuccess(Integer orderId);

    /**
     * 获取待支付订单数量
     * @return 待支付订单数量
     */
    @Select("select count(*) from `order` where is_success = 1 and is_paid = 0 and customer_id = #{curId}")
    Integer selectPaidMessageCount(Integer curId);


    /**
     * 修改订单为已支付
     * @param orderId 订单id
     */
    @Update("update `order` set is_paid = 1 where id = #{orderId}")
    void updatePayStatus(Integer orderId);

    /**
     * 删除订单
     * @param orderId 订单id
     */
    @Delete("delete from `order` where id = #{orderId}")
    void deleteOrderById(Integer orderId);

    /**
     * 根据订单id获取零件数量
     * @param orderId 订单id
     * @return 零件数量
     */
    @Select("select part_id,amount from `order` where id = #{orderId}")
    Map<String, Integer> getAmountById(Integer orderId);
}
