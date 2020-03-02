package com.igeek.egobuy.egobuyshop.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
/**
*
*  @author author
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TbItemParam implements Serializable {

    private static final long serialVersionUID = 1582888317973L;


    /**
    * 主键
    * 
    * isNullAble:0
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 商品类目ID
    * isNullAble:1
    */
    private Long itemCatId;

    /**
    * 参数数据，格式为json格式
    * isNullAble:1
    */
    private String paramData;

    /**
    * 
    * isNullAble:1
    */
    private Date created;

    /**
    * 
    * isNullAble:1
    */
    private Date updated;


}
