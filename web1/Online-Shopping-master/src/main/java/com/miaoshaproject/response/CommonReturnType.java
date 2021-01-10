package com.miaoshaproject.response;

//统一返回
public class CommonReturnType {

    //表名请求的返回处理结果 success或者fail
    private String status;

    //若status=success，则返回前端json数据
    //若status=fail，则返回前端通用错误码
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result,"success");
    }

    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type=new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
