package org.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.config.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2021-01-01
 * Time: 12:06
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {

    //注入
    @Autowired
    private ObjectMapper objectMapper;



    //配置Controller中请求映射方法路径匹配规则
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        //设置路径前缀的规则，以第二个参数的返回值作为请求映射方法是否添加前缀
        configurer.addPathPrefix("api",c->true);
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //前后端处理逻辑是否一样
        //前端：敏感资源在拦截器中处理为：没登陆跳转首页
        //后端：敏感资源在拦截器中处理为：返回json.401状态码
        registry.addInterceptor(new LoginInterceptor(objectMapper))
                //添加路径拦截规则，*代表路径下一级，**表示下级多级目录的任意字符匹配
                .addPathPatterns("/api/**")
                //排除登录路径
                .excludePathPatterns("/api/user/**")
                .addPathPatterns("/views/article.html")
                .addPathPatterns("/views/main.html");
    }
}
