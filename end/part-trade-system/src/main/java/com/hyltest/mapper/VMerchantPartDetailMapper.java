package com.hyltest.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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
public interface VMerchantPartDetailMapper extends BaseMapper<VMerchantPartDetail> {

    /**
     * 获取最新的十件在售零件
     * @return 最新的十件在售零件
     */
    @Select("select * from v_merchant_part_detail order by create_time desc limit 10")
    List<VMerchantPartDetail> getTenNewGoods();

    /**
     * 获取指定位置后的在售零件
     * @param cursor 指定的位置
     * @param size 获取的零件数量
     * @return 指定位置的零件
     */
    List<VMerchantPartDetail> getGoodsFromCursor(Integer cursor, Integer size);

    /**
     * 获取指定供应商的零件数量
     * @param merchantId 供应商id
     * @return 零件数量
     */
    @Select("select count(*) from v_merchant_part_detail where merchant_id = #{merchantId}")
    Integer selectTotalPartByMerchantId(Integer merchantId);

    /**
     * 获取指定供应商的零件列表
     * @param merchantId 供应商id
     * @param start 起始索引
     * @param pageSize 页大小
     * @return 零件列表
     */
    List<VMerchantPartDetail> selectPartPageByMerchantId(Integer merchantId, Integer start, Integer pageSize);

    /**
     * 模糊查询零件
     *
     * @param merchantId 供应商id
     * @param partId     零件id
     * @param partName   零件名称（模糊）
     * @param weight     零件重量
     * @param color      零件颜色
     * @param startTime  创建时间开始
     * @param endTime    创建时间结束
     * @param isPublish  发布状态（0：未发布；1：已发布）
     * @return 零件列表
     */
    List<VMerchantPartDetail> selectLike(Integer merchantId, Integer partId, String partName, Double weight, Integer color, LocalDateTime startTime, LocalDateTime endTime, Integer isPublish);

    /**
     * 模糊查询零件
     *
     * @param inputMerchant 供应商名称（模糊）
     * @param inputPart    零件名称（模糊）
     * @return 零件列表
     */
    List<VMerchantPartDetail> likeGoods(String inputMerchant, String inputPart);
}
