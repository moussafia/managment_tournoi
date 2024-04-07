package ma.youcode.managment_tournoi_backend.util.mail;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import ma.youcode.managment_tournoi_backend.entity.AppUser;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import freemarker.template.Configuration;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class MailUtil {
    private JavaMailSender mailSender;
    private  Configuration configuration;
    public MailResponse sendMail(AppUser member, String password) {
        MailRequest mailRequest = MailRequest.builder()
                .to(member.getEmail())
                .subject("Welcome to YouCode Soccer Tournament")
                .name(member.getUsername())
                .build();
        HashMap<String , Object> map = new HashMap<>();
        map.put("password", password);
        map.put("username", member.getUsername());
        map.put("lastName", member.getLastName());
        return this.sendMailUtil(mailRequest, map);
    }
    private MailResponse sendMailUtil(MailRequest request, Map<String, Object> model) {
        MailResponse mailResponse = new MailResponse();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());
            helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
            Template template = configuration.getTemplate("email-template.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(request.getTo());
            helper.setText(html, true);
            helper.setSubject(request.getSubject());
            mailSender.send(mimeMessage);
            mailResponse.setMessage("mail send to : " + request.getTo());
            mailResponse.setStatus(Boolean.TRUE);
        } catch (MessagingException | TemplateException | IOException e) {
            e.printStackTrace();
            mailResponse.setMessage("Mail Sending failure : "+e.getMessage());
            mailResponse.setStatus(Boolean.FALSE);
        }
        return mailResponse;
    }

}
