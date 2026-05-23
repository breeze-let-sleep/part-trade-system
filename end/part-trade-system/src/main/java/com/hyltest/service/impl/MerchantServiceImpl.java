package com.hyltest.service.impl;

import com.hyltest.constant.MessageConstant;
import com.hyltest.exception.*;
import com.hyltest.mapper.MerchantMapper;
import com.hyltest.pojo.PageResult;
import com.hyltest.pojo.entity.Merchant;
import com.hyltest.service.IMerchantService;
import com.hyltest.utils.MD5Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 供应商表 服务实现类
 * 实现IMerchantService接口定义的所有业务逻辑
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@RequiredArgsConstructor // 替代@Autowired，final字段必用
@Service
public class MerchantServiceImpl implements IMerchantService {

    private final MerchantMapper merchantMapper;

    @Override
    public Merchant getMerchantById(Integer id) {
        try {
            return merchantMapper.selectById(id);
        } catch (Exception e) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
    }

    @Override
    public PageResult getMerchantPage(int currentPage, int pageSize) {
        PageResult pageResult = new PageResult();
        int total = merchantMapper.getNum();
        pageResult.setTotal(total);
        List<Merchant> rows = merchantMapper.selectPage((currentPage-1)*pageSize, pageSize);
        pageResult.setRows(rows);
        return pageResult;
    }

    @Override
    public PageResult searchMerchants(String likeName, String likeAddress, LocalDateTime startTime, LocalDateTime endTime) {
        PageResult pageResult = new PageResult();
        List<Merchant> list = null;
        try {
            list = merchantMapper.searchMerchants(likeName, likeAddress, startTime, endTime);
        } catch (Exception e) {
            throw new UnknownException(MessageConstant.UNKNOWN_ERROR);
        }
        pageResult.setRows(list);
        pageResult.setTotal(list.size());
        return pageResult;
    }


    @Override
    public void addMerchant(Merchant merchant) {
        merchant.setCreateTime(LocalDateTime.now());
        merchant.setUpdateTime(LocalDateTime.now());
        merchant.setPassword(MD5Utils.encrypt(merchant.getPassword()));
        try {
            merchantMapper.insertNewMerchant(merchant);
        } catch (Exception e) {
            throw new InsertFailException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @Override
    public void updateMerchant(Merchant merchant) {
        merchant.setUpdateTime(LocalDateTime.now());
        // 密码加密
        String pwd = merchant.getPassword();

        if (pwd == null || pwd.trim().isEmpty()){
            throw new UnknownException(MessageConstant.PASSWORD_ERROR);
        }else merchant.setPassword(MD5Utils.encrypt(pwd));
        try {
            merchantMapper.updateMerchant(merchant);
        } catch (Exception e) {
            throw new UpdateFailException(MessageConstant.UNKNOWN_ERROR);
        }
    }

    @Override
    public boolean deleteMerchant(Integer id) {
        try {
            return merchantMapper.deleteById(id) > 0;
        } catch (Exception e) {
            throw new DeletionNotAllowedException(MessageConstant.UNKNOWN_ERROR);
        }
    }
}