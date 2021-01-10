package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public static final String CONTENT_TYPE_FORMED="application/x-www-form-urlencoded";
    //定义excptionHandler解决违背controller层吸收的exception
    @ExceptionHandler(Exception.class) //指定，当出现Exception这个异常的时候就执行相关的代码
    @ResponseStatus(HttpStatus.OK)  //让他以正常的状态返回
    @ResponseBody
    public CommonReturnType handlerException(HttpServletRequest request, Exception ex){
        ex.printStackTrace();
        Map<String, Object> responseData = new HashMap<>();
        if(ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            responseData.put("errCode", businessException.getErrCode());
            responseData.put("errMsg", businessException.getErrMsg());
            return CommonReturnType.create(responseData, "fail");
        }else {

            responseData.put("errCode",EmBusinessError.UNKNOWN_ERROR);
            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
            return CommonReturnType.create(responseData, "fail");
        }
    }
}