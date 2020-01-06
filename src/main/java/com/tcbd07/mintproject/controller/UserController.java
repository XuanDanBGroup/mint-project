package com.tcbd07.mintproject.controller;


import com.tcbd07.mintproject.entity.User;
import com.tcbd07.mintproject.service.LoginService;
import com.tcbd07.mintproject.util.ResultMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@RestController
@Api(tags = "用户及商家")
public class UserController {
    @Resource
    LoginService loginService;
    @ApiOperation(value = "登录",notes = "账号密码")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "登录成功"),
            @ApiResponse(code = 401,message = "账号不存在"),
            @ApiResponse(code = 402,message = "密码错误")
    })
    @PostMapping("/login")
    public ResultMessage login( HttpServletRequest request,String phone , String pwd){
        User user=loginService.login(phone);
        if (user!=null){

            if(user.getUserPassword().equals(pwd)){
                HttpSession session=  request.getSession();
                session.setAttribute("user",user);
                return ResultMessage.success("200","登录成功");
            }
            return  ResultMessage.erro("402","密码错误");
        }
        return  ResultMessage.erro("401","账号不存在");

    }
}
