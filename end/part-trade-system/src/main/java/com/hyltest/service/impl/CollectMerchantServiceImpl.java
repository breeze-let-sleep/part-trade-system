package com.hyltest.service.impl;

import com.hyltest.mapper.CollectMerchantMapper;
import com.hyltest.service.ICollectMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 收藏关系表业务实现
 */
@Service
public class CollectMerchantServiceImpl implements ICollectMerchantService {

    @Autowired
    private CollectMerchantMapper collectMerchantMapper;

    @Override
    public void addCollect(Integer customerId, Integer merchantId) {
        LocalDateTime now = LocalDateTime.now();
        collectMerchantMapper.addCollect(customerId, merchantId,now);
    }

    @Override
    public void removeCollect(Integer customerId, Integer merchantId) {
        collectMerchantMapper.removeCollect(customerId, merchantId);
    }
}
