package com.ai.st.microservice.notifier.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ai.st.microservice.notifier.entities.NotificationEntity;


public interface NotificationRepository extends CrudRepository<NotificationEntity, Long> {

	@Override
	List<NotificationEntity> findAll();

	@Query("SELECT n FROM NotificationEntity n WHERE n.userCode = :userCode AND n.status = :status")
	List<NotificationEntity> getNotificationsByUserCodeAndStatus(@Param("userCode") Long userCode, @Param("status") int status);

}
