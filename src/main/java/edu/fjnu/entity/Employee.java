package edu.fjnu.entity;

import edu.fjnu.utils.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class Employee {

    //工号（序号）
    private Integer empId;

    //姓名
    private String empName;

    //性别
    private String empSex;

    //职务
    private String empDuty;

    //部门
    private String empDepartment;
    //显示部门辅助字符数组
    private String[] empDept;
    //显示部门辅助字符串
    private String empDeptName;

    //电话
    private String empPhone;

    //Email
    private String empEmail;

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex == null ? null : empSex.trim();
    }

    public String getEmpDuty() {
        return empDuty;
    }

    public void setEmpDuty(String empDuty) {
        this.empDuty = empDuty == null ? null : empDuty.trim();
    }

    public String getEmpDepartment() {
        return empDepartment;
    }

    public void setEmpDepartment(String empDepartment) {
        this.empDepartment = empDepartment == null ? null : empDepartment.trim();
        StringUtil stringUtil = new StringUtil();
        this.empDept=stringUtil.strList(empDepartment);
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone == null ? null : empPhone.trim();
    }

    public String getEmpEmail() {
        return empEmail;
    }

    public void setEmpEmail(String empEmail) {
        this.empEmail = empEmail == null ? null : empEmail.trim();
    }


    public String[] getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String[] empDept) {
        this.empDept = empDept;

        StringUtil stringUtil = new StringUtil();
        this.empDepartment=stringUtil.str(empDept);

    }

    public String getEmpDeptName() {
        return empDeptName;
    }

    public void setEmpDeptName(String empDeptName) {
        this.empDeptName = empDeptName;
    }
}