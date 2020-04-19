package com.ly.task.bean.mail;

import java.util.List;

/**
 * 邮件信息
 * @author liuyang
 * @date 2020/3/12 23:09
 */
public class Email {

    /**
     * 标题
     */
    private String subject;
    /**
     * 内容
     */
    private String text;
    /**
     * 发件人
     */
    private String sender;
    /**
     * 收件人
     */
    private String addressee;
    /**
     * 图片
     */
    private List<Img> images;
    /**
     * 上传附件集合
     */
    private List<AppendFile> files;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public List<Img> getImages() {
        return images;
    }

    public void setImages(List<Img> images) {
        this.images = images;
    }

    public List<AppendFile> getFiles() {
        return files;
    }

    public void setFiles(List<AppendFile> files) {
        this.files = files;
    }
}
