package com.hyltest.mapper;


import com.hyltest.pojo.entity.Part;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 零件表 Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Mapper
public interface PartMapper {

    /**
     * 根据零件名称获取零件id
     */
    @Select("select id from part where name=#{partName}")
    Integer getIdByName(String partName);

    /**
     * 根据零件id获取零件单价
     */
    @Select("select single_price from part where id = #{partId}")
    Integer getSinglePriceById(Integer partId);

    /**
     * 新增零件信息
     */
    @Insert("insert into part(id,merchant_id,name,color,weight,description,inventory,single_price,create_time,update_time) values(#{id},#{merchantId},#{name},#{color},#{weight},#{description},#{inventory},#{singlePrice},#{createTime},#{updateTime})")
    int insertNewPart(Part part);

    /**
     * 修改零件信息
     */
    int updatePart(Part part);

    /**
     * 删除零件信息
     */
    @Delete("delete from part where id=#{id} and merchant_id=#{merchantId}")
    int deletePartById(Integer id, Integer merchantId);
}
