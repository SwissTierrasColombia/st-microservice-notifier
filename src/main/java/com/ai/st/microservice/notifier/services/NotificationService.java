package com.ai.st.microservice.notifier.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.st.microservice.notifier.entities.NotificationEntity;
import com.ai.st.microservice.notifier.repositories.NotificationRepository;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationEntity createNotification(NotificationEntity notificationEntity) {
        return notificationRepository.save(notificationEntity);
    }

    @Override
    public List<NotificationEntity> getNotificationsByUserCodeAndStatus(Long userCode, int status) {
        return notificationRepository.getNotificationsByUserCodeAndStatus(userCode, status);
    }

    @Override
    public NotificationEntity getNotificationById(Long id) {
        return notificationRepository.findById(id).get();
    }

    @Override
    public NotificationEntity updateNotification(NotificationEntity requestEntity) {
        return notificationRepository.save(requestEntity);
    }

    @Override
    public NotificationEntity updateNotificationStatus(Long notificationId, int status) {
        NotificationEntity e = notificationRepository.findById(notificationId).get();
        e.setStatus(status);
        return notificationRepository.save(e);
    }

}
