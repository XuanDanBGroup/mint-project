package com.tcbd07.mintproject.service;

import com.tcbd07.mintproject.entity.User;
import org.apache.ibatis.annotations.Param;

public interface LoginService {
    /**
     *  登录
     * @param phone 手机号
     *
     * @return 用户
     */
    User login( String phone);
}
