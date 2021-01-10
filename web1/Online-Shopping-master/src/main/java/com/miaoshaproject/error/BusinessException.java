package com.miaoshaproject.error;

//包装器
public class BusinessException extends Exception implements CommonError{

    private CommonError commonError;

    //接收EmBussinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError=commonError;
    }


    //接收errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError=commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.setErrMsg(errMsg);
        return this;
    }
}
