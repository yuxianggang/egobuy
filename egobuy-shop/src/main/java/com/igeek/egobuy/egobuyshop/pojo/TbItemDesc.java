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
public class TbItemDesc implements Serializable {

    private static final long serialVersionUID = 1582888311709L;


    /**
    * 主键
    * 商品ID
    * isNullAble:0
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    /**
    * 商品描述
    * isNullAble:1
    */
    private String itemDesc;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Date created;

    /**
    * 更新时间
    * isNullAble:1
    */
    private Date updated;


}
