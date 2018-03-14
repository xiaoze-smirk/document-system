package edu.fjnu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class Item implements Serializable {

    //顺序号（id）
    private Integer itemId;

    //项目号：年份（4位）+客户号（5位）+顺序号（5位）
    private String itemNum;

    //所属客户
    private String clientId;

    //项目名
    private String itemName;

    //状态
    private String itemState;

    //起始日期
    @JSONField(format = "yyyy-MM-dd")
    private Date itemStartDate;

    //结束日期
    @JSONField(format = "yyyy-MM-dd")
    private Date itemDeadline;

    //审核人(用户id)
    private String userAccount;
    //审核人(用户)
    private User user;

    //摘要
    private String itemContent;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? null : itemNum.trim();
        if(this.itemNum.length()==14){
            this.clientId=this.itemNum.substring(4,9);
        }
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemState() {
        return itemState;
    }

    public void setItemState(String itemState) {
        this.itemState = itemState == null ? null : itemState.trim();
    }

    public Date getItemStartDate() {
        return itemStartDate;
    }

    public void setItemStartDate(Date itemStartDate) {
        this.itemStartDate = itemStartDate;
    }

    public Date getItemDeadline() {
        return itemDeadline;
    }

    public void setItemDeadline(Date itemDeadline) {
        this.itemDeadline = itemDeadline;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent == null ? null : itemContent.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemNum='" + itemNum + '\'' +
                ", clientId='" + clientId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemState='" + itemState + '\'' +
                ", itemStartDate=" + itemStartDate +
                ", itemDeadline=" + itemDeadline +
                ", userAccount='" + userAccount + '\'' +
                ", itemContent='" + itemContent + '\'' +
                '}';
    }
}