package com.sc.common.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageJsonResultVo<T> {
    private List<T> items;
    private long total; //总记录数
    private long page; //当前页
    private long pageSize; //每页多少条
    private long pages; //总页数
}
