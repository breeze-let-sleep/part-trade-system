package com.hyltest.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页的结果集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
    //总记录数
    private Long total;
    //结果集
    private List rows;
}
