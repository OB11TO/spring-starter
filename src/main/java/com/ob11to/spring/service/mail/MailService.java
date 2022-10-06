package com.ob11to.spring.service.mail;

import com.ob11to.spring.config.MailConfig;
import com.ob11to.spring.service.mail.types.MailMessageItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    private final MailConfig mailConfig;
    private final JavaMailSender javaMailSender;

    public boolean sendMail(MailMessageItem mailMessageItem) {
        try {
            log.info("Send mail massage : {}", mailMessageItem);

            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            var mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);

            mimeMessageHelper.setFrom(mailConfig.getMailSender());
            mimeMessageHelper.setTo(mailMessageItem.getMainRecipient());
            mimeMessageHelper.setSubject(mailMessageItem.getSubject());
            mimeMessageHelper.setText(mailMessageItem.getMessageBody());

            if (mailMessageItem.getOtherRecipient() != null) {
                for (String recipient : mailMessageItem.getOtherRecipient()) {
                    mimeMessageHelper.addCc(recipient);
                }
            }

            FileSystemResource attachment = new FileSystemResource(new File(mailMessageItem.getPathToAttachment()));
            mimeMessageHelper.addAttachment(Objects.requireNonNull(attachment.getFilename()), attachment);

            javaMailSender.send(mimeMailMessage);

            log.info("Massage has been successfully sent");
            return true;
        } catch (Exception exception) {
            log.error("{} : {}", exception.getClass().getName(), exception.getMessage());
            return false;
        }
    }
}
