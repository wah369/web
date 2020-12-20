$(function () {//html页面加载完执行方法
    $("#login_form").submit(function () {//绑定表单的提交事件

        // alert("submit")//验证
        $.ajax({
            url: "data/login.json",
            type: "get",//请求方法
            // dataType: "x-www-form-urlencoded",//请求数据类型：Content-Type
            data: $("#login_form").serialize(),//请求数据：使用表单的数据
            contentType: "json",//响应数据类型：Content-Type
            success: function (resp) {
                alert(JSON.stringify(resp))//提示框：json对象转为字符串
            }
        })
        return false;//返回值决定是否使用表单默认提交
    })
})