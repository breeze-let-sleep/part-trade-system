package com.hyltest.service.impl;

import com.hyltest.mapper.OrderMapper;
import com.hyltest.mapper.PartMapper;
import com.hyltest.mapper.VOrderCompleteInfoMapper;
import com.hyltest.mapper.VOrderDetailCompleteMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.Order;
import com.hyltest.pojo.entity.VOrderCompleteInfo;
import com.hyltest.pojo.entity.VOrderDetailComplete;
import com.hyltest.service.IOrderService;
import com.hyltest.utils.CurrentHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final OrderMapper orderMapper;
    private final VOrderCompleteInfoMapper orderCompleteInfoMapper;
    private final VOrderDetailCompleteMapper orderDetailCompleteMapper;
    private final PartMapper partMapper;

    @Override
    @Transactional
    public void addOrder(Order order) {
        order.setCreateTime(LocalDateTime.now());
        orderMapper.addOrder(order);
        //修改零件数量
        partMapper.decreaseInventory(order.getPartId(),order.getAmount());
    }

    @Override
    public PageResult getPaidMessagePage(Integer currentPage, Integer pageSize) {
        PageResult pageResult = new PageResult();
        Integer curId = CurrentHolder.getCurrentId();
        Integer start = (currentPage - 1) * pageSize;
        Integer total = orderMapper.selectPaidMessageCount(curId);
        pageResult.setTotal(total);
        if (total == 0) {
            pageResult.setRows(List.of());
        }else {
            List<VOrderCompleteInfo> rows = orderCompleteInfoMapper.selectPaidMessage(curId,start, pageSize);
            pageResult.setRows(rows);
        }
        return pageResult;
    }

    @Override
    @Transactional
    public boolean payOrder(Integer orderId) {
        orderMapper.updatePayStatus(orderId);
        return true;
    }

    @Override
    @Transactional
    public void deleteOrderById(Integer orderId) {
        //把对应零件数量加回来
        Map<String, Integer> map = orderMapper.getAmountById(orderId);
        partMapper.increaseInventory(map.get("part_id"),map.get("amount"));
        //删除订单
        orderMapper.deleteOrderById(orderId);

    }

    @Override
    public PageResult getFinishedOrderPage(String partName, Integer orderId, Integer star,
                                           LocalDateTime startTime, LocalDateTime endTime,
                                           Integer currentPage, Integer pageSize) {
        PageResult pageResult = new PageResult();
        Integer total = orderDetailCompleteMapper.selectEndAllOrderCount();
        pageResult.setTotal(total);
        Integer start = (currentPage - 1) * pageSize;
        List<VOrderCompleteInfo> rows = orderDetailCompleteMapper.selectEndOrderList(partName, orderId, star, startTime, endTime, start, pageSize);
        pageResult.setRows(rows);
        return pageResult;
    }
}