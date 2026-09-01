package com.hyltest.pojo.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 存储报表所需的所有数据
 */
@Data
@Schema(description = "报表数据")
@AllArgsConstructor
@NoArgsConstructor
public class ReportVO {
    //统计数据
    //所有用户数量
    @Schema(description = "所有用户数量", example = "100")
    private Integer allUsers;
    //所有订单数量
    @Schema(description = "所有订单数量", example = "200")
    private Integer allOrders;
    //所有已完成订单数量
    @Schema(description = "所有已完成订单数量", example = "150")
    private Integer hasDoneOrders;
    //总成交金额
    @Schema(description = "总成交金额", example = "50000")
    private Integer totalMoney;
    //好评订单数量
    @Schema(description = "好评订单数量", example = "120")
    private Integer goodComments;
    //图表数据
    //柱状图
    @Schema(description = "柱状图标签", example = "[\"供应商小明\", \"供应商小红\"]")
    private List<String> barLabels;
    @Schema(description = "柱状图数据", example = "[10, 20]")
    private List<Integer> barData;
    //折线图
    @Schema(description = "折线图标签", example = "[\"1月\", \"2月\"]")
    private List<String> lineLabels;
    @Schema(description = "折线图数据", example = "[100, 200]")
    private List<Integer> lineData;
    //饼图
    @Schema(description = "饼图标签", example = "[\"已完成\", \"处理中\"]")
    private List<String> pieLabels;
    @Schema(description = "饼图数据", example = "[150, 50]")
    private List<Integer> pieData;
}
