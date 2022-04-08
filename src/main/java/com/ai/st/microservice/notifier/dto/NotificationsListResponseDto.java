package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.util.List;

public class NotificationsListResponseDto implements Serializable {

    private static final long serialVersionUID = 3050930525504636650L;

    private List<NotificationDto> list;

    public NotificationsListResponseDto() {

    }

    public List<NotificationDto> getList() {
        return list;
    }

    public void setList(List<NotificationDto> list) {
        this.list = list;
    }

}
