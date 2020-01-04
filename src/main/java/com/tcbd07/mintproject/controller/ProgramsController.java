package com.tcbd07.mintproject.controller;


import com.google.common.graph.Network;
import com.tcbd07.mintproject.entity.ProgramsVo;
import com.tcbd07.mintproject.entity.User;
import com.tcbd07.mintproject.service.ProgramsVoService;
import com.tcbd07.mintproject.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProgramsController {
    @Autowired
    private ProgramsVoService programsVoService;

    @GetMapping("/queryAllProgramsVo")
    public ResultMessage queryAllProgramsVo(){
        List<ProgramsVo> programsVoList = programsVoService.queryAllProgramsVo();
        return ResultMessage.success(programsVoList);
    }

    @GetMapping("/getProgramsVo")
    public ResultMessage getProgramsVo(String programId){
        ProgramsVo programsVo = programsVoService.getProgramsVo(programId);
        return ResultMessage.success(programsVo);
    }

    @GetMapping("/getAllFirms")
    public ResultMessage getAllFirms(String firmId){
        List<User> userList = programsVoService.getAllFirms(firmId);
        return ResultMessage.success(userList);
    }








}
