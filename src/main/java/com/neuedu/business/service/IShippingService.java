package com.neuedu.business.service;

import com.neuedu.business.common.ServerResponse;
import com.neuedu.business.pojo.Shipping;

public interface IShippingService {

    ServerResponse add(Shipping shipping);
}
