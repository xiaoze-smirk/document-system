package edu.fjnu.entity;

import edu.fjnu.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Document {

    //序号
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
    private Date docReleaseDate;
    //发布日期辅助类
    private String docReleaseDateStr;

    //审核人
    private String docCheckPerson;

    //摘要
    private String docContent;

    //测试计划编号
    private String testJh;

    //测试用例编号
    private String testYl;

    //测试记录编号
    private String testJl;

    //缺陷报告编号
    private String testQx;

    //测试报告编号
    private String testBg;

    //测试计划地址
    private String testJhPath;

    //测试用例地址
    private String testYlPath;

    //测试记录地址
    private String testJlPath;

    //缺陷报告地址
    private String testQxPath;

    //测试报告地址
    private String testBgPath;

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
        Utils utils = new Utils();
        this.docReleaseDateStr=utils.dateToStr(this.docReleaseDate);
    }

    public String getDocReleaseDateStr() {
        return docReleaseDateStr;
    }

    public void setDocReleaseDateStr(String docReleaseDateStr) {
        this.docReleaseDateStr = docReleaseDateStr;
        Utils utils = new Utils();
        this.docReleaseDate=utils.strToDate(this.docReleaseDateStr);
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

    public String getTestJh() {
        return testJh;
    }

    public void setTestJh(String testJh) {
        this.testJh = testJh == null ? null : testJh.trim();
    }

    public String getTestYl() {
        return testYl;
    }

    public void setTestYl(String testYl) {
        this.testYl = testYl == null ? null : testYl.trim();
    }

    public String getTestJl() {
        return testJl;
    }

    public void setTestJl(String testJl) {
        this.testJl = testJl == null ? null : testJl.trim();
    }

    public String getTestQx() {
        return testQx;
    }

    public void setTestQx(String testQx) {
        this.testQx = testQx == null ? null : testQx.trim();
    }

    public String getTestBg() {
        return testBg;
    }

    public void setTestBg(String testBg) {
        this.testBg = testBg == null ? null : testBg.trim();
    }

    public String getTestJhPath() {
        return testJhPath;
    }

    public void setTestJhPath(String testJhPath) {
        this.testJhPath = testJhPath == null ? null : testJhPath.trim();
    }

    public String getTestYlPath() {
        return testYlPath;
    }

    public void setTestYlPath(String testYlPath) {
        this.testYlPath = testYlPath == null ? null : testYlPath.trim();
    }

    public String getTestJlPath() {
        return testJlPath;
    }

    public void setTestJlPath(String testJlPath) {
        this.testJlPath = testJlPath == null ? null : testJlPath.trim();
    }

    public String getTestQxPath() {
        return testQxPath;
    }

    public void setTestQxPath(String testQxPath) {
        this.testQxPath = testQxPath == null ? null : testQxPath.trim();
    }

    public String getTestBgPath() {
        return testBgPath;
    }

    public void setTestBgPath(String testBgPath) {
        this.testBgPath = testBgPath == null ? null : testBgPath.trim();
    }
}