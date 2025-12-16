package com.hyltest.pojo.query;


import lombok.Data;
import org.springframework.ai.tool.annotation.ToolParam;

@Data
public class PartQuery {

    @ToolParam(required = false, description = "零件名称")
    private String name;

    @ToolParam(required = false, description = "零件颜色：1-红、2-黄、3-绿、4-蓝、5-白、6-黑")
    private Integer color;

    @ToolParam(required = false, description = "零件描述")
    private String description;
}
