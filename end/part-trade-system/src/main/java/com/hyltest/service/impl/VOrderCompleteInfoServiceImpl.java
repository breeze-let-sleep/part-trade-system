package com.hyltest.service.impl;

import com.hyltest.mapper.ContractMapper;
import com.hyltest.mapper.VOrderCompleteInfoMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.VO.ContractVO;
import com.hyltest.pojo.entity.VOrderCompleteInfo;
import com.hyltest.service.IVOrderCompleteInfoService;
import com.hyltest.utils.CurrentHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * VIEW 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */

@RequiredArgsConstructor
@Service
public class VOrderCompleteInfoServiceImpl implements IVOrderCompleteInfoService {

    private final VOrderCompleteInfoMapper orderCompleteInfoMapper;
    private final ContractMapper contractMapper;

    @Override
    public PageResult getProcessingContractPage(Integer currentPage, Integer pageSize) {
        //先获取当前对象的id判断要获取哪些订单
        Integer curId = CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        //todo 分页查询仍然存在问题，不应是只查询isSuccess，要联表查询
        if (curId < 200000) {
            //供应商
            //1、获取总记录数
            Integer totalNum = orderCompleteInfoMapper.getTotalNumToMerchant(curId);
            pageResult.setTotal(totalNum);
            //2、获取当前页数据
            List<ContractVO> rows = orderCompleteInfoMapper.getContractsByPageToMerchant(curId, currentPage-1, pageSize);
            pageResult.setRows(rows);
            //3、封装PageResult对象并返回
            return pageResult;
        }else {
            //顾客
            Integer totalNum = orderCompleteInfoMapper.getTotalNumToCustomer(curId);
            pageResult.setTotal(totalNum);
            List<ContractVO> rows = orderCompleteInfoMapper.getContractsByPageToCustomer(curId, currentPage-1, pageSize);
            pageResult.setRows(rows);
            return pageResult;
        }
    }

    @Override
    public VOrderCompleteInfo getProcessingOrderDetail(Integer orderId) {
        VOrderCompleteInfo detail = orderCompleteInfoMapper.getOrderDetailByOrderId(orderId);
        return detail;
    }
}
