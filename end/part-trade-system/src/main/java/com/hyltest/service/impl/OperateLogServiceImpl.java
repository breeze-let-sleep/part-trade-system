package com.hyltest.service.impl;

import com.hyltest.mapper.VOperateLogWithAdminMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.VOperateLogWithAdmin;
import com.hyltest.service.IOperateLogService;
import com.hyltest.service.IVOperateLogWithAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 管理员操作日志表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@RequiredArgsConstructor
@Service
public class OperateLogServiceImpl implements IOperateLogService {

    private final VOperateLogWithAdminMapper vOperateLogWithAdminMapper;

    @Override
    public PageResult getLogs(Integer currentPage, Integer pageSize) {
        PageResult pageResult = new PageResult();
        Integer total = vOperateLogWithAdminMapper.getCount();
        pageResult.setTotal(total);
        List<VOperateLogWithAdmin> rows = vOperateLogWithAdminMapper.getLogList((currentPage-1)*pageSize, pageSize);
        pageResult.setRows(rows);
        return pageResult;
    }
}
