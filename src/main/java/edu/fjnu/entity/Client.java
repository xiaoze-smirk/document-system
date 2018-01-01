package edu.fjnu.entity;

import org.springframework.stereotype.Component;

@Component
public class Client {

    //客户号
    private String clientId;

    //公司名称
    private String clientCompany;

    //联系人
    private String clientPerson;

    //联系电话
    private String clientPhone;

    //邮件
    private String clientEmail;

    //地址
    private String clientAddr;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    public String getClientCompany() {
        return clientCompany;
    }

    public void setClientCompany(String clientCompany) {
        this.clientCompany = clientCompany == null ? null : clientCompany.trim();
    }

    public String getClientPerson() {
        return clientPerson;
    }

    public void setClientPerson(String clientPerson) {
        this.clientPerson = clientPerson == null ? null : clientPerson.trim();
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone == null ? null : clientPhone.trim();
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail == null ? null : clientEmail.trim();
    }

    public String getClientAddr() {
        return clientAddr;
    }

    public void setClientAddr(String clientAddr) {
        this.clientAddr = clientAddr == null ? null : clientAddr.trim();
    }
}