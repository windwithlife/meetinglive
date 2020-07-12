package com.simple.server.auto.generic.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "Generic", path = "/v1/generic", url = "${staffjoy.account-service-endpoint}")
// TODO Client side validation can be enabled as needed
// @Validated
public interface GenericClient {
    
   
}
