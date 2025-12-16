package com.hyltest.mapper;


import com.hyltest.pojo.entity.VOperateLogWithAdmin;
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
public interface VOperateLogWithAdminMapper {

    /**
     * 获取操作日志数量
     * @return 操作日志数量
     */
    @Select("select count(*) from v_operate_log_with_admin")
    Integer getCount();

    /**
     * 分页请求操作日志列表
     * @param start 起始位置
     * @param pageSize 页大小
     * @return 操作日志列表
     */
    @Select("select id, admin_id, method, create_time, name from v_operate_log_with_admin limit #{start},#{pageSize}")
    List<VOperateLogWithAdmin> getLogList(int start, Integer pageSize);
}
