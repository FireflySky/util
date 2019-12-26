package com.iask.starpires.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * 发送邮件
 * @author yuanp
 * @date 2019/11/11
 */
@Component
public class MailUtil {

    /**
     * 发送的html模板
     */
    @Value("${mail.forgot.template}")
    private String forgotFile;

    /**
     * 发送邮箱服务器类
     */
    @Autowired
    private JavaMailSenderImpl mailSender;
    /**
     * 发送带网页格式的邮件
     */
    public void sendHTML(String recipient,String subject,String content) throws MessagingException {
        MimeMessage msg=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(msg,true,"UTF-8");
            helper.setFrom(mailSender.getUsername());
            helper.setTo(recipient);
            helper.setSubject(subject);
            helper.setText(content,true);
        mailSender.send(msg);
    }

    /**
     * 读取邮箱类容 忘记密码邮件模板
     */
    public String forgotPasswordMailContent(String userName,String userPwd) throws IOException {
        String filePath = MailUtil.class.getResource("/"+forgotFile.split(",")[0]).getPath();
        String content = FileReadUtil.readFileContent(filePath);
        content=content.replace("#userName",userName);
        return content.replace("#userPwd",userPwd);
    }

    /**
     * 读取邮箱类容 修改密码验证码邮件模板
     * @param invalid
     * @return
     * @throws IOException
     */
    public String invalideMailContent(String invalid)throws IOException {
        String filePath = MailUtil.class.getResource("/"+forgotFile.split(",")[1]).getPath();
        System.out.println("filePath: "+filePath);
        String content = FileReadUtil.readFileContent(filePath);
        return content.replace("#invalide",invalid);
    }
}
