package com.hyltest.mapper;


import com.hyltest.pojo.entity.VMerchantCollectInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface VMerchantCollectInfoMapper {

    /**
     * 获取收藏供应商数量
     * @return 收藏供应商数量
     */
    @Select("select count(*) from v_merchant_collect_info where customer_id = #{curId}")
    Integer getCount(Integer curId);

    /**
     * 模糊查询已收藏的供应商
     * @param inputMerchant 供应商名称
     * @param customerId 顾客id
     * @param start 起始位置
     * @param pageSize 页大小
     * @return 已收藏的供应商列表
     */
    List<VMerchantCollectInfo> likeCollects(String inputMerchant, Integer customerId, int start, Integer pageSize);

    /**
     * 分页请求收藏的供应商列表
     *
     * @param curId     当前用户id
     * @param start    起始位置
     * @param pageSize 页大小
     * @return 收藏的供应商列表
     */
    @Select("select merchant_id, name, phone, address, avatar, description, customer_id, create_time from v_merchant_collect_info where v_merchant_collect_info.customer_id = #{curId} limit #{start},#{pageSize}")
    List<VMerchantCollectInfo> getCollectList(Integer curId, int start, Integer pageSize);
}
