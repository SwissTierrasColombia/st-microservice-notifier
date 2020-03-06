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
	

	public String sendNotification(Long userCode, String email, String subject, String message, String type) {
		String r = null;
		NotificationDto omessage = new NotificationDto(userCode, email, subject, message, type);
		r = (String) rabbitTemplate.convertSendAndReceive(exchangeFilesName, routingkeyFilesName, omessage);
		return r;
	}

}
