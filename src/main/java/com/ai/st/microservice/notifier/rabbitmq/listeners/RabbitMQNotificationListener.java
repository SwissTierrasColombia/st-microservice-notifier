package com.ai.st.microservice.notifier.rabbitmq.listeners;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

// import java.util.logging.Logger;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ai.st.microservice.notifier.dto.rabbitmq.NotificationMessageDto;
import com.ai.st.microservice.notifier.entities.NotificationEntity;
import com.ai.st.microservice.notifier.services.INotificationService;

@Component
public class RabbitMQNotificationListener {

	// private final static Logger log =
	// Logger.getLogger(RabbitMQNotificationListener.class.getName());

	@Autowired
	private INotificationService notificationService;
	
	@Autowired
    private JavaMailSender javaMailSender;

	@RabbitListener(queues = "${st.rabbitmq.queueNotifications.queue}")
	public String recievedMessageFile(NotificationMessageDto message) {
		
		System.out.println(">>>>>>>>>>>>>>>>>> PROCESANDO MENSAJE <<<<<<<<<<<<<<<<<<<<<<");
		
		NotificationEntity notification = new NotificationEntity(
				null, 
				message.getUserCode(), 
				message.getEmail(), 
				message.getSubject(),
				message.getMessage(), 
				message.getType(), 
				1, 
				new Date(), 
				new Date()
		);
		
		
		MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(msg, true);
			helper.setTo(message.getEmail());
			helper.setSubject(message.getSubject());
			helper.setText(message.getMessage(), true);
			javaMailSender.send(msg);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		notificationService.createNotification(notification);

		return "";
	}

}