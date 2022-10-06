package com.ob11to.spring.service.mail.types;

import lombok.Builder;
import lombok.Value;

import java.util.Set;

@Value
@Builder
public class MailMessageItem {

    String mainRecipient;
    String subject;
    String messageBody;
    Set<String> otherRecipient;

    String pathToAttachment;
}
