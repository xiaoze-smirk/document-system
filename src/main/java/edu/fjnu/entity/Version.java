package edu.fjnu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class Version implements Serializable {

    //版本序号（id）
    private Integer verId;

    //项目顺序号（项目id）
    private Integer itemId;
    //项目
    private Item item;

    //版本号
    private Double verNum;

    //修改时间
    @JSONField(format = "yyyy-MM-dd hh:mm:ss")
    private Date verAlertTime;

    //修改人（用户id）
    private String verAlertPeople;

    //修改摘要
    private String verContent;

    //测试计划最高版本
    private Double jhBiggestNum;

    //测试用例最高版本
    private Double ylBiggestNum;

    //测试记录最高版本
    private Double jlBiggestNum;

    //缺陷报告最高版本
    private Double qxBiggestNum;

    //测试报告最高版本
    private Double bgBiggestNum;

    //测试输入项最高版本
    private Double shBiggestNum;

    //其他最高版本
    private Double qtBiggestNum;

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

    //测试输入项最高版本
    private String testShPath;

    //其他最高版本
    private String testQtPath;

    public Integer getVerId() {
        return verId;
    }

    public void setVerId(Integer verId) {
        this.verId = verId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getVerNum() {
        return verNum;
    }

    public void setVerNum(Double verNum) {
        this.verNum = verNum ;
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

    public Double getJhBiggestNum() {
        return jhBiggestNum;
    }

    public void setJhBiggestNum(Double jhBiggestNum) {
        this.jhBiggestNum = jhBiggestNum;
    }

    public Double getYlBiggestNum() {
        return ylBiggestNum;
    }

    public void setYlBiggestNum(Double ylBiggestNum) {
        this.ylBiggestNum = ylBiggestNum;
    }

    public Double getJlBiggestNum() {
        return jlBiggestNum;
    }

    public void setJlBiggestNum(Double jlBiggestNum) {
        this.jlBiggestNum = jlBiggestNum;
    }

    public Double getQxBiggestNum() {
        return qxBiggestNum;
    }

    public void setQxBiggestNum(Double qxBiggestNum) {
        this.qxBiggestNum = qxBiggestNum;
    }

    public Double getBgBiggestNum() {
        return bgBiggestNum;
    }

    public void setBgBiggestNum(Double bgBiggestNum) {
        this.bgBiggestNum = bgBiggestNum;
    }

    public Double getShBiggestNum() {
        return shBiggestNum;
    }

    public void setShBiggestNum(Double shBiggestNum) {
        this.shBiggestNum = shBiggestNum;
    }

    public Double getQtBiggestNum() {
        return qtBiggestNum;
    }

    public void setQtBiggestNum(Double qtBiggestNum) {
        this.qtBiggestNum = qtBiggestNum;
    }

    public String getVerContent() {
        return verContent;
    }

    public void setVerContent(String verContent) {
        this.verContent = verContent == null ? null : verContent.trim();
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

    public String getTestShPath() {
        return testShPath;
    }

    public void setTestShPath(String testShPath) {
        this.testShPath = testShPath == null ? null : testShPath.trim();
    }

    public String getTestQtPath() {
        return testQtPath;
    }

    public void setTestQtPath(String testQtPath) {
        this.testQtPath = testQtPath == null ? null : testQtPath.trim();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Version{" +
                "verId=" + verId +
                ", itemId=" + itemId +
                ", item=" + item +
                ", verNum=" + verNum +
                ", verAlertTime=" + verAlertTime +
                ", verAlertPeople='" + verAlertPeople + '\'' +
                ", verContent='" + verContent + '\'' +
                ", jhBiggestNum=" + jhBiggestNum +
                ", ylBiggestNum=" + ylBiggestNum +
                ", jlBiggestNum=" + jlBiggestNum +
                ", qxBiggestNum=" + qxBiggestNum +
                ", bgBiggestNum=" + bgBiggestNum +
                ", shBiggestNum=" + shBiggestNum +
                ", qtBiggestNum=" + qtBiggestNum +
                ", testJhPath='" + testJhPath + '\'' +
                ", testYlPath='" + testYlPath + '\'' +
                ", testJlPath='" + testJlPath + '\'' +
                ", testQxPath='" + testQxPath + '\'' +
                ", testBgPath='" + testBgPath + '\'' +
                ", testShPath='" + testShPath + '\'' +
                ", testQtPath='" + testQtPath + '\'' +
                '}';
    }
}