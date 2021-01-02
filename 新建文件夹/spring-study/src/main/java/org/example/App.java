package org.example;
import org.example.controller.LoginController;
import org.example.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        //根据Spring配置文件路径创建容器：应用上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        //获取注册的Bean对象:根据名称获取，或根据类型
        LoginController loginController = (LoginController) context.getBean("loginController");
        LoginController loginController2 = (LoginController) context.getBean(LoginController.class);

        //获取Service,DAO的对象

        //打印
        System.out.println(loginController == loginController2);

        User user1 = (User) context.getBean("user1");
        User user2 = (User) context.getBean("user2");
        System.out.println(user1);
        System.out.println(user2.getUsername());

        User user3 = (User) context.getBean("userFactory");
        System.out.println(user3);

        //LoginService loginService = (LoginService) context.getBean("prototype");
        //System.out.println();

        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }
}