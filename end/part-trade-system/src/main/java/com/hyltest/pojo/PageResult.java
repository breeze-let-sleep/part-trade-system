package com.hyltest.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页的结果集
 */
@Data
@Schema(description = "分页的结果集")
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    //总记录数
    @Schema(description = "总记录数", example = "100")
    private Integer total;
    //结果集
    @Schema(description = "结果集", example = "[{...}]")
    private List rows;
}
