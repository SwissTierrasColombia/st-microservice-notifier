package com.ai.st.microservice.notifier.services;

import java.util.List;

import com.ai.st.microservice.notifier.entities.NotificationEntity;

public interface INotificationService {

    NotificationEntity createNotification(NotificationEntity requestEntity);

    List<NotificationEntity> getNotificationsByUserCodeAndStatus(Long userCode, int status);

    NotificationEntity getNotificationById(Long id);

    NotificationEntity updateNotification(NotificationEntity requestEntity);

    NotificationEntity updateNotificationStatus(Long notificationId, int status);

}
