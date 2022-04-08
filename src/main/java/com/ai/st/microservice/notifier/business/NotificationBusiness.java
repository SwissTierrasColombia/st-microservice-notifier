package com.ai.st.microservice.notifier.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.st.microservice.notifier.dto.NotificationDto;
import com.ai.st.microservice.notifier.entities.NotificationEntity;
import com.ai.st.microservice.notifier.exceptions.NotificationException;
import com.ai.st.microservice.notifier.services.INotificationService;
import com.ai.st.microservice.notifier.services.RabbitMQSenderService;

@Component
public class NotificationBusiness {

    @Autowired
    private RabbitMQSenderService rabbitMQService;

    @Autowired
    private INotificationService notificationService;

    public NotificationDto createNotification(Long userCode, String email, String subject, String message, String type)
            throws NotificationException {

        rabbitMQService.sendNotification(userCode, email, subject, message, type);

        NotificationDto notification = new NotificationDto();
        notification.setEmail(email);
        notification.setMessage(message);
        notification.setType(type);
        notification.setUserCode(userCode);
        notification.setStatus(0);
        notification.setSubject(subject);

        return notification;
    }

    public List<NotificationEntity> getNotifications(Long userCode, int status) {
        List<NotificationEntity> response = notificationService.getNotificationsByUserCodeAndStatus(userCode, status);
        return response;
    }

    public NotificationEntity changeNotificationStatus(Long notificationId, int status) throws NotificationException {
        return notificationService.updateNotificationStatus(notificationId, status);
    }

}
