package com.clearwateranalytics.quiz.service;

import com.clearwateranalytics.quiz.pojo.request.MailRequest;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Configuration configuration;

    public String sendMail(MailRequest request, Map<String, String> model, File convFile) {
        String response;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            ClassPathResource image = new ClassPathResource("static/img.png");
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(InternetAddress.parse(request.getTo()));
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(html, true);
            helper.addInline("logo", image);
            helper.addAttachment("attachment.pdf", convFile);
            mailSender.send(message);
            response = "Email has been sent to :" + request.getTo();
        } catch (MessagingException | IOException | TemplateException e) {
            response = "Email send failure to :" + request.getTo();
        }
        return response;
    }
}
