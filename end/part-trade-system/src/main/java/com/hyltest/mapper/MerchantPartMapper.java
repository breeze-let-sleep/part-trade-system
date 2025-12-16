package com.hyltest.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 供应商零件管理（商品表） Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface MerchantPartMapper {

    /**
     * 修改零件发布状态
     * @param partId 零件id
     * @param merchantId 供应商id
     * @param isPublish 零件发布状态（0：未发布；1：已发布）
     * @return 操作结果（1：成功；0：失败）
     */
    @Update("update merchant_part set is_publish = #{isPublish} where part_id = #{partId} and merchant_id = #{merchantId}")
    int updatePublishStatus(Integer partId, Integer merchantId, Integer isPublish);
}
