package com.ai.st.microservice.notifier.services;

import java.util.List;

import com.ai.st.microservice.notifier.entities.NotificationEntity;

public interface INotificationService {

    public NotificationEntity createNotification(NotificationEntity requestEntity);

    public List<NotificationEntity> getNotificationsByUserCodeAndStatus(Long userCode, int status);

    public NotificationEntity getNotificationById(Long id);

    public NotificationEntity updateNotification(NotificationEntity requestEntity);

    public NotificationEntity updateNotificationStatus(Long notificationId, int status);

}
