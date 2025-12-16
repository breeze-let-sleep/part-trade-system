package com.hyltest.service.impl;

import com.hyltest.mapper.OrderDetailMapper;
import com.hyltest.mapper.VOrderCompleteInfoMapper;
import com.hyltest.mapper.VOrderDetailCompleteMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.OrderDetail;
import com.hyltest.pojo.entity.VOrderCompleteInfo;
import com.hyltest.pojo.entity.VOrderDetailComplete;
import com.hyltest.service.IOrderDetailService;
import com.hyltest.utils.CurrentHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 订单详情表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final OrderDetailMapper orderDetailMapper;
    private final VOrderDetailCompleteMapper vOrderDetailCompleteMapper;

    @Override
    public PageResult getProcessingOrderPage(Integer currentPage, Integer pageSize) {
        //获取当前用户id
        Integer curId = CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        if (curId < 200000){
            //供应商
            //查询当前用户正在进行的订单数量
            Integer totalNum = orderDetailMapper.getProcessingTotalNumToMerchant(curId);
            pageResult.setTotal(totalNum);
            //根据当前用户id获取is_take=0的订单详情
            List<VOrderDetailComplete> rows = vOrderDetailCompleteMapper.getProcessingOrderDetailByMerchantId(curId, (currentPage-1)*pageSize, pageSize);
            pageResult.setRows(rows);
        }else {
            //顾客
            //查询当前用户正在进行的订单数量
            Integer totalNum = orderDetailMapper.getProcessingTotalNumToCustomer(curId);
            pageResult.setTotal(totalNum);
            //根据当前用户id获取is_take=0的订单详情
            List<VOrderDetailComplete> rows = vOrderDetailCompleteMapper.getProcessingOrderDetailByCustomerId(curId, (currentPage-1)*pageSize, pageSize);
            pageResult.setRows(rows);
        }
        return pageResult;
    }

    @Override
    public PageResult getEndOrderPage(Integer currentPage, Integer pageSize) {
        //获取当前用户id
        Integer curId = CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        if (curId < 200000){
            //供应商
            //查询当前用户已经完成的订单数量
            Integer totalNum = orderDetailMapper.getEndTotalNumToMerchant(curId);
            pageResult.setTotal(totalNum);
            //根据当前用户id获取is_take=1的订单详情
            List<OrderDetail> rows = orderDetailMapper.getEndOrderDetailByMerchantId(curId, (currentPage-1)*pageSize, pageSize);
            pageResult.setRows(rows);
        }else {
            //顾客
            Integer totalNum = orderDetailMapper.getEndTotalNumToCustomer(curId);
            pageResult.setTotal(totalNum);
            List<OrderDetail> rows = orderDetailMapper.getEndOrderDetailByCustomerId(curId, (currentPage-1)*pageSize, pageSize);
            pageResult.setRows(rows);
        }
        return pageResult;
    }

    @Override
    public void evaluateOrder(OrderDetail orderDetail) {
        orderDetail.setUpdateTime(LocalDateTime.now());
        orderDetailMapper.updateOrderHasTake(orderDetail);
    }

    @Override
    public void deliverById(Integer id) {
        LocalDateTime now = LocalDateTime.now();
        orderDetailMapper.updateOrderDeliver(id,now);
    }
}
