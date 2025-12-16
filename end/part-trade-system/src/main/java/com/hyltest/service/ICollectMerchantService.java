package com.hyltest.service;

/**
 * 收藏关系表（collect_merchant）相关业务
 */
public interface ICollectMerchantService {

    /**
     * 添加收藏记录
     *
     * @param customerId 顾客 id
     * @param merchantId 供应商 id
     */
    void addCollect(Integer customerId, Integer merchantId);

    /**
     * 取消收藏
     *
     * @param customerId 顾客 id
     * @param merchantId 供应商 id
     */
    void removeCollect(Integer customerId, Integer merchantId);
}
