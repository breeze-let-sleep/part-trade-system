package com.hyltest.service;

import com.hyltest.pojo.entity.Part;
import com.hyltest.pojo.PageResult;

import java.time.LocalDateTime;

/**
 * <p>
 * 零件表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IPartService {
    /**
     * 分页获取零件列表（支持按供应商id筛选）
     * @param currentPage 当前页
     * @param pageSize 每页数量
     * @return 分页结果
     */
    PageResult getPartPage(Integer currentPage, Integer pageSize);

    /**
     * 模糊查询零件
     *
     * @param partId    零件id
     * @param partName  零件名称（模糊）
     * @param weight    重量
     * @param color     颜色
     * @param startTime 创建时间开始
     * @param endTime   创建时间结束
     * @param isPublish 发布状态（0：未发布；1：已发布）
     * @return 分页结果
     */
    PageResult likePart(Integer partId, String partName, Double weight, Integer color, LocalDateTime startTime, LocalDateTime endTime, Integer isPublish);

    /**
     * 新增零件（含merchant_part关联）
     * @param part 零件对象
     * @return 操作结果（1：成功；0：失败）
     */
    int addPart(Part part);

    /**
     * 修改零件信息
     * @param part 零件对象（含id和merchantId）
     * @return 操作结果（1：成功；0：失败）
     */
    int updatePart(Part part);

    /**
     * 修改零件发布状态
     * @param id 零件id
     * @param isPublish 发布状态（0：未发布；1：已发布）
     * @return 操作结果（1：成功；0：失败）
     */
    int publishPart(Integer id, Integer isPublish);

    /**
     * 删除零件（含merchant_part关联）
     * @param id 零件id
     * @return 操作结果（1：成功；0：失败）
     */
    int deletePart(Integer id);
}
