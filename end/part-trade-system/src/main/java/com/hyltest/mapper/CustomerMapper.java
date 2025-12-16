package com.hyltest.mapper;


import com.hyltest.pojo.VO.UserVO;
import com.hyltest.pojo.entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 顾客表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface CustomerMapper {

    /**
     *  注册顾客
     */
    @Insert("insert into customer(name,password,create_time,update_time) values(#{name},#{password},#{createTime},#{updateTime})")
    void register(UserVO userVO);

    /**
     * 获取顾客数量
     */
    @Select("select count(*) from customer")
    Integer getNum();

    /**
     * 通过用户名获取顾客信息
     */
    @Select("select id,name,password from customer where name = #{name}")
    Customer getInfoByName(UserVO userVO);

    // 根据id查询顾客
    @Select("select id, name, phone, address, avatar from customer where id = #{id}")
    Customer selectById(Integer id);

    // 分页查询顾客列表
    @Select("select id, name, phone, address, avatar, create_time, update_time from customer limit #{start}, #{pageSize}")
    List<Customer> selectPage(Integer start, Integer pageSize);

    // 查询顾客总数
    @Select("select count(*) from customer")
    Integer selectTotalCount();

    // 新增顾客
    @Insert("insert into customer(id,name,password,phone,address,avatar,create_time,update_time) values(#{id},#{name},#{password},#{phone},#{address},#{avatar},#{createTime},#{updateTime})")
    int insertNewCustomer(Customer customer);

    // 更新顾客信息
    int updateCustomer(Customer customer);

    // 根据id删除顾客
    @Delete("delete from customer where id = #{id}")
    int deleteById(Integer id);


    /**
     * 模糊查询顾客列表
     */
    List<Customer> likeCustomers(String likeName, String likeAddress, LocalDateTime startTime, LocalDateTime endTime);
}
