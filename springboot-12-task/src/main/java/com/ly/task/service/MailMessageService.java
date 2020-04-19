package com.ly.task.service;

import com.ly.task.bean.mail.AppendFile;
import com.ly.task.bean.mail.Email;
import com.ly.task.bean.mail.Img;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送邮件：简单邮件，复杂邮件
 * 群发邮件 Email.sendEmail(nameAndAddrMap,subject,htmlContent); 地址集合，标题，内容
 * @author liuyang
 * @date 2020/3/12 21:04
 */
@Service
public class MailMessageService {

    @Resource
    JavaMailSenderImpl mailSender;

    /**
     * 简单邮件
     * @param email 邮件信息
     * @return SimpleMailMessage
     */
    public void sendSimpleMailMessage(Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        //设置邮件标题和内容
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        //收件人
        message.setTo(email.getAddressee());
        //发件人
        message.setFrom(email.getSender());

        mailSender.send(message);
    }

    /**
     * 复杂邮件,上传附件
     */
    public void sendMimeMailByHelper(Email email) throws Exception{
        //创建一个复杂的消息邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        //邮件设置
        helper.setSubject(email.getSubject());
        helper.setText(email.getText());
        helper.setTo(email.getAddressee());
        helper.setFrom(email.getSender());

        //上传附件
        if ( email.getFiles() != null && email.getFiles().size() > 0) {
            for (AppendFile file : email.getFiles()) {
                helper.addAttachment(file.getFilename(), new File(file.getPathname()));

            }
        }

        mailSender.send(mimeMessage);
    }

    /**
     * 支持多图片，多附件发送
     * @param email
     * @throws MessagingException
     */
    public void sendMail(Email email) throws MessagingException {
        //创建一封邮件的实例对象
        MimeMessage msg = mailSender.createMimeMessage();
        //发件人
        msg.setFrom(email.getSender());
        //收件人
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getAddressee()));
        //邮件标题
        msg.setSubject(email.getSubject());

        //邮件内容
        //准备图片数据
        List<MimeBodyPart> imgBodyPartList = new ArrayList<>();
        if ( email.getImages() != null && email.getImages().size() > 0) {
            for (Img img : email.getImages()) {
                MimeBodyPart imagePart = new MimeBodyPart();
                DataHandler handler = new DataHandler(new FileDataSource(img.getPath()));
                imagePart.setDataHandler(handler);
                imagePart.setContentID(img.getImgId()); //设置图片id
                imgBodyPartList.add(imagePart);
            }
        }

        //准备文本
        StringBuffer sBuffer = new StringBuffer(email.getText());
        //上传图片
        if ( email.getImages() != null && email.getImages().size() > 0){
            for (Img img : email.getImages()) {
                sBuffer.append("<img src='cid:").append(img.getImgId()).append("'/>");
            }
        }
        MimeBodyPart text = new MimeBodyPart();
        text.setContent(sBuffer.toString(), "text/html;charset=utf-8");

        //附件
        List<MimeBodyPart> fileBodyParList = new ArrayList<>();
        if ( email.getImages() != null && email.getImages().size() > 0) {
            for (AppendFile file : email.getFiles()) {
                MimeBodyPart appendix = new MimeBodyPart();
                appendix.setDataHandler(new DataHandler(new FileDataSource(file.getPathname())));
                appendix.setFileName(file.getFilename());
                fileBodyParList.add(appendix);
            }
        }


        //拼装邮件正文
        MimeMultipart mimeMultipart = new MimeMultipart();
        if ( imgBodyPartList != null && imgBodyPartList.size() > 0) {
            for (MimeBodyPart imgBodyPart : imgBodyPartList) {
                mimeMultipart.addBodyPart(imgBodyPart);
            }
        }
        mimeMultipart.addBodyPart(text);
        mimeMultipart.setSubType("related");//文本和图片内嵌成功

        //将拼装好的正文内容设置为主体
        MimeBodyPart contentText = new MimeBodyPart();
        contentText.setContent(mimeMultipart);

        //拼接附件
        MimeMultipart allFile = new MimeMultipart();
        if ( fileBodyParList != null && fileBodyParList.size() > 0) {
            for (MimeBodyPart imgBodyPart : fileBodyParList) {
                allFile.addBodyPart(imgBodyPart);//附件
            }
        }
        allFile.addBodyPart(contentText);//正文
        allFile.setSubType("mixed"); //正文和附件都存在邮件中，所有类型设置为mixed


        //放到Message消息中
        msg.setContent(allFile);
        msg.saveChanges();//保存修改

        mailSender.send(msg);
    }

    /**
     * 示例
     * 复杂邮件，发送带图片的邮件
     * @return MimeMessage
     */
    public void sendMimeMailMessage(Email email) throws MessagingException {
        //创建一封邮件的实例对象
        MimeMessage msg = mailSender.createMimeMessage();
        //发件人
        msg.setFrom(email.getSender());
        //收件人
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email.getAddressee()));
        //邮件标题
        msg.setSubject(email.getSubject());

        //邮件内容
        //准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler handler = new DataHandler(new FileDataSource("E:\\IdeaProjects\\WebEmail\\resources\\测试图片.png"));
        image.setDataHandler(handler);
        image.setContentID("test.png"); //设置图片id

        //准备文本
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("这是一段文本<img src='cid:test.png'>", "text/html;charset=utf-8");

        //附件
        MimeBodyPart appendix = new MimeBodyPart();
        appendix.setDataHandler(new DataHandler(new FileDataSource("E:\\IdeaProjects\\WebEmail\\resources\\测试文件.txt")));
        appendix.setFileName("test.txt");

        //拼装邮件正文
        MimeMultipart mimeMultipart = new MimeMultipart();
        mimeMultipart.addBodyPart(image);
        mimeMultipart.addBodyPart(text);
        mimeMultipart.setSubType("related");//文本和图片内嵌成功

        //将拼装好的正文内容设置为主体
        MimeBodyPart contentText = new MimeBodyPart();
        contentText.setContent(mimeMultipart);

        //拼接附件
        MimeMultipart allFile = new MimeMultipart();
        allFile.addBodyPart(appendix);//附件
        allFile.addBodyPart(contentText);//正文
        allFile.setSubType("mixed"); //正文和附件都存在邮件中，所有类型设置为mixed


        //放到Message消息中
        msg.setContent(allFile);
        msg.saveChanges();//保存修改

        mailSender.send(msg);
    }
}
