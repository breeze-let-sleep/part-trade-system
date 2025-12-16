package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import com.hyltest.service.IVMerchantPartDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private final IVMerchantPartDetailService iVMerchantPartDetailService;

    @GetMapping("/getGoods")
    public Result getGoods(Integer cursor,Integer size){
        log.info("分页获取商品：最后一条商品的id（cursor）: {}, 返回的记录数size: {}", cursor, size);
        List<VMerchantPartDetail> list = iVMerchantPartDetailService.getGoods(cursor, size);
        return Result.success(list);
    }

    @GetMapping("likeGoods")
    public Result likeGoods(@RequestParam(required = false) String inputMerchant,@RequestParam(required = false) String inputPart){
        log.info("模糊查询商品：输入的供应商名称inputMerchant: {}, 输入的零件名称inputPart: {}", inputMerchant, inputPart);
        List<VMerchantPartDetail> list = iVMerchantPartDetailService.likeGoods(inputMerchant,inputPart);
        log.info("模糊查询商品结果：{}", list);
        return Result.success(list);
    }
}
