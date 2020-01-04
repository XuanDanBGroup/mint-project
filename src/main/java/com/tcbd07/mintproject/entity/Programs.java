package com.tcbd07.mintproject.entity;

/**
 * 项目
 */
public class Programs {
    private String programId,programTitle,programContent,programComment;
    private Integer programStatus;

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
}
