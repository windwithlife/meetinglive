package xyz.staffjoy.account.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.staffjoy.account.AccountConstant;
import xyz.staffjoy.account.dto.*;
import xyz.staffjoy.common.api.BaseResponse;
import xyz.staffjoy.common.auth.AuthConstant;
import xyz.staffjoy.common.validation.Group1;
import xyz.staffjoy.common.validation.PhoneNumber;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.simple.server.auto.generic.dto.*;

@FeignClient(name ="Generic", path = /v1/generic, url = "${staffjoy.account-service-endpoint}")
// TODO Client side validation can be enabled as needed
// @Validated
public interface GenericClient {
    
   
}
