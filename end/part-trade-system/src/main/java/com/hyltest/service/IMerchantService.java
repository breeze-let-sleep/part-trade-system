package com.hyltest.service;


import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.Merchant;

import java.time.LocalDateTime;

/**
 * <p>
 * 供应商表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IMerchantService{

    /**
     * 根据ID查询单个供应商
     * @param id 供应商主键ID（非空）
     * @return Merchant 供应商实体对象（含id/name/phone等字段，不含敏感字段password）
     */
    Merchant getMerchantById(Integer id);

    /**
     * 分页获取供应商列表（返回完整分页数据：总记录数+当前页数据）
     * @param currentPage 当前页码（最小1，默认1）
     * @param pageSize 每页条数（最小1，默认10）
     * @return PageResult 完整分页结果（total=总记录数，rows=当前页数据）
     */
    PageResult getMerchantPage(int currentPage, int pageSize);

    /**
     * 按条件模糊查询供应商列表
     *
     * @param likeName    模糊查询关键词（支持部分匹配，如"张"匹配"张三"）
     * @param likeAddress 模糊查询关键词（支持部分匹配，如"张"匹配"张三"）
     * @param startTime   模糊查询开始时间
     * @param endTime     模糊查询结束时间
     * @return List<Merchant> 匹配的供应商列表（空列表表示无匹配数据）
     */
    PageResult searchMerchants(String likeName, String likeAddress, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 新增供应商信息
     * @param merchant 供应商实体对象（需包含name/password/phone字段，id自动生成）
     */
    void addMerchant(Merchant merchant);

    /**
     * 修改供应商信息
     * @param merchant 供应商实体对象（需包含id字段，及要修改的其他字段）
     */
    void updateMerchant(Merchant merchant);

    /**
     * 删除供应商信息
     * @param id 供应商主键ID（非空）
     * @return boolean 删除结果（true=删除成功，false=删除失败/无此供应商）
     */
    boolean deleteMerchant(Integer id);
}
