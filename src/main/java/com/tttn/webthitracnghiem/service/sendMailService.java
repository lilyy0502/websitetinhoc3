package com.tttn.webthitracnghiem.service;

import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@Service
public class sendMailService {
    public void sendMail (String targetEmail, String passwordGenerate) {
        final String fromEmail = "lyng9722@gmail.com"; // Email gửi
        final String password = "gxvs bnag wlgg izmc"; // Mật khẩu email gửi
        final String toEmail = targetEmail; // Email nhận

        // Thiết lập thuộc tính
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Sử dụng Gmail SMTP
        properties.put("mail.smtp.port", "587"); // Cổng gửi
        properties.put("mail.smtp.auth", "true"); // Yêu cầu xác thực
        properties.put("mail.smtp.starttls.enable", "true"); // Bật TLS
        properties.put("mail.mime.charset", "UTF-8"); // Bật TLS

        // Tạo session với thông tin xác thực
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Tạo email
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "Website trắc nghiệm", "UTF-8")); // Người gửi
            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(toEmail)); // Người nhận
            message.setSubject(MimeUtility.encodeText("Quên mật khẩu", "UTF-8", "B")); // Tiêu đề email
            message.setContent("Website trắc nghiệm chào bạn\n\nMật khẩu của bạn đã được đổi thành: " + passwordGenerate, "text/plain; charset=UTF-8"); // Nội dung email

            // Gửi email
            Transport.send(message);

            System.out.println("Email đã được gửi thành công!");
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("Đã xảy ra lỗi khi gửi email.");
        }
    }
}

