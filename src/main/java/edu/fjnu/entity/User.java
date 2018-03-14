package edu.fjnu.entity;

import edu.fjnu.utils.StringUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class User implements UserDetails, Serializable {

    //用户账号
    private String userAccount;

    //用户姓名
    private String userName;

    //用户密码
    private String userPassword;

    //用户性别
    private String userSex;

    //用户手机号
    private String userPhone;

    //用户邮箱
    private String userEmail;

    //用户权限编号
    private String userAuthorityId;
    //辅助权限
    private Authority authority;

    //用户头像路径
    private String userAvatar;

    //用户职务
    private String userDuty;

    //用户所属部门
    private String userDepartment;
    //显示部门辅助字符数组
    private String[] userDept;
    //显示部门辅助字符串
    private String userDeptName;

    //用户个人照
    private byte[] userFaceAvatar;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getUserAuthorityId() {
        return userAuthorityId;
    }

    public void setUserAuthorityId(String userAuthorityId) {
        this.userAuthorityId = userAuthorityId == null ? null : userAuthorityId.trim();
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar == null ? null : userAvatar.trim();
    }

    public String getUserDuty() {
        return userDuty;
    }

    public void setUserDuty(String userDuty) {
        this.userDuty = userDuty == null ? null : userDuty.trim();
    }

    public String getUserDepartment() {
        return userDepartment;
    }

    public void setUserDepartment(String userDepartment) {
        this.userDepartment = userDepartment == null ? null : userDepartment.trim();
        StringUtil stringUtil = new StringUtil();
        this.userDept=stringUtil.strList(userDepartment);
    }

    public String[] getUserDept() {
        return userDept;
    }

    public void setUserDept(String[] userDept) {
        this.userDept = userDept;
        StringUtil stringUtil = new StringUtil();
        this.userDepartment=stringUtil.str(userDept);
    }

    public String getUserDeptName() {
        return userDeptName;
    }

    public void setUserDeptName(String userDeptName) {
        this.userDeptName = userDeptName;
    }

    public byte[] getUserFaceAvatar() {
        return userFaceAvatar;
    }

    public void setUserFaceAvatar(byte[] userFaceAvatar) {
        this.userFaceAvatar = userFaceAvatar;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //  需将 List<Authority> 转成 List<SimpleGrantedAuthority>，否则前端拿不到角色列表名称
        List<SimpleGrantedAuthority> simpleAuthorities = new ArrayList<>();

        simpleAuthorities.add(new SimpleGrantedAuthority(this.authority.getAuthority()));

        return simpleAuthorities;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userAccount;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}