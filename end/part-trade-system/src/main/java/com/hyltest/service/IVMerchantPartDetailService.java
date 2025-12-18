package com.hyltest.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import com.hyltest.pojo.query.PartQuery;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IVMerchantPartDetailService  extends IService<VMerchantPartDetail> {

    /**
     * 获取最新的十件在售零件
     * @return 最新的十件在售零件
     */
    List<VMerchantPartDetail> getTenNewGoods();

    /**
     * 从cursor处开始获取size条在售零件
     * @param cursor 最后一个商品的id
     * @param size 每页大小
     * @return 在售零件
     */
    List<VMerchantPartDetail> getGoods(Integer cursor, Integer size);

    /**
     * 模糊查询
     * @param inputMerchant 商家名称
     * @param inputPart 零件名称
     * @return 模糊查询结果
     */
    List<VMerchantPartDetail> likeGoods(String inputMerchant, String inputPart);

    /**
     * 根据用户提出条件模糊查询在售零件
     * @param query 查询条件
     * @return 模糊查询结果
     */
    List<VMerchantPartDetail> likeQuery(PartQuery query);
}
