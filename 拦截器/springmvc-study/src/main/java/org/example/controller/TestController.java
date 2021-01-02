package org.example.controller;

import org.example.model.JSONResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/1")
    public String test1(){
        //转发：只有一次请求，url不变，服务端把资源做为响应体直接返回
        return "forward:/home.html";
    }

    @RequestMapping("/2")
    public String test2(){
        //重定向：2次请求，url会变，响应3xx+Location，浏览器自动跳转
        return "redirect:/home.html";
    }

    @RequestMapping("/3")
    @ResponseBody
    public String test3(){
        //字符串内容会作为响应体返回
        return "redirect:/home.html";
    }

    @RequestMapping("/4")
    @ResponseBody
    public Object test4(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "就");
        map.put(2, "OK");
        return map;
    }

    @RequestMapping(value = "/4_1", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object test4_1(){
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "就");
        map.put(2, "OK");
        return map;
    }

    @RequestMapping("/4_2")
    @ResponseBody
    public Object test4_2(){
        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("code", "LOG001");
        map.put("message", "用户不存在");
        map.put("data", null);
        return map;
    }

    @RequestMapping("/4_3")
    @ResponseBody
    public Object test4_3(){
        JSONResponse resp = new JSONResponse();
        resp.setCode("LOG001");
        resp.setMessage("用户不存在");
        resp.setData(new Date());
        return resp;
    }

    @RequestMapping("/5")
    @ResponseBody
    public ResponseEntity test5(){
        JSONResponse resp = new JSONResponse();
        resp.setCode("LOG000");
        resp.setMessage("未授权的请求，不允许访问");
        resp.setData(new Date());
        return ResponseEntity.status(401)
                .body(resp);
    }

    @RequestMapping("/6_1")
    @ResponseBody
    public String test6_1(){
        return null;
    }

    @RequestMapping("/6_2")
    @ResponseBody
    public Object test6_2(){
        return null;
    }

    /**
     * 1. success=true, data有业务数据
     * 2. success=true, data=null
     * 3. success=false, code和message有信息
     */
}
