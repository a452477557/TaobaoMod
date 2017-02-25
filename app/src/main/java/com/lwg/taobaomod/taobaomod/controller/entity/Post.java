package com.lwg.taobaomod.taobaomod.controller.entity;

import java.io.Serializable;

/**
 * Post -- 帖子实体类
 * Serializable -- 序列化 -- 在Intent中可以作为参数传递
 */
public class Post implements Serializable{
    private String subject; //标题:
    private String content;  //地区:
    private String  str1;//价格
    private String num1;//金币抵:
    private String num2;//包邮:
    private String ttype;//类型 :	2000
    private String rate;//付款人数:
    private String pic; //图片:	upload/ps1.jpg
    private String tags;//天猫:	1-是; 0-否

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStr1() {
        return str1;
    }

    public void setStr1(String str1) {
        this.str1 = str1;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    public String getTtype() {
        return ttype;
    }

    public void setTtype(String ttype) {
        this.ttype = ttype;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", str1='" + str1 + '\'' +
                ", num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                ", ttype='" + ttype + '\'' +
                ", rate=" + rate +
                ", pic='" + pic + '\'' +
                ", tags='" + tags + '\'' +
                '}';
    }
}
