package edu.fjnu.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class Item implements Serializable {

    //顺序号：编号
    private Integer autoId;

    //项目号：年份（4位）+客户号（3位）+顺序号（3位）
    private String itemId;

    //所属客户
    private String clientId;

    //项目名
    private String itemName;

    //起始日期
    @JSONField(format = "yyyy-MM-dd")
    private Date itemStartDate;

    //结束日期
    @JSONField(format = "yyyy-MM-dd")
    private Date itemDeadline;

    //业务负责人
    private String itemPrincipal;

    public Integer getAutoId() {
        return autoId;
    }

    public void setAutoId(Integer autoId) {
        this.autoId = autoId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {

        this.itemId = itemId == null ? null : itemId.trim();
        if(this.itemId.length()==10){
            this.clientId=this.itemId.substring(4,7);
        }

    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
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

    public String getItemPrincipal() {
        return itemPrincipal;
    }

    public void setItemPrincipal(String itemPrincipal) {
        this.itemPrincipal = itemPrincipal == null ? null : itemPrincipal.trim();
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "Item{" +
                "autoId=" + autoId +
                ", itemId='" + itemId + '\'' +
                ", clientId='" + clientId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemStartDate=" + itemStartDate +
                ", itemDeadline=" + itemDeadline +
                ", itemPrincipal='" + itemPrincipal + '\'' +
                '}';
    }
}