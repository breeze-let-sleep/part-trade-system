package com.hyltest.service.impl;

import com.hyltest.mapper.CustomerMapper;
import com.hyltest.mapper.MerchantMapper;
import com.hyltest.mapper.OrderDetailMapper;
import com.hyltest.mapper.OrderMapper;
import com.hyltest.pojo.VO.ReportVO;
import com.hyltest.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MerchantMapper merchantMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public ReportVO getReport() {
        ReportVO reportVO = new ReportVO();
        log.info("获取报表数据");
        //统计数据
        Integer num1 = merchantMapper.getNum();
        Integer num2 = customerMapper.getNum();
        Integer allUsers = num1 + num2;
        reportVO.setAllUsers(allUsers);
        Integer allOrders = orderMapper.getNum();
        reportVO.setAllOrders(allOrders);
        Integer hasDoneOrders = orderDetailMapper.getNum();
        reportVO.setHasDoneOrders(hasDoneOrders);
        Integer totalMoney = orderMapper.getTotalMoney();
        reportVO.setTotalMoney(totalMoney);
        Integer goodComments = orderDetailMapper.getGoodComments();
        reportVO.setGoodComments(goodComments);
        //图表数据
        List<String> partName = new ArrayList<>();
        List<Integer> num = new ArrayList<>();
        List<Map<String, Object>> bar = orderMapper.getSellPartFour();
        for (Map<String, Object> row : bar) {
            // 提取第一列（标签）
            partName.add((String) row.get("part_name"));
            // 提取第二列（数值）
            num.add(((Number) row.get("num")).intValue());

        }
        reportVO.setBarLabels(partName);
        reportVO.setBarData(num);

        List<String> month = new ArrayList<>();
        List<Integer> orderNum = new ArrayList<>();
        List<Map<String, Object>> line = orderMapper.getRecentFourMonthOrder();
        for (Map<String, Object> row : line) {
            // 提取第一列（标签）
            month.add((String) row.get("month"));
            // 提取第二列（数值）
            orderNum.add(((Number) row.get("order_num")).intValue());
        }
        reportVO.setLineLabels(month);
        reportVO.setLineData(orderNum);

        reportVO.setPieLabels(Arrays.stream(new String[]{"已完成订单","总订单数"}).toList());
        reportVO.setPieData(Arrays.stream(new Integer[]{hasDoneOrders,allOrders}).toList());
        log.info("报表数据获取完毕"+reportVO);
        return reportVO;
    }
}
