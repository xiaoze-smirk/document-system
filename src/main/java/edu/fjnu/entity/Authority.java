package edu.fjnu.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Authority implements Serializable , GrantedAuthority {

    //权限编号
    private String authorityId;

    //权限英文名
    private String authorityEnglishName;

    //权限中文名
    private String authorityChineseName;

    public String getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(String authorityId) {
        this.authorityId = authorityId == null ? null : authorityId.trim();
    }

    public String getAuthorityEnglishName() {
        return authorityEnglishName;
    }

    public void setAuthorityEnglishName(String authorityEnglishName) {
        this.authorityEnglishName = authorityEnglishName == null ? null : authorityEnglishName.trim();
    }

    public String getAuthorityChineseName() {
        return authorityChineseName;
    }

    public void setAuthorityChineseName(String authorityChineseName) {
        this.authorityChineseName = authorityChineseName == null ? null : authorityChineseName.trim();
    }

    @Override
    public String getAuthority() {
        return this.authorityEnglishName;
    }
}