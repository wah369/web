package org.example.controller;

import org.example.model.JSONResponse;
import org.example.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-02
 * Time: 9:28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    public Object login(User user, HttpServletRequest req){
        JSONResponse json = new JSONResponse();
        if("abc".equals(user.getUsername())) {
            //通过请求头Cookie:JSESSIONID=xxx,在服务端map中通过xxx查找session
            // 找到session就返回，如果没有，创建一个
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            json.setSuccess(true);
        }else {
            json.setCode("USRLOG");
            json.setMessage("用户密码错误");
        }
        return json;
    }

}
