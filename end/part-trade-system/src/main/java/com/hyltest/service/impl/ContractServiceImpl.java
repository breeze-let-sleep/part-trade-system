package com.hyltest.service.impl;

import com.hyltest.mapper.ContractMapper;
import com.hyltest.mapper.OrderMapper;
import com.hyltest.service.IContractService;
import com.hyltest.utils.CurrentHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 订单合同表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@RequiredArgsConstructor
@Service
public class ContractServiceImpl implements IContractService {

    private final ContractMapper contractMapper;
    private final OrderMapper orderMapper;

    @Override
    public String signature(Integer orderId, String value) {
        Integer userId = CurrentHolder.getCurrentId();
        //todo 存在问题
        //只展示对应角色未签名的合同，因此直接通过id对该订单进行签名即可
        //判断当前用户是供应商还是顾客
        if (userId < 200000) {
            //供应商
            //对订单进行签名
            contractMapper.signatureToMerchant(orderId,value);
        }else {
            //顾客
            contractMapper.signatureToCustomer(orderId,value);
        }
        //判断订单是否已经全部完成签名，是则设置订单状态为成功
        Map<String, String> map = contractMapper.getContractStatus(orderId);
        String val3 = map.get("merchant_signature");
        String val4 = map.get("customer_signature");
        System.out.println(map);
        System.out.println(val3);
        System.out.println(val4);
        if (val3 != null && val4 != null) {
            orderMapper.updateOrderSuccess(orderId);
        }
        return null;
    }
}
