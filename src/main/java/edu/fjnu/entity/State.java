package edu.fjnu.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class State implements Serializable {

    //状态标识符:未测(a)、启动(b)、计划(c)、用例(d)、报告(e)、结束(f)、投诉(g)
    private String stateStr;

    //对应的状态名称
    private String stateStrName;

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr == null ? null : stateStr.trim();
    }

    public String getStateStrName() {
        return stateStrName;
    }

    public void setStateStrName(String stateStrName) {
        this.stateStrName = stateStrName == null ? null : stateStrName.trim();
    }

}