package com.hyltest.service;


import com.hyltest.pojo.PageResult;

/**
 * <p>
 * VIEW 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IVMerchantCollectInfoService{

    /**
     * 模糊分页获取收藏的供应商列表
     *
     * @param inputMerchant 供应商名称
     * @param customerId    用户id
     * @param currentPage 当前页
     * @param pageSize    每页数量
     * @return 供应商列表
     */
    PageResult likeCollects(String inputMerchant, Integer customerId, Integer currentPage, Integer pageSize);

    /**
     * 分页获取收藏的供应商列表
     *
     * @param currentPage 当前页
     * @param pageSize    每页数量
     * @return 供应商列表
     */
    PageResult pageCollects(Integer currentPage, Integer pageSize);
}
