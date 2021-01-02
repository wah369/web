package org.example.controller;

import org.example.model.User;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-18
 * Time: 10:59
 */
@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    @Qualifier("user1")
    public User user1;

    /*public String login(String username, String password) {

    }*/
    @Bean
    public User user1() {
        User user = new User();
        user.setUsername("鸣人");
        user.setPassword("123");
        return user;
    }

    @Bean
    public User user2() {
        User user = new User();
        user.setUsername("佐助");
        user.setPassword("456");
        return user;
    }



}
