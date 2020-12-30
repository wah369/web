package org.example.controller;

import org.example.model.JSONResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: 联想
 * Date: 2020-12-26
 * Time: 10:44
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/1")
    public String test1(){
        return "forward:/home.html";
        //转发：只有一次请求，url不变，服务端把资源作为响应体直接返回
    }
    @RequestMapping("/test2")
    public String test2(){
        return "redirect:/home.html";
        //重定向：2次请求，url会变，相应3xx+Location，浏览器自动跳转
    }

    @RequestMapping("/3")
    @ResponseBody
    public String test3(){
        return "好了，已经知道了";
    }
    @RequestMapping("/4")
    @ResponseBody
    public Object test4(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "张三");
        map.put(2, "李四");
        map.put(3, "王五");
        return map;
    }
    @RequestMapping("/5")
    public ResponseEntity test5(){
        Map<Integer, String> map = new HashMap<>();
        map.put(4, "张三");
        map.put(5, "李四");
        map.put(6, "王五");
        return ResponseEntity.status(200).body(map);
    }
    @RequestMapping("/6")
    @ResponseBody
    public Object test6(){
        Map<String, Object> map = new HashMap<>();
        map.put("success",false);
        map.put("code","LOG001");
        map.put("message","用户不存在");
        map.put("data",null);
        return map;
    }

    @RequestMapping("/6_1")
    @ResponseBody
    public Object test6_1(){
        JSONResponse resp = new JSONResponse();


        return resp;
    }


}


