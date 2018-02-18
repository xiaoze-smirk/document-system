package edu.fjnu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class Document implements Serializable {

    //序号（文档号）
    private String docNum;

    //标题
    private String docTitle;

    //撰写人
    private String docAuthor;

    //版本号
    private String verNum;

    //状态
    private String docState;

    //发布日期
    @JSONField(format = "yyyy-MM-dd")
    private Date docReleaseDate;

    //审核人
    private String docCheckPerson;

    //摘要
    private String docContent;


    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum == null ? null : docNum.trim();
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle == null ? null : docTitle.trim();
    }

    public String getDocAuthor() {
        return docAuthor;
    }

    public void setDocAuthor(String docAuthor) {
        this.docAuthor = docAuthor == null ? null : docAuthor.trim();
    }

    public String getVerNum() {
        return verNum;
    }

    public void setVerNum(String verNum) {
        this.verNum = verNum == null ? null : verNum.trim();
    }

    public String getDocState() {
        return docState;
    }

    public void setDocState(String docState) {
        this.docState  =docState == null ? null : docState.trim();
    }

    public Date getDocReleaseDate() {
        return docReleaseDate;
    }

    public void setDocReleaseDate(Date docReleaseDate) {
        this.docReleaseDate = docReleaseDate;
    }

    public String getDocCheckPerson() {
        return docCheckPerson;
    }

    public void setDocCheckPerson(String docCheckPerson) {
        this.docCheckPerson = docCheckPerson == null ? null : docCheckPerson.trim();
    }

    public String getDocContent() {
        return docContent;
    }

    public void setDocContent(String docContent) {
        this.docContent = docContent == null ? null : docContent.trim();
    }

}