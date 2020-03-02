package com.igeek.egobuy.egobuyshop.dto;

import lombok.Data;

/**
 * @author 余祥刚
 * @create 2020-02-28 19:56
 */
@Data
public class QueryVo {

    private Integer page;
    private Integer limit;
    private Long minPrice;
    private Long maxPrice;
    private String title;
    //卖点
    private String sellPoint;
}
