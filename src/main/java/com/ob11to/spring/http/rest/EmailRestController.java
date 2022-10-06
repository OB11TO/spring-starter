package com.ob11to.spring.http.rest;

import com.ob11to.spring.service.mail.MailService;
import com.ob11to.spring.service.mail.types.MailMessageItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
public class EmailRestController {

    private final MailService mailService;

    @GetMapping("/simple-order-email/{user-email}/{users-email}")
    public ResponseEntity<String> sendEmailAttachment(@PathVariable("user-email") String email,
                                                      @PathVariable("users-email") Set<String> otherEmails) {    //Что возвращать ?
        var test = MailMessageItem.builder()
                .mainRecipient(email)
                .messageBody("Напиши мне потом, если получишь это сообщение =)")
                .subject("Первая рассылка в Spring-Boot-Starter-Mail")
                .otherRecipient(otherEmails)
                .pathToAttachment("/home/obiito/c/java/full_day/java_prac/demDev/image/hello.gif")  // Как лучше всего избавиться от хардкда
                .build();

        if (!mailService.sendMail(test)) {
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Sms ok", HttpStatus.OK);
    }
}
