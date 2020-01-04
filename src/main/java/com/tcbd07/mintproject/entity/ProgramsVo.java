package com.tcbd07.mintproject.entity;

import java.util.List;

/**
 * 项目及商家展示
 */
public class ProgramsVo {
    private String programId,programTitle,programContent,programComment;
    private Integer programStatus;
    private List<User> userList;

    public String getProgramId() {
        return programId;
    }

    public void setProgramId(String programId) {
        this.programId = programId;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getProgramContent() {
        return programContent;
    }

    public void setProgramContent(String programContent) {
        this.programContent = programContent;
    }

    public String getProgramComment() {
        return programComment;
    }

    public void setProgramComment(String programComment) {
        this.programComment = programComment;
    }

    public Integer getProgramStatus() {
        return programStatus;
    }

    public void setProgramStatus(Integer programStatus) {
        this.programStatus = programStatus;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "ProgramsVo{" +
                "programId='" + programId + '\'' +
                ", programTitle='" + programTitle + '\'' +
                ", programContent='" + programContent + '\'' +
                ", programComment='" + programComment + '\'' +
                ", programStatus=" + programStatus +
                ", userList=" + userList +
                '}';
    }
}
