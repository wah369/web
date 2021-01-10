package com.miaoshaproject.error;

public enum EmBusinessError implements CommonError {
    //通用错误类型00001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),
    //20000开头为为用户信息相关的错误定义
    USER_NOT_EXIST(20001,"用户不存在"),
    USER_LOGIN_FAIL(20001,"用户手机号或密码不正确"),
    USER_NOT_LOGIN(20003,"用户还未登录"),

    //30000开头为交易信息错误
    STOCK_NOT_ENOUGH(30000,"库存不足")
    ;

    private int errCode;
    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode=errCode;
        this.errMsg=errMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg=errMsg;
        return this;
    }


    //1.定义了一个CommonReturnType通用返回类（ status + data ）方式返回所有的json数据
    //2.定义了一个EmBusinessError类来管理所有的异常错误
    //3.定义了一个BaseController来处理在Controller层的所有异常
}
