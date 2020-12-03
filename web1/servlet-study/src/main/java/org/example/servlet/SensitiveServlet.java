package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-02
 * Time: 16:44
 */
@WebServlet
public class SensitiveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("test/html");


        PrintWriter pw = resp.getWriter();
        //敏感资源需要使用false，如果没有返回Null
        HttpSession session = req.getSession(false);
        if (session == null) { //用户没有登陆
            resp.setStatus(401);
            pw.println("没有登录，不允许访问");
        }else {
//            //伪代码
//            User user = (User) session.getAttribute("user");
//            if(user.允许访问的资源不包含当前访问的服务路径) {
//                resp.setStatus(403); // 没有权限
//                pw.println("没有权限禁止访问");
//            }else {
//                pw.println("访问成功");
//            }
            pw.println("访问成功");


        }




    }
}
