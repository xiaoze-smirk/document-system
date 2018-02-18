package edu.fjnu.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class User implements Serializable {

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

    //用户qq账号
    private String userQqAccount;

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

    public String getUserQqAccount() {
        return userQqAccount;
    }

    public void setUserQqAccount(String userQqAccount) {
        this.userQqAccount = userQqAccount == null ? null : userQqAccount.trim();
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
}