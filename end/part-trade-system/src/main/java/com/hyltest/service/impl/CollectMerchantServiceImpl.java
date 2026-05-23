package com.hyltest.service.impl;

import com.hyltest.constant.MessageConstant;
import com.hyltest.exception.DeletionNotAllowedException;
import com.hyltest.exception.UnknownException;
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
        try {
            collectMerchantMapper.addCollect(customerId, merchantId,now);
        } catch (Exception e) {
            throw new UnknownException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @Override
    public void removeCollect(Integer customerId, Integer merchantId) {
        try {
            collectMerchantMapper.removeCollect(customerId, merchantId);
        } catch (Exception e) {
            throw new DeletionNotAllowedException(MessageConstant.UNKNOWN_ERROR);
        }
    }
}
