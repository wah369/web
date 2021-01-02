package org.example.controller;

import org.example.model.JSONResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/rest")
public class TestRestController {

    @GetMapping("/1")
    public Object test1(){
        JSONResponse resp = new JSONResponse();
        resp.setCode("LOG001");
        resp.setMessage("用户不存在");
        resp.setData(new Date());
        return resp;
    }
}
