package com.igeek.egobuy.egobuyshop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.igeek.egobuy.egobuyshop.dto.QueryVo;
import com.igeek.egobuy.egobuyshop.mapper.TbItemCatMapper;
import com.igeek.egobuy.egobuyshop.mapper.TbItemMapper;
import com.igeek.egobuy.egobuyshop.pojo.TbItem;
import com.igeek.egobuy.egobuyshop.pojo.TbItemCat;
import com.igeek.egobuy.egobuyshop.service.ItemService;
import com.igeek.egobuy.util.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 余祥刚
 * @create 2020-02-28 19:24
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public TbItem getItemById(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResponseEntity list(QueryVo queryVo) {

        PageHelper.startPage(queryVo.getPage(),queryVo.getLimit());

        Example example = new Example(TbItem.class);
        Example.Criteria criteria = example.createCriteria();
        //标题
        if(!StringUtils.isEmpty(queryVo.getTitle())){
            criteria.andLike("title","%"+queryVo.getTitle()+"%");
        }
        //卖点
        if(!StringUtils.isEmpty(queryVo.getSellPoint())){
            criteria.andLike("sellPoint","%"+queryVo.getSellPoint()+"%");
        }
        //价格最低值
        if(queryVo.getMinPrice() != 0 && !StringUtils.isEmpty(queryVo.getMinPrice())){
            criteria.andGreaterThanOrEqualTo("price",queryVo.getMinPrice());
        }
        //价格最高值
        if(queryVo.getMaxPrice() != 0 && !StringUtils.isEmpty(queryVo.getMaxPrice())){
            criteria.andLessThanOrEqualTo("price",queryVo.getMaxPrice());
        }
        //状态
        criteria.andNotEqualTo("status",3);

        List<TbItem> tbItems = tbItemMapper.selectByExample(example);

        PageInfo<TbItem> info = new PageInfo<>(tbItems);

        Map<String,Object> data = new HashMap<>();
        List<TbItem> itemList = info.getList();
        for (TbItem tbItem : itemList) {
            Long cid = tbItem.getCid();
            TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(cid);
            tbItem.setItemCat(tbItemCat);
        }
        data.put("data",itemList);
        data.put("total",info.getTotal());
        ResponseEntity responseEntity = ResponseEntity.success(data);
        return responseEntity;
    }

    @Override
    public int update(TbItem tbItem) {
        return tbItemMapper.updateByPrimaryKeySelective(tbItem);
    }
}
