package com.neuedu.business.service;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.pojo.Product;

public interface IProductService {
    ServerResponse addOrProduct(Product product);
    /**
     * 前台-商品检索
     */
    ServerResponse list(Integer categoryId, String keyword, Integer pageNum, Integer pageSize, String orderBy);

    /**
     * 前台-查看详情
     * */
    ServerResponse detail(Integer productId);
    /**
     * 商品扣库存
     * type: 0 ->减quantity库存
     *       1->加quantity库存
     * */
    ServerResponse updateStock(Integer productId,Integer quantity,int type);
}
