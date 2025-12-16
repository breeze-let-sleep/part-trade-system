package com.hyltest.service;

import com.hyltest.pojo.entity.Customer;
import com.hyltest.pojo.PageResult;

import java.time.LocalDateTime;

/**
 * <p>
 * 顾客表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface ICustomerService {
    /**
     * 根据id获取单个顾客信息
     * @param id 顾客id
     * @return 顾客信息
     */
    Customer getCustomerInfo(Integer id);

    /**
     * 分页获取顾客列表
     * @param currentPage 当前页
     * @param pageSize 每页数量
     * @return 分页结果（总记录数+顾客列表）
     */
    PageResult getCustomerPage(Integer currentPage, Integer pageSize);

    /**
     * 新增顾客
     * @param customer 顾客对象
     * @return 操作结果（1：成功；0：失败）
     */
    int addCustomer(Customer customer);

    /**
     * 修改顾客信息
     * @param customer 顾客对象（含id）
     * @return 操作结果（1：成功；0：失败）
     */
    int updateCustomer(Customer customer);

    /**
     * 根据id删除顾客
     * @param id 顾客id
     * @return 操作结果（1：成功；0：失败）
     */
    int deleteCustomer(Integer id);

    /**
     * 模糊查询顾客列表
     * @param likeName 顾客姓名（模糊匹配）
     * @param likeAddress 顾客地址（模糊匹配）
     * @param startTime 创建时间开始
     * @param endTime 创建时间结束
     * @return 顾客列表
     */
    PageResult likeCustomers(String likeName, String likeAddress, LocalDateTime startTime, LocalDateTime endTime);

}