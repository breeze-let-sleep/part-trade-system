package com.hyltest.mapper;

import com.hyltest.pojo.VO.UserVO;
import com.hyltest.pojo.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface AdminMapper {

    /**
     * 通过名字获取管理员信息（login）
     */
    @Select("select id,name,password from admin where name = #{name}")
    Admin getInfoByName(UserVO userVO);


    /**
     * 通过ID查询单个管理员
     * @param id 管理员主键ID（非空）
     * @return Admin 管理员实体对象（含id/name/phone等字段，不含敏感字段password）
     */
    @Select("select id,name,phone,avatar from admin where id = #{id}")
    Admin selectById(Integer id);

    /**
     * 按条件模糊查询管理员列表
     * @param input      模糊查询关键词（支持部分匹配，如"张"匹配"张三"）
     * @param startTime 模糊查询开始时间
     * @param endTime 模糊查询结束时间
     * @return List<Admin> 匹配的管理员列表（空列表表示无匹配数据）
     */
    List<Admin> selectByLike(String input, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 新增管理员信息
     * @param admin 管理员实体对象（需包含name/password/phone字段，id自动生成）
     */
    @Insert("insert into admin(id,name,password,phone,avatar,create_time,update_time) values(#{id},#{name},#{password},#{phone},#{avatar},#{createTime},#{updateTime})")
    void insertNewAdmin(Admin admin);

    /**
     * 修改管理员信息
     * @param admin 管理员实体对象（需包含id字段，及要修改的其他字段）
     */
    void updateAdmin(Admin admin);

    /**
     * 删除管理员信息
     * @param id 管理员主键ID（非空）
     * @return boolean 删除结果（true=删除成功，false=删除失败/无此管理员）
     */
    @Delete("delete from admin where id = #{id}")
    int deleteById(Integer id);

    /**
     * 获取管理员总数
     * @return Integer 管理员总数
     */
    @Select("select count(*) from admin")
    Integer selectTotal();

    /**
     * 分页获取管理员列表
     * @param currentPage 当前页码（最小1，默认1）
     * @param pageSize 每页条数（最小1，默认10）
     * @return List<Admin> 分页后的管理员列表（空列表表示无数据）
     */
    @Select("select id,name,phone,avatar,create_time,update_time from admin limit #{currentPage},#{pageSize}")
    List<Admin> selectPage(int currentPage, int pageSize);

    /**
     * 通过ID获取管理员名称
     * @param id 管理员主键ID（非空）
     * @return String 管理员名称
     */
    @Select("select name from admin where id = #{id}")
    String getNameById(Integer id);
}
