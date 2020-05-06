package com.sc.common.dto;

import lombok.Data;

@Data
public class PageDto {
    private long page = 1;
    private long pageSize = 10;
}
