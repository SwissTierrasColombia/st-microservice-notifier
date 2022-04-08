package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;

public class NotificationChangeStatusDto implements Serializable {

    private static final long serialVersionUID = 3050930525504636650L;

    private Long notificationId;
    private int status;

    public NotificationChangeStatusDto() {

    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
