package com.neuedu.business.controller;

import com.neuedu.business.common.Consts;
import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.pojo.Shipping;
import com.neuedu.business.pojo.User;
import com.neuedu.business.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/shipping/")
public class ShippingController {

    @Autowired
    IShippingService iShippingService;
    @RequestMapping("add.do")
    public ServerResponse add(Shipping shipping, HttpSession session){
       User user=(User) session.getAttribute(Consts.USER);
       shipping.setUserId(user.getId());
       return iShippingService.add(shipping);
    }

}
