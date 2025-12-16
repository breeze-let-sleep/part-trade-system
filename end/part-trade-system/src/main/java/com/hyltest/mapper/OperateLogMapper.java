package com.hyltest.mapper;


import com.hyltest.pojo.entity.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员操作日志表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface OperateLogMapper {

    /**
     * 新增管理员操作日志
     * @param operateLog 管理员操作日志实体对象（需包含name/password/phone字段，id自动生成）
     */
    @Insert("insert into operate_log(id,admin_id,method,create_time) values(#{id},#{adminId},#{method},#{createTime})")
    void insertNewOperateLog(OperateLog operateLog);
}
