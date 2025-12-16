package com.hyltest.service.impl;

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
        return merchantMapper.selectById(id);
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
        List<Merchant> list = merchantMapper.searchMerchants(likeName, likeAddress, startTime, endTime);
        pageResult.setRows(list);
        pageResult.setTotal(list.size());
        return pageResult;
    }


    @Override
    public void addMerchant(Merchant merchant) {
        merchant.setCreateTime(LocalDateTime.now());
        merchant.setUpdateTime(LocalDateTime.now());
        merchant.setPassword(MD5Utils.encrypt(merchant.getPassword()));
        merchantMapper.insertNewMerchant(merchant);
    }

    @Override
    public void updateMerchant(Merchant merchant) {
        merchant.setUpdateTime(LocalDateTime.now());
        // 密码加密
        String pwd = merchant.getPassword();

        if (pwd == null || pwd.trim().isEmpty()){
            merchant.setPassword(null);
        }else merchant.setPassword(MD5Utils.encrypt(pwd));
        merchantMapper.updateMerchant(merchant);
    }

    @Override
    public boolean deleteMerchant(Integer id) {
        return merchantMapper.deleteById(id) > 0;
    }
}