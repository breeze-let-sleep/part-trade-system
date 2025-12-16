package com.hyltest.mapper;


import com.hyltest.pojo.VO.UserVO;
import com.hyltest.pojo.entity.Merchant;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 供应商表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface MerchantMapper {

    /**
     * 注册供应商
     */
    @Insert("insert into merchant(name,password,create_time,update_time) values(#{name},#{password},#{createTime},#{updateTime})")
    void register(UserVO userVO);

    /**
     * 获取供应商数量
     */
    @Select("select count(*) from merchant")
    Integer getNum();

    /**
     * 登录查询
     */
    @Select("select id,name,password from merchant where name=#{name}")
    Merchant getInfoByName(UserVO userVO);

    /**
     * 根据供应商名称获取供应商id
     */
    @Select("select id from merchant where name=#{merchantName}")
    Integer getIdByName(String merchantName);

    /**
     * 根据id查询供应商信息
     */
    @Select("select id,name,phone,address,avatar,description from merchant where id=#{id}")
    Merchant selectById(Integer id);

    /**
     * 分页查询供应商信息
     */
    @Select("select id,name,phone,address,avatar,description,create_time,update_time from merchant limit #{currentPage},#{pageSize}")
    List<Merchant> selectPage(int currentPage, int pageSize);

    /**
     * 新增供应商信息
     */
    @Insert("insert into merchant(name,phone,password,address,avatar,description,create_time,update_time) values(#{name},#{phone},#{password},#{address},#{avatar},#{description},#{createTime},#{updateTime})")
    void insertNewMerchant(Merchant merchant);

    /**
     * 修改供应商信息
     */
    void updateMerchant(Merchant merchant);

    /**
     * 删除供应商信息
     */
    @Delete("delete from merchant where id= #{id}")
    int deleteById(Integer id);

    /**
     * 按条件模糊查询供应商列表
     */
    List<Merchant> searchMerchants(String likeName, String likeAddress, LocalDateTime startTime, LocalDateTime endTime);
}
