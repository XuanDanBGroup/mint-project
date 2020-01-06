package com.tcbd07.mintproject.controller;


import com.tcbd07.mintproject.entity.ProgramsVo;
import com.tcbd07.mintproject.entity.User;
import com.tcbd07.mintproject.service.ProgramsVoService;
import com.tcbd07.mintproject.util.ResultMessage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "项目及商家展示")
public class ProgramsController {
    @Autowired
    private ProgramsVoService programsVoService;

    @ApiOperation(value = "查询所有项目（含商家）",notes = "无参")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 403,message = "网络异常")
    })
    @GetMapping("/queryAllProgramsVo")
    public ResultMessage queryAllProgramsVo(){
        List<ProgramsVo> programsVoList = programsVoService.queryAllProgramsVo();
        return ResultMessage.success(programsVoList);
    }
    @ApiOperation(value = "根据项目id查询单个项目（含商家）",notes = "一个参数，请输入programId！")
    @ApiImplicitParam(name = "programId",value = "programId",dataType = "String",example = "项目id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 403,message = "网络异常")
    })
    @GetMapping("/getProgramsVo")
    public ResultMessage getProgramsVo(String programId){
        ProgramsVo programsVo = programsVoService.getProgramsVo(programId);
        return ResultMessage.success(programsVo);
    }

    @ApiOperation(value = "根据商家id查询用户",notes = "一个参数，请输入firmId")
    @ApiImplicitParam(name = "firmId",value = "firmId",dataType = "String",example = "商家id")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "查询成功"),
            @ApiResponse(code = 403,message = "网络异常")
    })
    @GetMapping("/getAllFirms")
    public ResultMessage getAllFirms(String firmId){
        List<User> userList = programsVoService.getAllFirms(firmId);
        return ResultMessage.success(userList);
    }








}
