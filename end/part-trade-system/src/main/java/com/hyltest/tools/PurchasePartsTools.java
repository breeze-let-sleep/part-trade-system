package com.hyltest.tools;

import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.hyltest.mapper.MerchantMapper;
import com.hyltest.mapper.OrderMapper;
import com.hyltest.mapper.PartMapper;
import com.hyltest.pojo.entity.Order;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import com.hyltest.pojo.query.PartQuery;
import com.hyltest.service.IVMerchantPartDetailService;
import com.hyltest.utils.CurrentHolder;
import com.hyltest.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Component
public class PurchasePartsTools {

    private final IVMerchantPartDetailService partDetailService;
    private final OrderMapper orderMapper;
    private final MerchantMapper merchantMapper;
    private final PartMapper partMapper;


    @Tool(description = "根据条件查询在售零件")
    public List<VMerchantPartDetail> queryGoods(@ToolParam(required = false, description = "零件查询条件") PartQuery query) {
        QueryChainWrapper<VMerchantPartDetail> wrapper = partDetailService.query();

        wrapper
                .like(query.getName() != null, "partName", query.getName())
                .eq(query.getColor() != null, "color", query.getColor())
                .like(query.getDescription() != null, "description", query.getDescription());
        return wrapper.list();
    }

    @Tool(description = "查询最新发布的在售零件")
    public List<VMerchantPartDetail> queryNewGoods() {
        return partDetailService.getTenNewGoods();
    }

    @Tool(description = "生成购买零件的订单，并返回订单号")
    public String generateOrder(
            String merchantName, String partName, Integer amount) {
        Order newOrder = new Order();
        //根据商家名称查询商家id
        Integer merchantId =merchantMapper.getIdByName(merchantName);
        newOrder.setMerchantId(merchantId);
        //根据零件名称查询零件id和单价
        Integer partId = partMapper.getIdByName(partName);
        newOrder.setPartId(partId);
        Integer singlePrice = partMapper.getSinglePriceById(partId);
        //设置订单其他信息
        //在threadLocal中获取当前用户信息
        newOrder.setCustomerId(CurrentHolder.getCurrentId());
        newOrder.setAmount(amount);
        newOrder.setTotalPrice(amount * singlePrice);
        newOrder.setCreateTime(LocalDateTime.parse(TimeUtils.getCurrentTime()));
        //保存订单
        orderMapper.addOrder(newOrder);
        //返回订单号
        return String.valueOf(newOrder.getId());
    }

}

