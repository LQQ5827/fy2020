package com.neuedu.business.controller;

import com.neuedu.business.annotation.AutoIdempontent;
import com.neuedu.business.common.Consts;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/order/")
public class OrderController {

    @Autowired
    IOrderService orderService;

    //@AutoIdempontent
    @RequestMapping("create.do")
    public ServerResponse create(Integer shippingId, HttpSession session){

//        //step1:先判断用户是否登录
        User user=(User)session.getAttribute(Consts.USER);
//        if(user==null){//未登录
//            return ServerResponse.serverResponseByFail(StatusEnum.NO_LOGIN.getStatus(),StatusEnum.NO_LOGIN.getDesc());
//        }
        return orderService.createOrder(user.getId(),shippingId);
    }

    @RequestMapping("cancel.do")
    public ServerResponse cancel(Long orderNo){
        return orderService.cancelOrder(orderNo);
    }
}
