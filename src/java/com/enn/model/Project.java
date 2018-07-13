package com.enn.model;

public class Project {

    private int projectId;

    private String projectName;

    private String projectFrom;

    private String projectEnd;

    private String projectComment;

    private int shareTimesLimit;

    private int bonusProject;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectFrom() {
        return projectFrom;
    }

    public void setProjectFrom(String projectFrom) {
        this.projectFrom = projectFrom;
    }

    public String getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(String projectEnd) {
        this.projectEnd = projectEnd;
    }

    public String getProjectComment() {
        return projectComment;
    }

    public void setProjectComment(String projectComment) {
        this.projectComment = projectComment;
    }

    public int getShareTimesLimit() {
        return shareTimesLimit;
    }

    public void setShareTimesLimit(int shareTimesLimit) {
        this.shareTimesLimit = shareTimesLimit;
    }

    public int getBonusProject() {
        return bonusProject;
    }

    public void setBonusProject(int bonusProject) {
        this.bonusProject = bonusProject;
    }
}
