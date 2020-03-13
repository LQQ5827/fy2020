package com.neuedu.business.service.impl;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.common.StatusEnum;
import com.neuedu.business.dao.ShippingMapper;
import com.neuedu.business.pojo.Shipping;
import com.neuedu.business.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements IShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    @Override
    public ServerResponse add(Shipping shipping) {


        int count=shippingMapper.insert(shipping);

        if(count<=0){
            return ServerResponse.serverResponseByFail(StatusEnum.ADDRESS_ADD_FAIL.getStatus(),StatusEnum.ADDRESS_ADD_FAIL.getDesc());
        }


        return ServerResponse.serverResponseBySuccess(null,shipping.getId());// STOPSHIP: 20/2/25 . ;
    }
}
