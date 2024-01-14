package com.springboot.blogApp.service.serviceImpl;

import com.springboot.blogApp.service.EmailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSendServiceImpl implements EmailSendService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Override
    public void sendSimpleMail(String to, String body, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("officeshahzain97@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        System.out.println("mail send");


    }
}
