package com.ai.st.microservice.notifier.services.rabbitmq.listeners;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.ai.st.microservice.notifier.services.tracing.SCMTracing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.ai.st.microservice.notifier.dto.rabbitmq.NotificationMessageDto;
import com.ai.st.microservice.notifier.entities.NotificationEntity;
import com.ai.st.microservice.notifier.models.services.INotificationService;

@Component
public class RabbitMQNotificationListener {

    private final Logger log = LoggerFactory.getLogger(RabbitMQNotificationListener.class);

    @Value("${st.site.email}")
    public String siteEmail;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = "${st.rabbitmq.queueNotifications.queue}")
    public void sendEmail(NotificationMessageDto notificationMessage) {

        log.info(String.format("Procesando mensaje de notificación: %s", notificationMessage.toString()));

        NotificationEntity notification = new NotificationEntity(null, notificationMessage.getUserCode(),
                notificationMessage.getEmail(), notificationMessage.getSubject(), notificationMessage.getMessage(),
                notificationMessage.getType(), 1, new Date(), new Date());

        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(msg, true, "UTF-8");
            helper.setTo(notificationMessage.getEmail());
            helper.setSubject(notificationMessage.getSubject());
            helper.setText(notificationMessage.getMessage(), true);
            helper.setFrom(siteEmail);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            String messageError = String.format("Error enviando correo %s : %s", notificationMessage, e.getMessage());
            SCMTracing.sendError(messageError);
            log.error(messageError);
        } catch (Exception e) {
            String messageError = String.format("Error general enviando correo %s : %s", notificationMessage,
                    e.getMessage());
            SCMTracing.sendError(messageError);
            log.error(messageError);
        }

        notificationService.createNotification(notification);
    }

}
