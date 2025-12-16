package com.hyltest.service.impl;

import com.hyltest.mapper.MerchantPartMapper;
import com.hyltest.mapper.VMerchantPartDetailMapper;
import com.hyltest.pojo.entity.VMerchantPartDetail;
import com.hyltest.service.IPartService;
import com.hyltest.utils.CurrentHolder;
import org.springframework.stereotype.Service;
import com.hyltest.mapper.PartMapper;
import com.hyltest.pojo.entity.Part;
import com.hyltest.pojo.PageResult;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
/**
 * <p>
 * 零件表 服务实现类
 * </p>
 *
 * @author Huang
 * @since 2025-11-21
 */
@Service
@RequiredArgsConstructor
public class PartServiceImpl implements IPartService {

    private final PartMapper partMapper;
    private final VMerchantPartDetailMapper vMerchantPartDetailMapper;
    private final MerchantPartMapper merchantPartMapper;
    // 当前登录供应商id
    private Integer merchantId;

    @Override
    public PageResult getPartPage(Integer currentPage, Integer pageSize) {
        merchantId=CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        // 计算起始索引
        Integer start = (currentPage - 1) * pageSize;
        // 查询分页数据
        List<VMerchantPartDetail> partList = vMerchantPartDetailMapper.selectPartPageByMerchantId(merchantId, start, pageSize);
        // 查询总记录数
        Integer totalCount = vMerchantPartDetailMapper.selectTotalPartByMerchantId(merchantId);
        // 封装结果
        pageResult.setTotal(totalCount);
        pageResult.setRows(partList);
        return pageResult;
    }

    @Override
    public PageResult likePart(Integer partId, String partName, Double weight, Integer color, LocalDateTime startTime, LocalDateTime endTime, Integer isPublish) {
        merchantId=CurrentHolder.getCurrentId();
        PageResult pageResult = new PageResult();
        // 模糊查询数据
        List<VMerchantPartDetail> partList = vMerchantPartDetailMapper.selectLike(merchantId, partId, partName, weight, color, startTime, endTime, isPublish);
        // 封装结果
        pageResult.setTotal(partList.size());
        pageResult.setRows(partList);
        return pageResult;
    }

    @Override
    public int addPart(Part part) {
        merchantId=CurrentHolder.getCurrentId();
        part.setCreateTime(LocalDateTime.now());
        part.setUpdateTime(LocalDateTime.now());
        part.setMerchantId(merchantId);
        // 新增零件到part表(通过触发器可以直接关联到零件管理表，不需要再次执行操作)
        int partResult = partMapper.insertNewPart(part);
        return partResult > 0 ? 1 : 0;
    }

    @Override
    public int updatePart(Part part) {
        merchantId=CurrentHolder.getCurrentId();
        part.setMerchantId(merchantId);
        part.setUpdateTime(LocalDateTime.now());
        return partMapper.updatePart(part);
    }

    @Override
    public int publishPart(Integer id, Integer isPublish) {
        merchantId=CurrentHolder.getCurrentId();
        return merchantPartMapper.updatePublishStatus(id, merchantId, isPublish);
    }

    @Override
    public int deletePart(Integer id) {
        merchantId=CurrentHolder.getCurrentId();
        // 删除零件从part表(通过触发器可以直接关联到零件管理表，不需要再次执行操作)
        int partResult = partMapper.deletePartById(id,merchantId);
        return partResult > 0 ? 1 : 0;
    }
}