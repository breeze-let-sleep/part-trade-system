package com.hyltest.service;

import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.Admin;

import java.time.LocalDateTime;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IAdminService{

    /**
     * 根据ID查询单个管理员
     * @param id 管理员主键ID（非空）
     * @return Admin 管理员实体对象（含id/name/phone等字段，不含敏感字段password）
     */
    Admin getAdminById(Integer id);


    /**
     * 分页获取管理员列表（返回完整分页数据：总记录数+当前页数据）
     * @param currentPage 当前页码（最小1，默认1）
     * @param pageSize 每页条数（最小1，默认10）
     * @return PageResult 完整分页结果（total=总记录数，rows=当前页数据）
     */
    PageResult getAdminPage(int currentPage, int pageSize); // 新增方法

    /**
     * 按名称模糊查询管理员列表
     *
     * @param input     模糊查询关键词（支持部分匹配，如"张"匹配"张三"）
     * @param startTime 查询开始时间
     * @param endTime   查询结束时间
     * @return List<Admin> 匹配的管理员列表（空列表表示无匹配数据）
     */
    PageResult searchAdminsByName(String input, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 新增管理员信息
     * @param admin 管理员实体对象（需包含name/password/phone字段，id自动生成）
     */
    void addAdmin(Admin admin);

    /**
     * 修改管理员信息
     * @param admin 管理员实体对象（需包含id字段，及要修改的其他字段）
     * @return Admin 修改后的管理员对象（从数据库重新查询，确保数据最新）
     */
    Admin updateAdmin(Admin admin);

    /**
     * 删除管理员信息
     * @param id 管理员主键ID（非空）
     * @return boolean 删除结果（true=删除成功，false=删除失败/无此管理员）
     */
    boolean deleteAdmin(Integer id);
}
