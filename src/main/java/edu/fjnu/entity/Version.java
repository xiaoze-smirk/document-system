package edu.fjnu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Version {

    //序号
    private Integer verId;

    //文档号
    private String docNum;

    //所属项目
    private String forItem;

    //版本号
    private String verNum;

    //修改时间
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date verAlertTime;

    //修改人
    private String verAlertPeople;

    //修改摘要
    private String verContent;

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

    public Integer getVerId() {
        return verId;
    }

    public void setVerId(Integer verId) {
        this.verId = verId;
    }

    public String getDocNum() {
        return docNum;
    }

    public void setDocNum(String docNum) {
        this.docNum = docNum == null ? null : docNum.trim();
    }

    public String getVerNum() {
        return verNum;
    }

    public void setVerNum(String verNum) {
        this.verNum = verNum == null ? null : verNum.trim();
    }

    public Date getVerAlertTime() {
        return verAlertTime;
    }

    public void setVerAlertTime(Date verAlertTime) {
        this.verAlertTime = verAlertTime;
    }

    public String getVerAlertPeople() {
        return verAlertPeople;
    }

    public void setVerAlertPeople(String verAlertPeople) {
        this.verAlertPeople = verAlertPeople == null ? null : verAlertPeople.trim();
    }

    public String getVerContent() {
        return verContent;
    }

    public void setVerContent(String verContent) {
        this.verContent = verContent == null ? null : verContent.trim();
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

    public String getForItem() {
        return forItem;
    }

    public void setForItem(String forItem) {
        this.forItem = forItem;
    }
}