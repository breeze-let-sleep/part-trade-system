package com.hyltest.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 后端统一返回结果
 */
@Data
@Schema(description = "后端统一返回结果")
public class Result {

    @Schema(description = "编码：1成功，0为失败", example = "1")
    private Integer code;
    @Schema(description = "提示信息", example = "success")
    private String msg;
    @Schema(description = "响应数据", example = "{...}")
    private Object data;

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
