package com.miaoshaproject.controller;

import com.miaoshaproject.controller.viewobject.UserVO;
import com.miaoshaproject.dao.UserDOMapper;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.UserModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(origins = "*",allowCredentials = "true",allowedHeaders = "*") //解决跨域问题,默认是没有办法实现Session共享
//allowCredentials = "true",allowedHeaders = "*"
public class UserController extends BaseController{


    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;  //不是单例模式，就是当前用户的http请求


    //用户登陆借口
    @RequestMapping(value = "/login",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED}) //定义对应的请求方法
    @ResponseBody
    public CommonReturnType login(@RequestParam(name = "telphone") String telphone,
                                  @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //入参校验
        if(StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //用户登录服务，用来校验用户登录是否合法
        UserModel userModel = userService.validateLogin(telphone,EncodeByMd5(password));

        //登录成功的话，将凭证加入到用户的登录成功的session中
        this.httpServletRequest.getSession().setAttribute("IS_LOGIN",true);
        this.httpServletRequest.getSession().setAttribute("LOGIN_USER",userModel);

        return CommonReturnType.create(null);
    }

    //用户获取opt短信的接口
    @RequestMapping(value = "/getopt",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED}) //定义对应的请求方法
    @ResponseBody
    public CommonReturnType getOpt(@RequestParam(name = "telphone") String telphone){

        //按照一定的规则生成验证码
        Random random = new Random();
        int randomInt = random.nextInt(99999);
        randomInt+=10000;
        String optCode=String.valueOf(randomInt);

        //将OPT验证码同对应用户的手机号连接  这里用httpsession将optcode与手机号绑定

        httpServletRequest.getSession().setAttribute(telphone,optCode);

        //将opt验证码通过短信发送给用户,省略
        //......
        System.out.println(telphone+"对应的验证码:"+optCode);

        return CommonReturnType.create(null);
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {

        //调用service服务获取用户的对象返回给前端
        UserModel userModel = userService.getUserById(id);

        //若获取的对应用户信息不存在
        if(userModel==null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST); //抛异常
        }
//        //将核心领域模型转化为UI使用的Object
//        return convertFromModek(userModel);
        UserVO userVO = convertFromModek(userModel);
        //返回通用对象
        return CommonReturnType.create(userVO);
    }

    private UserVO convertFromModek(UserModel userModel){

        if(userModel==null){
            return null;
        }

        UserVO userVO=new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }


    //用户注册接口
    @RequestMapping(value = "/register",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED}) //定义对应的请求方法
    @ResponseBody
    public CommonReturnType register(@RequestParam(name = "telphone") String telphone,
                                     @RequestParam(name = "optCode") String optCode,
                                     @RequestParam(name = "name") String name,
                                     @RequestParam(name = "gender") Integer gender,
                                     @RequestParam(name = "age") Integer age,
                                     @RequestParam(name = "password") String password) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证手机号和对应的optCode相符合
        String  inSessionOptCode = (String) this.httpServletRequest.getSession().getAttribute(telphone);//telphone

        //将两者 进行比对
        if(!com.alibaba.druid.util.StringUtils.equals(optCode,inSessionOptCode)){
            //不相等的时候
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"短信验证码不符合要求");
        }

        //注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setGender(new Byte(String.valueOf(gender.intValue())));
        userModel.setAge(age);
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byPhone");
        userModel.setEncrptPassword(this.EncodeByMd5(password));

        userService.regist(userModel);

        return CommonReturnType.create(null);
    }

    public String EncodeByMd5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        //确定一个计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        //加密字符串
        String encode = base64Encoder.encode(md5.digest(str.getBytes("utf-8")));
        return encode;
    }

}
