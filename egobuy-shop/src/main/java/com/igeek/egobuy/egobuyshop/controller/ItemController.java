package com.igeek.egobuy.egobuyshop.controller;

import com.igeek.egobuy.egobuyshop.dto.QueryVo;
import com.igeek.egobuy.egobuyshop.pojo.TbItem;
import com.igeek.egobuy.egobuyshop.service.ItemService;
import com.igeek.egobuy.exception.CustomException;
import com.igeek.egobuy.exception.CustomExceptionType;
import com.igeek.egobuy.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 余祥刚
 * @create 2020-02-28 19:22
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/list")
    public ResponseEntity list(@RequestBody QueryVo queryVo){
        return itemService.list(queryVo);
    }

    @RequestMapping("/update")
    public ResponseEntity update(@RequestBody TbItem tbItem){
        if(tbItem.getId() != null && tbItem.getId() !=0){
            int result = itemService.update(tbItem);
            String msg;
            if(result == 1){
                msg = "修改成功";
            }else{
                msg = "修改失败";
            }
            return ResponseEntity.success(msg);
        }
        return ResponseEntity.fail(new CustomException(CustomExceptionType.USER_ERROR,"商品编号为空"));
    }


    @RequestMapping("/{id}")
    public TbItem getId(@PathVariable Long id){
        return itemService.getItemById(id);
    }

}
