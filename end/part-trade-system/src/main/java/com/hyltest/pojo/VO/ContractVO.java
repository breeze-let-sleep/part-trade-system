package com.hyltest.pojo.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Schema(description = "合同信息")
@AllArgsConstructor
@NoArgsConstructor
public class ContractVO {


    /**
     * 订单id
     */
    @Schema(description = "订单id", example = "1")
    private Integer orderId;

    /**
     * 零件名称
     */
    @Schema(description = "零件名称", example = "六角螺丝")
    private String partName;

    /**
     * 零件数量
     */
    @Schema(description = "零件数量", example = "10")
    private Integer amount;

    /**
     * 合计价格
     */
    @Schema(description = "合计价格", example = "500")
    private Integer TotalPrice;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间", example = "2025-11-21 12:00:00")
    private LocalDateTime createTime;

}
