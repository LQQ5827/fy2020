package com.neuedu.business.service;

import com.neuedu.business.common.ServerResponse;
import java.util.Map;

public interface IpayService {

    ServerResponse pay(Long orderNo);

    String callbackLogic(Map<String, String> signParam);

}
