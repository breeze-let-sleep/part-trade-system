package com.hyltest.pojo.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

@Data
@Schema(description = "零件查询条件")
public class PartQuery {

    @Schema(description = "零件名称", example = "六角螺丝")
    @ToolParam(required = false, description = "零件名称")
    private String name;

    @Schema(description = "零件颜色：1-红、2-黄、3-绿、4-蓝、5-白、6-黑", example = "1")
    @ToolParam(required = false, description = "零件颜色：1-红、2-黄、3-绿、4-蓝、5-白、6-黑")
    private Integer color;

    @Schema(description = "零件描述", example = "不锈钢材质，物美价廉")
    @ToolParam(required = false, description = "零件描述")
    private String description;
}
