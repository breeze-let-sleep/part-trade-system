package com.hyltest.service.impl;

import com.hyltest.mapper.VMerchantCollectInfoMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.VMerchantCollectInfo;
import com.hyltest.service.IVMerchantCollectInfoService;
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
public class VMerchantCollectInfoServiceImpl implements IVMerchantCollectInfoService {

    private final VMerchantCollectInfoMapper vMerchantCollectInfoMapper;
    @Override
    public PageResult likeCollects(String inputMerchant, Integer customerId, Integer currentPage, Integer pageSize) {
        Integer curId = CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        Integer total = vMerchantCollectInfoMapper.getCount(curId);
        pageResult.setTotal(total);
        List<VMerchantCollectInfo> rows = vMerchantCollectInfoMapper.likeCollects(inputMerchant, customerId, (currentPage - 1) * pageSize, pageSize);
        pageResult.setRows(rows);
        return pageResult;
    }

    @Override
    public PageResult pageCollects(Integer currentPage, Integer pageSize) {
        Integer curId = CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        Integer total = vMerchantCollectInfoMapper.getCount(curId);
        pageResult.setTotal(total);
        List<VMerchantCollectInfo> rows = vMerchantCollectInfoMapper.getCollectList(curId,(currentPage - 1) * pageSize, pageSize);
        pageResult.setRows(rows);
        return pageResult;
    }
}
