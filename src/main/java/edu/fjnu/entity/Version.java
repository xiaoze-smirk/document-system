package edu.fjnu.entity;

import edu.fjnu.utils.Utils;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Version {

    //序号
    private Integer verId;

    //文档号
    private String docNum;

    //版本号
    private String verNum;

    //修改时间
    private Date verAlertTime;
    //修改时间辅助类
    private String verAlertTimeStr;

    //修改人
    private String verAlertPeople;

    //修改摘要
    private String verContent;

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
        Utils utils=new Utils();
        this.verAlertTimeStr=utils.timeToStr(this.verAlertTime);
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

    public String getVerAlertTimeStr() {
        return verAlertTimeStr;
    }

    public void setVerAlertTimeStr(String verAlertTimeStr) {
        this.verAlertTimeStr = verAlertTimeStr;
        Utils utils=new Utils();
        this.verAlertTime=utils.strToTime(this.verAlertTimeStr);
    }
}