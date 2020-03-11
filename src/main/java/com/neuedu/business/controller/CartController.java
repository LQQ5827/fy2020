package com.neuedu.business.controller;

import com.neuedu.business.common.Consts;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.common.StatusEnum;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.ICartService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @类 名： CartController <br/>
 * @描 述： <br/>
 * @日 期： 2020/3/10 16:07<br/>
 * @作 者： 鼠小倩<br/>
 * @版 本： 1.0.0
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/cart/")
public class CartController {
    @Autowired
    ICartService cartService;

    /**
     * 购物车列表
     */
    @RequestMapping("/list.do")
    public ServerResponse list(HttpSession session){
        //step1：先判断用户是否存在
        User user=(User) session.getAttribute(Consts.USER);
        if(user==null){//未登录
            return ServerResponse.serverResponseByFail(StatusEnum.NO_LOGIN.getStatus(),StatusEnum.NO_LOGIN.getDesc());
        }

        return cartService.list(user.getId());
    }

    /**
     * 添加购物车
     * @param productId
     * @param count
     * @param session
     * @return
     */
    @RequestMapping("add.do")
    public ServerResponse add(Integer productId,Integer count,HttpSession session){

        //step1:先判断用户是否登录
        User user=(User)session.getAttribute(Consts.USER);
        if(user==null){//未登录
            return ServerResponse.serverResponseByFail(StatusEnum.NO_LOGIN.getStatus(),StatusEnum.NO_LOGIN.getDesc());
        }
        return cartService.add(user.getId(),productId,count);
    }
}



