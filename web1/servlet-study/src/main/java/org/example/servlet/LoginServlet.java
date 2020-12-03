package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-11-27
 * Time: 16:59
 */
//定义当前类为Servlet(服务端Java代码提供的http服务)
//服务路径必须以/开头，否则tomcat启动就会报错
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("test/html");//响应头Content-Type,告诉对端响应体的解析

        //getParameter可以获取url中的queryString请求数据，以及请就提表单数据类型的请求数据
        String uesrname = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.printf("username=%s,password=%s%n",uesrname,password);

        PrintWriter pw = resp.getWriter();
        //伪代码：用户登录的jdbc效验
        //作业：完成用户登录Servlet+JDBC的代码，返回数据要求是JSON格式的数据
//        if(LoginDAD.query(uesrname,password)) {
//            pw.println("登录成功");
//        }else {
//            pw.println("登录失败");
//        }
//        ObjectMapper
        pw.println("登录成功");
        pw.flush();
        pw.close();
    }
}
