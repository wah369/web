$(function () {//html页面加载完执行方法
    $("#login_form").submit(function () {//绑定表单的提交事件

        // alert("submit")//验证
        $.ajax({
            //url: "../data/login2.json",
            url: "../data/login.json",
            type: "get",//请求方法
            // dataType: "x-www-form-urlencoded",//请求数据类型：Content-Type
            data: $("#login_form").serialize(),//请求数据：使用表单的数据
            contentType: "json",//响应数据类型：Content-Type
            success: function (resp) {
                //返回数据数据success=true，跳转到main,html
                //返回数据数据success=false，提示code和message
                if (resp.success){
                    //把地址栏url改为指定的值
                    window.location.href = "main.html";
                }else {
                    alert("错误码："+resp.code+"\n错误信息"+resp.message)
                }

                //alert(JSON.stringify(resp))//提示框：json对象转为字符串
            },
            error:function (req,textStatus,err) { //响应状态码为4xx，5xx
                alert(req.status)
            }
        })
        return false;//返回值决定是否使用表单默认提交
    })
})