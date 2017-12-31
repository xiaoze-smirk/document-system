package edu.fjnu.entity;

import org.springframework.stereotype.Component;

@Component
public class Department {

    //部门编号
    private String deptId;

    //部门名称
    private String deptName;

    //部门职能
    private String deptContent;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptContent() {
        return deptContent;
    }

    public void setDeptContent(String deptContent) {
        this.deptContent = deptContent == null ? null : deptContent.trim();
    }
}