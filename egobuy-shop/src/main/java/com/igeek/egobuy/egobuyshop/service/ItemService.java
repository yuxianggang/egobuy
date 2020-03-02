package com.igeek.egobuy.egobuyshop.service;

import com.igeek.egobuy.egobuyshop.dto.QueryVo;
import com.igeek.egobuy.egobuyshop.pojo.TbItem;
import com.igeek.egobuy.util.ResponseEntity;


/**
 * @author 余祥刚
 * @create 2020-02-28 19:24
 */
public interface ItemService {
    TbItem getItemById(Long id);

    ResponseEntity list(QueryVo queryVo);

    int update(TbItem tbItem);
}
