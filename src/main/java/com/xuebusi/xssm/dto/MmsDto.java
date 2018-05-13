package com.xuebusi.xssm.dto;

import java.io.Serializable;

public class MmsDto implements Serializable {

    private String userName;

    private String password;

    private String title;

    private String userNumbers;

    private String sendType;

    private String MMSContent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNumbers() {
        return userNumbers;
    }

    public void setUserNumbers(String userNumbers) {
        this.userNumbers = userNumbers;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getMMSContent() {
        return MMSContent;
    }

    public void setMMSContent(String MMSContent) {
        this.MMSContent = MMSContent;
    }
}
