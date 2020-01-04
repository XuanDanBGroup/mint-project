package com.tcbd07.mintproject.service;



import com.tcbd07.mintproject.entity.ProgramsVo;
import com.tcbd07.mintproject.entity.User;

import java.util.List;

public interface ProgramsVoService {
    List<ProgramsVo> queryAllProgramsVo();//展示所有项目含商家
    ProgramsVo getProgramsVo(String programId);//展示单个项目
    List<User> getAllFirms(String firmId);//通过商家查询客户
}
