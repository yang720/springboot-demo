package com.ly.task.bean.mail;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;

/**
 * 邮件图片
 * @author liuyang
 * @date 2020/3/13 12:53
 */

public class Img {

    private String name;
    private String path;
    private String imgId;

    public Img(){
    }

    public Img(String name,String path){
        this.name = name;
        this.path = path;
        this.imgId = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }
}
