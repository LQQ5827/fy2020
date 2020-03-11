package com.neuedu.business.controller;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @类 名： ProductFrontController <br/>
 * @描 述： <br/>
 * @日 期： 2020/2/29 16:29<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/product/")
public class ProductFrontController {
    @Autowired
    IProductService productService;

    /**
     * 产品搜索及动态排序List
     * @param categoryId类别id
     * @param keyword 关键字
     * @param pageNum 第几页
     * @param pageSize 一页多少条数据
     * @orderBy 按照字段排序 fieldname_desc/filedname_asc
     * */

    @RequestMapping("list.do")
    public ServerResponse list(
            @RequestParam(required = false,defaultValue = "-1") Integer categoryId,
            @RequestParam(required = false,defaultValue = "") String  keyword,
            @RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer   pageSize,
            @RequestParam(required = false,defaultValue = "") String  orderBy){
        return productService.list(categoryId, keyword, pageNum, pageSize, orderBy);
    }


    @RequestMapping("detail.do")
    public ServerResponse detail(Integer productId){
        return  productService.detail(productId);
    }
}



