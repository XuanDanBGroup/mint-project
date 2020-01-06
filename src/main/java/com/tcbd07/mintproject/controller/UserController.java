package com.tcbd07.mintproject.controller;


import com.alibaba.fastjson.JSON;
import com.tcbd07.mintproject.entity.Dto;
import com.tcbd07.mintproject.entity.User;
import com.tcbd07.mintproject.service.LoginService;
import com.tcbd07.mintproject.util.*;
import cz.mallat.uasparser.UserAgentInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


@RestController
@Api(tags = "用户及商家")
public class UserController {
    @Resource
    LoginService loginService;
    @Resource
    RedisUtil redisUtil;
    @ApiOperation(value = "登录",notes = "账号密码")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "登录成功"),
            @ApiResponse(code = 401,message = "账号不存在"),
            @ApiResponse(code = 402,message = "密码错误")
    })

    @PostMapping( "/login")
    public Dto loginByPwd(User user, HttpServletRequest request)throws Exception{
        System.out.println(user);
        String ua = request.getHeader("token");
        UserAgentInfo userAgentInfo= UserAgentUtil.uaSparser.parse(ua);
        String type=userAgentInfo.getDeviceType();

        Object[] results=this.login(user,type);
        if (results==null){
            return DtoUtils.returnfalse("登录失败");
        }else{
            return DtoUtils.returnsuccess("登录成功",JSON.toJSONString(results));
        }
    }
    public Object[] login(User user,String type) throws Exception{
        //验证
        User userDb=loginService.login(user.getUserPhone(),user.getUserPassword());
        if (userDb==null){
            return null;
        }
        //生成token
        String token=this.createToken(userDb,type);
        //保存token到redis
        this.saveToken(token,userDb);
        return new Object[]{userDb,token};
    }

    private String createToken(User user, String type){
        StringBuilder builder=new StringBuilder();
        builder.append("token-");
        builder.append(type+"-");
        String info= MD5.getMD5(user.getUserId(),32);
        builder.append(info+"-");
        builder.append(new SimpleDateFormat(
                "yyyyMMddHHmmss").format(new Date()));
        builder.append(UUID.randomUUID().
                toString().substring(0,6));
        return builder.toString();
    }
    private void saveToken(String token,User user){

        String tokenKey="xs"+user.getUserId();
        String tokenValue=null;
        if ((tokenValue=(String) redisUtil.get(tokenKey))!=null){
            //说明用户已经登陆过，而且没过期
            redisUtil.delete(tokenKey);
        }
        //缓存用户
        redisUtil.set(tokenKey,20,token);
        //缓存详细信息
        redisUtil.set(token,20, JSON.toJSONString(user));

    }


}
