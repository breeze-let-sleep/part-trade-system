package com.hyltest.pojo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractVO {


    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 零件名称
     */
    private String partName;

    /**
     * 零件数量
     */
    private Integer amount;

    /**
     * 合计价格
     */
    private Integer TotalPrice;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
