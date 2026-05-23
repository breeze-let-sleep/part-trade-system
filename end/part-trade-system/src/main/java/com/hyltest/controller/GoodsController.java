package com.hyltest.controller;


import com.hyltest.pojo.Result;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import com.hyltest.service.IVMerchantPartDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "Authorization")
@Tag(name = "商品接口",description = "用于获取商品信息")
public class GoodsController {

    private final IVMerchantPartDetailService iVMerchantPartDetailService;

    @Operation(summary = "分页获取商品",description = "分页获取商品信息，从cursor开始返回指定数量的商品信息")
    @GetMapping("/getGoods")
    public Result getGoods(Integer cursor,Integer size){
        log.info("分页获取商品：最后一条商品的id（cursor）: {}, 返回的记录数size: {}", cursor, size);
        List<VMerchantPartDetail> list = iVMerchantPartDetailService.getGoods(cursor, size);
        return Result.success(list);
    }

    @Operation(summary = "模糊查询商品",description = "模糊查询商品信息")
    @GetMapping("likeGoods")
    public Result likeGoods(@RequestParam(required = false) String inputMerchant,@RequestParam(required = false) String inputPart){
        log.info("模糊查询商品：输入的供应商名称inputMerchant: {}, 输入的零件名称inputPart: {}", inputMerchant, inputPart);
        List<VMerchantPartDetail> list = iVMerchantPartDetailService.likeGoods(inputMerchant,inputPart);
        log.info("模糊查询商品结果：{}", list);
        return Result.success(list);
    }
}
