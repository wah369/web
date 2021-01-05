package org.example.controller;

import org.example.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/arg")
public class TestArgumentController {

    //
    private static final Logger log = LoggerFactory.getLogger(TestArgumentController.class);

    @GetMapping("/holiday/{day}")
    public Object holiday(@PathVariable String day){
        log.debug("获取到请求路径参数："+day);
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/login")
    public Object login(@RequestParam String username, @RequestParam String password){
        log.debug("获取到请求路径参数：username={}, password={}", username, password);
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/login2")//完成代码及测试，Integer改为int，重新测试
//    public Object login2(@RequestParam(required = false) Integer i){
    public Object login2(@RequestParam(required = false) int i){
        log.debug("获取到请求路径参数：i="+i);
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/register")
    public Object register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam MultipartFile file) throws IOException {
        log.debug("获取到请求路径参数：username={}, password={}", username, password);
        log.debug("头像信息，名称={}, 内容={}", file.getOriginalFilename(),
                new String(file.getBytes()));
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/login/pojo1")
    public Object loginPojo1(String username, String password){
        log.debug("获取到请求路径参数：username={}, password={}", username, password);
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    //springmvc自动实例化user对象，根据请求key获取值，注入到user对象的属性中
    @PostMapping("/login/pojo2")
    public Object loginPojo2(User user){
        log.debug("获取到请求路径参数：username={}, password={}",
                user.getUsername(), user.getPassword());
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/register/pojo")
    public Object register(User user,
                           MultipartFile file) throws IOException {
        log.debug("获取到请求路径参数：username={}, password={}",
                user.getUsername(), user.getPassword());
        log.debug("头像信息，名称={}, 内容={}", file.getOriginalFilename(),
                new String(file.getBytes()));
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/json")
    public Object json(@RequestBody User user){
        log.debug("用户信息为：{}", user);
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }

    @PostMapping("/file")
    public Object file(@RequestPart MultipartFile file) throws IOException {
        log.debug("头像信息，名称={}, 内容={}", file.getOriginalFilename(),
                new String(file.getBytes()));
        Map<String, Object> map = new HashMap<>();
        map.put("ok", true);
        return map;
    }
}
