package com.tcbd07.mintproject.util;


import com.tcbd07.mintproject.entity.Dto;

public class DtoUtils {
    public static  String success="true";
    private static  String fail="false";
    private static  String errorCode="123";
    public static Dto retrnSuccess(){
        Dto dto=new Dto();
        dto.setSuccess(success);
        return dto;
    }
    public static  Dto returnsuccess(String mes){
        Dto dto=new Dto();
        dto.setMsg(mes);
        dto.setSuccess(success);
        return dto;
    }
    public static  Dto returnsuccess(String msg, Object date){
        Dto dto=new Dto();
        dto.setMsg(msg);
        dto.setSuccess(success);
        dto.setDate(date);
        return dto;
    }
    public static Dto returnfalse(String msg){
        Dto dto=new Dto();
        dto.setSuccess(fail);
        dto.setMsg(msg);
        dto.setErrorCode(errorCode);
        return dto;
    }
}
