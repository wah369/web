package org.example.config.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.JSONResponse;
import org.example.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-02
 * Time: 11:28
 */
public class LoginInterceptor implements HandlerInterceptor {

    private ObjectMapper objectMapper;

    public LoginInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /*@Autowired
    private ObjectMapper objectMapper;*/
    //没有扫描到

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {//获取登陆时设置的用户信息
            User user = (User) session.getAttribute("user");
            if (user != null) {  //登陆了，允许访问
                return true;
            }
        }
        //登录失败，不允许访问的业务：区分前后端
        //TODO:前端跳转登录页面，后端返回json
//        new ObjectMapper().writeValueAsString(Object); //序列化对象为json字符串
        String servletPath = request.getServletPath(); //获取到请求服务路径
        if (servletPath.startsWith("/api/")) { //后端逻辑,返回json
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            JSONResponse json = new JSONResponse();
            json.setCode("USER000");
            json.setMessage("用户没有登陆不允许访问");
            //返回给前端，序列化为json字符串
            String s = objectMapper.writeValueAsString(json);
            response.setStatus(HttpStatus.UNAUTHORIZED.value()); //返回401
            PrintWriter pw = response.getWriter();
            pw.println(s);
            pw.flush();

        }else { //前端逻辑 ：跳转到登录页面 /views/index.html
            //使用转发的方式       用绝对路径
            //相对路径的写法，一定是请求路径作为相对位置的参照点
            //使用绝对路径来重定向,不建议使用相对路径和转发
            String schema = request.getScheme(); //http
            String host = request.getServerName(); //ip
            int port = request.getServerPort();  //port
            String contextPath = request.getContextPath(); // application Context path 应用上下文路经
            String basePath = schema+"://"+host+":"+port+contextPath;
            //重定向到登陆页面
            response.sendRedirect(basePath+"/views/index.html");


        }
        return false;
    }
}
