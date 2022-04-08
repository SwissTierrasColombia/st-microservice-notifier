package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;

public class NotificationsListRequestDto implements Serializable {

    private static final long serialVersionUID = 3050930525504636650L;

    private Long userCode;

    public NotificationsListRequestDto() {

    }

    public NotificationsListRequestDto(Long userCode, String email, String message, String type) {
        this.userCode = userCode;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

}
