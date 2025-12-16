package com.hyltest.service;


import com.hyltest.pojo.PageResult;

/**
 * <p>
 * 管理员操作日志表 服务类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
public interface IOperateLogService{

    /**
     * 获取操作日志列表
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @return 操作日志列表
     */
    PageResult getLogs(Integer currentPage, Integer pageSize);
}
