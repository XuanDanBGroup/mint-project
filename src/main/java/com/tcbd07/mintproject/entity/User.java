package com.tcbd07.mintproject.entity;

/**
 * 商家及用户
 */
public class User {
    private String userId,userWebName,userPhone,userPassword,userWechart;
    private Integer userRole,userStatus;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserWebName() {
        return userWebName;
    }

    public void setUserWebName(String userWebName) {
        this.userWebName = userWebName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserWechart() {
        return userWechart;
    }

    public void setUserWechart(String userWechart) {
        this.userWechart = userWechart;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userWebName='" + userWebName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userWechart='" + userWechart + '\'' +
                ", userRole=" + userRole +
                ", userStatus=" + userStatus +
                '}';
    }
}
