package com.ai.st.microservice.notifier.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "notifications", schema = "notifications")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_code", nullable = false)
    private Long userCode;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Column(name = "subject", nullable = false, length = 255)
    private String subject;

    @Column(name = "message", nullable = false, length = 5000)
    private String message;

    @Column(name = "type", nullable = false, length = 255)
    private String type;

    // 0 -> sending, 1 -> sent, 2-> read
    @Column(name = "status", nullable = false, length = 255)
    private int status;

    @Column(name = "change_status_at", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date changeStatusAt;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public NotificationEntity() {

    }

    public NotificationEntity(Long id, Long userCode, String email, String subject, String message, String type,
            int status, Date changeStatusAt, Date createdAt) {
        super();
        this.id = id;
        this.userCode = userCode;
        this.email = email;
        this.message = message;
        this.type = type;
        this.status = status;
        this.changeStatusAt = changeStatusAt;
        this.createdAt = createdAt;
        this.subject = subject;
    }

    public Long getUserCode() {
        return userCode;
    }

    public void setUserCode(Long userCode) {
        this.userCode = userCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getChangeStatusAt() {
        return changeStatusAt;
    }

    public void setChangeStatusAt(Date changeStatusAt) {
        this.changeStatusAt = changeStatusAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}
