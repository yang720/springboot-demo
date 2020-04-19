package com.ly.task;

import com.ly.task.bean.mail.Email;
import com.ly.task.bean.mail.AppendFile;
import com.ly.task.bean.mail.Img;
import com.ly.task.service.MailMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Springboot12TaskApplicationTests {

    @Resource
    JavaMailSenderImpl mailSender;

    @Resource
    MailMessageService mailMessageService;

    /**
     * 简单邮件
     */
    @Test
    void contextLoads() {
        Email email = new Email();
        //设置邮件标题和内容
        email.setSubject("来自未来的短信");
        email.setText("你好，我是来自M78星云的居民，在地球即将毁灭的今天，我们愿意帮助人类来到新的生存环境，坐标 -37°，-58°23', 20h2m ,欢迎你们的到来。");
        //妹：1193116277@qq.com  我的网易：wy2216681165@163.com
        //接收人
        email.setAddressee("1193116277@qq.com");
        //发件人
        email.setSender("3341927107@qq.com");

        mailMessageService.sendSimpleMailMessage(email);
    }

    /**
     * 复杂邮件，可以html解析，上传附件
     * @throws Exception
     */
    @Test
    public void test02() throws Exception{
        Email email = new Email();
        //邮件设置  2459625742
        email.setSubject("来自未来的短信");
        email.setText("<b style='color:red'>你好，我是来自M78星云的居民，在地球即将毁灭的今天，我们愿意帮助人类来到新的生存环境，坐标 -37°，-58°23', 20h2m , 204.372AU ,欢迎你们的到来。</b>");
        email.setAddressee("1193116277@qq.com");
        email.setSender("3341927107@qq.com");

        //上传图片
        List<Img> images = new ArrayList<>();
        images.add(new Img("word.jpg","F:\\word.jpg"));
        //上传附件
        List<AppendFile> files = new ArrayList<>();
        files.add(new AppendFile("1.jpg","F:\\1.jpg"));
        files.add(new AppendFile("2.jpeg","F:\\2.jpeg"));
        //上传图片和附件添加到邮件中
        email.setImages(images);
        email.setFiles(files);

        mailMessageService.sendMail(email);
    }

}
