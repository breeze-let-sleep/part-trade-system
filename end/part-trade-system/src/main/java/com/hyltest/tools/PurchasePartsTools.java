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
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.Inet4Address;
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
        return partDetailService.likeQuery(query);
    }

    @Tool(description = "查询最新发布的在售零件")
    public List<VMerchantPartDetail> queryNewGoods() {
        return partDetailService.getTenNewGoods();
    }

    @Tool(description = "购买零件并生成订单，返回订单号")
    public String generateOrder(
            @ToolParam(required = true, description = "当前用户ID") Integer curId,
            @ToolParam(required = true, description = "供应商名称") String merchantName,
            @ToolParam(required = true, description = "零件名称") String partName,
            @ToolParam(required = true, description = "购买数量")Integer amount) {
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
        /*
        直接通过CurrentHolder.getCurrentId()获取不到当前用户的id信息，但是filter是可以获取到的
        可能原因：
        1、在Spring AI的函数调用场景下，AI框架可能使用了不同的线程来执行工具方法，导致ThreadLocal变量无法共享。
        2、如果AI工具调用发生在过滤器处理之后的异步操作中，此时ThreadLocal已经被清理。
        3、Spring AI的函数调用可能在独立的线程池中执行，而不是在原始HTTP请求线程中。
        正确原因：
        查看日志发现均处于同一个线程中[io-8080-exec-10]，所以不是线程的问题；真正应该是ai调用是异步，
        当进入对应的controller后，直接就返回了，清除了ThreadLocal，但是ai调用还没有结束，所以导致获取不到当前用户的id信息
        解决：放在HttpServletRequest中进行获取
         */
//        newOrder.setCustomerId(CurrentHolder.getCurrentId());
        //直接设置
        //todo 获取当前用户id存在问题
        newOrder.setCustomerId(curId);
        newOrder.setAmount(amount);
        newOrder.setTotalPrice(amount * singlePrice);
        newOrder.setCreateTime(LocalDateTime.now());
        //保存订单
        orderMapper.addOrder(newOrder);
        //返回订单号
        return String.valueOf(newOrder.getId());
    }

}

