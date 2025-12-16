package com.hyltest.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 存储报表所需的所有数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportVO {
    //统计数据
    //所有用户数量
    private Integer allUsers;
    //所有订单数量
    private Integer allOrders;
    //所有已完成订单数量
    private Integer hasDoneOrders;
    //总成交金额
    private Integer totalMoney;
    //好评订单数量
    private Integer goodComments;
    //图表数据
    //柱状图
    private List<String> barLabels;
    private List<Integer> barData;
    //折线图
    private List<String> lineLabels;
    private List<Integer> lineData;
    //饼图
    private List<String> pieLabels;
    private List<Integer> pieData;
}
