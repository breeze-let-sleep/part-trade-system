package com.hyltest.service.impl;

import com.hyltest.constant.MessageConstant;
import com.hyltest.exception.DeletionNotAllowedException;
import com.hyltest.exception.InsertFailException;
import com.hyltest.exception.UnknownException;
import com.hyltest.exception.UpdateFailException;
import com.hyltest.mapper.CustomerMapper;
import com.hyltest.pojo.entity.Customer;
import com.hyltest.pojo.PageResult;
import com.hyltest.service.ICustomerService;
import com.hyltest.utils.MD5Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 顾客表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final CustomerMapper customerMapper;

    @Override
    public Customer getCustomerInfo(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public PageResult getCustomerPage(Integer currentPage, Integer pageSize) {
        PageResult pageResult = new PageResult();
        // 计算起始索引
        Integer start = (currentPage - 1) * pageSize;
        List<Customer> customerList = null;
        Integer totalCount = null;
        try {
            // 查询分页数据
            customerList = customerMapper.selectPage(start, pageSize);
            // 查询总记录数
            totalCount = customerMapper.selectTotalCount();
        } catch (Exception e) {
            throw new UnknownException(MessageConstant.UNKNOWN_ERROR);
        }
        // 封装结果
        pageResult.setTotal(totalCount);
        pageResult.setRows(customerList);
        return pageResult;
    }

    @Override
    public int addCustomer(Customer customer) {
        customer.setCreateTime(LocalDateTime.now());
        customer.setUpdateTime(LocalDateTime.now());
        customer.setPassword(MD5Utils.encrypt(customer.getPassword()));
        try {
            return customerMapper.insertNewCustomer(customer);
        } catch (Exception e) {
            throw new InsertFailException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @Override
    public int updateCustomer(Customer customer) {
        customer.setUpdateTime(LocalDateTime.now());
        String pwd = customer.getPassword();
        if (pwd == null || pwd.trim().isEmpty()){
            customer.setPassword(null);
        }else customer.setPassword(MD5Utils.encrypt(pwd));
        try {
            return customerMapper.updateCustomer(customer);
        } catch (Exception e) {
            throw new UpdateFailException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @Override
    public int deleteCustomer(Integer id) {
        try {
            return customerMapper.deleteById(id);
        } catch (Exception e) {
            throw new DeletionNotAllowedException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @Override
    public PageResult likeCustomers(String likeName, String likeAddress, LocalDateTime startTime, LocalDateTime endTime) {
        PageResult pageResult = new PageResult();
        List<Customer> customerList = List.of();
        try {
            customerList = customerMapper.likeCustomers(likeName, likeAddress, startTime, endTime);
        } catch (Exception e) {
            throw new UnknownException(MessageConstant.UNKNOWN_ERROR);
        }
        pageResult.setTotal(customerList.size());
        pageResult.setRows(customerList);
        return pageResult;
    }


}