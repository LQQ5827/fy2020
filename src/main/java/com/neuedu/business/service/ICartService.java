package com.neuedu.business.service;

import com.neuedu.business.common.ServerResponse;

public interface ICartService {
    /**
     * 查看购物车列表
     * */
    ServerResponse list(Integer userId);
    /**
     * 购物车中添加商品
     * */

    ServerResponse add(Integer userId,Integer productId,Integer count);
}
