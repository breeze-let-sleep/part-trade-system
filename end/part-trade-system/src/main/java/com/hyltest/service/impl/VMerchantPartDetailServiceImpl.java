package com.hyltest.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hyltest.mapper.VMerchantPartDetailMapper;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import com.hyltest.service.IVMerchantPartDetailService;
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
public class VMerchantPartDetailServiceImpl extends ServiceImpl<VMerchantPartDetailMapper, VMerchantPartDetail> implements IVMerchantPartDetailService {

    private final VMerchantPartDetailMapper merchantPartDetailMapper;

    @Override
    public List<VMerchantPartDetail> getTenNewGoods() {
        return merchantPartDetailMapper.getTenNewGoods();
    }

    @Override
    public List<VMerchantPartDetail> getGoods(Integer cursor, Integer size) {
        List<VMerchantPartDetail> list = merchantPartDetailMapper.getGoodsFromCursor(cursor, size);
        list.forEach(item -> item.setAmount(0));
        return list;
    }

    @Override
    public List<VMerchantPartDetail> likeGoods(String inputMerchant, String inputPart) {
        return merchantPartDetailMapper.likeGoods(inputMerchant, inputPart);
    }
}
