package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-26
 * Time: 10:51
 */
@Controller
public class Test2Controller {
    @RequestMapping("/test2")
    public String test(){
        return "redirect:/home.html";
    }
}