package com.tcbd07.mintproject.service.impl;

import com.tcbd07.mintproject.dao.LoginDao;
import com.tcbd07.mintproject.entity.User;
import com.tcbd07.mintproject.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    LoginDao dao;
    @Override
    public User login(String phone) {

        return dao.login(phone);
    }
}
