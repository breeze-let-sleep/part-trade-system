package com.hyltest.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

/**
 * <p>
 * 收藏供应商表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface CollectMerchantMapper {

    /**
     * 添加收藏关系
     *
     * @param customerId 顾客id
     * @param merchantId 供应商id
     * @param now 创建时间
     */
    @Insert("insert into collect_merchant(customer_id, merchant_id,create_time) values(#{customerId}, #{merchantId}, #{now})")
    void addCollect(Integer customerId, Integer merchantId, LocalDateTime now);

    /**
     * 删除收藏关系
     *
     * @param customerId 顾客id
     * @param merchantId 供应商id
     */
    @Delete("delete from collect_merchant where customer_id=#{customerId} and merchant_id=#{merchantId}")
    void removeCollect(Integer customerId, Integer merchantId);
}
