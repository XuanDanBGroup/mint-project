package com.tcbd07.mintproject.service.impl;

import com.tcbd07.mintproject.dao.ProgramsVoDao;

import com.tcbd07.mintproject.entity.ProgramsVo;
import com.tcbd07.mintproject.entity.User;
import com.tcbd07.mintproject.service.ProgramsVoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProgramsVoServiceImpl implements ProgramsVoService {
    @Resource
    private ProgramsVoDao programsVoDao;
    @Override
    public List<ProgramsVo> queryAllProgramsVo() {
        return programsVoDao.queryAllProgramsVo();
    }
    @Override
    public ProgramsVo getProgramsVo(String programId) {
        return programsVoDao.getProgramsVo(programId);
    }

    @Override
    public List<User> getAllFirms(String firmId) {
        return programsVoDao.getAllFirms(firmId);
    }
}
