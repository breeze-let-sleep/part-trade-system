package com.hyltest.service.impl;

import com.hyltest.service.IAdminService;
import com.hyltest.utils.MD5Utils;
import org.springframework.stereotype.Service;
import com.hyltest.mapper.AdminMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.Admin;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements IAdminService {

    private final AdminMapper adminMapper;

    /**
     * 根据ID查询单个管理员
     * 调用Mapper查询数据库，返回管理员实体
     */
    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectById(id);
    }


    @Override
    public PageResult getAdminPage(int currentPage, int pageSize) {
        PageResult pageResult = new PageResult();
        Integer total = adminMapper.selectTotal();
        pageResult.setTotal(total);
        List<Admin> admins = adminMapper.selectPage((currentPage-1)*pageSize, pageSize);
        pageResult.setRows(admins);
        return pageResult;
    }

    /**
     * 按名称模糊查询管理员列表
     * 调用Mapper的模糊查询方法，返回匹配结果
     */
    @Override
    public PageResult searchAdminsByName(String input, LocalDateTime startTime, LocalDateTime endTime) {
        PageResult pageResult = new PageResult();
        List<Admin> list = adminMapper.selectByLike(input, startTime, endTime);
        pageResult.setRows(list);
        pageResult.setTotal(list.size());
        return pageResult;
    }

    /**
     * 新增管理员信息
     * 1. 设置创建/更新时间 → 2. 调用Mapper插入数据库 → 3. 返回新增实体（含自增ID）
     */
    @Override
    public void addAdmin(Admin admin) {
        admin.setCreateTime(LocalDateTime.now());
        admin.setUpdateTime(LocalDateTime.now());
        admin.setPassword(MD5Utils.encrypt(admin.getPassword()));
        adminMapper.insertNewAdmin(admin);
    }

    /**
     * 修改管理员信息
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        admin.setUpdateTime(LocalDateTime.now());
        String pwd = admin.getPassword();
        if (pwd == null || pwd.trim().isEmpty()){
            admin.setPassword(null);
        }else admin.setPassword(MD5Utils.encrypt(pwd));
        adminMapper.updateAdmin(admin);
        return adminMapper.selectById(admin.getId());
    }

    /**
     * 删除管理员信息
     * 调用Mapper删除数据库记录，返回删除结果（受影响行数>0表示成功）
     */
    @Override
    public boolean deleteAdmin(Integer id) {
        return adminMapper.deleteById(id) > 0;
    }
}
