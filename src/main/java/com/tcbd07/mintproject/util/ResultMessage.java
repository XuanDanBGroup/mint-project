package com.tcbd07.mintproject.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description
 * @Author 赵泽明
 * @Version 1.0
 */
@ApiModel(description = "向页面返回的信息")
public class ResultMessage {

    @ApiModelProperty(value = "状态码")
    private String code;

    @ApiModelProperty(value = "携带的信息")
    private String message;

    @ApiModelProperty(value = "响应的结果数据")
    private  Object result;

    public ResultMessage(){

    }

    /**
     *
     * @param resultInterface 定义好的返回信息
     */
    public ResultMessage(ResultInterface resultInterface) {
        this.code = resultInterface.getResultCode();
        this.message = resultInterface.getResultMsg();
    }

    /**
     * 自定义返回信息
     * @param code 状态码
     * @param message 状态信息
     */
    public ResultMessage(String code,String message){
        this.code=code;
        this.message=message;
    }

    /**
     * 自定义成功信息 不带数据
     * @param code 状态码
     * @param message 状态信息
     * @return
     */
    public static ResultMessage success(String code,String message){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setCode(code);
        resultMessage.setMessage(message);
        resultMessage.setResult(null);
        return resultMessage;
    }

    /**
     * 成功不携带数据
     * @return
     */
    public static ResultMessage success(){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setCode(CommonEnum.SUCCESS.getResultCode());
        resultMessage.setMessage(CommonEnum.SUCCESS.getResultMsg());
        return resultMessage;
    }

    /**
     * 成功 携带数据
     * @param result 携带的数据
     * @return
     */
    public static ResultMessage success(Object result){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setCode(CommonEnum.SUCCESS.getResultCode());
        resultMessage.setMessage(CommonEnum.SUCCESS.getResultMsg());
        resultMessage.setResult(result);
        return resultMessage;
    }

    /**
     * 错误返回错误信息
     * @param resultInterface 已经声明好的错误信息
     * @return
     */
    public static ResultMessage error(ResultInterface resultInterface){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setCode(resultInterface.getResultCode());
        resultMessage.setMessage(resultInterface.getResultMsg());
        resultMessage.setResult(null);
        return resultMessage;
    }

    /**
     * 返回错误信息
     * @param code  状态码
     * @param message 错误信息
     * @return
     */
    public static ResultMessage erro(String code,String message){
        ResultMessage resultMessage=new ResultMessage();
        resultMessage.setCode(code);
        resultMessage.setMessage(message);
        resultMessage.setResult(null);
        return resultMessage;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
