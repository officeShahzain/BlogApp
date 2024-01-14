package com.springboot.blogApp.service;

public interface EmailSendService {
    void sendSimpleMail(String to, String body, String subject);
}
