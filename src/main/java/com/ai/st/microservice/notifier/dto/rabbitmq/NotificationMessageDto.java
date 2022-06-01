package com.ai.st.microservice.notifier.dto.rabbitmq;

import java.io.Serializable;

public class NotificationMessageDto implements Serializable {

    private static final long serialVersionUID = 3050930525504636650L;

    private Long userCode;
    private String email;
    private String subject;
    private String message;
    private String type;

    public NotificationMessageDto() {

    }

    public NotificationMessageDto(Long userCode, String email, String subject, String message, String type) {
        this.userCode = userCode;
        this.email = email;
        this.message = message;
        this.type = type;
        this.subject = subject;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "NotificationMessageDto{" + "userCode=" + userCode + ", email='" + email + '\'' + ", subject='" + subject
                + '\'' + ", message='" + message + '\'' + ", type='" + type + '\'' + '}';
    }
}
