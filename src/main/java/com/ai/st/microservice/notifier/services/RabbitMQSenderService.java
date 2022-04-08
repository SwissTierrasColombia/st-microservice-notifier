package com.ai.st.microservice.notifier.services;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ai.st.microservice.notifier.dto.NotificationDto;

@Service
public class RabbitMQSenderService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${st.rabbitmq.queueNotifications.exchange}")
    public String exchangeFilesName;

    @Value("${st.rabbitmq.queueNotifications.routingkey}")
    public String routingkeyFilesName;

    public void sendNotification(Long userCode, String email, String subject, String message, String type) {
        NotificationDto data = new NotificationDto(userCode, email, subject, message, type);
        rabbitTemplate.convertAndSend(exchangeFilesName, routingkeyFilesName, data);
    }

}
