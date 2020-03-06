package com.ai.st.microservice.notifier.exceptions;

public class NotificationException extends Exception {

	private static final long serialVersionUID = -6306603143465394875L;

	private String messageError;

	public NotificationException(String message) {
		super();
		this.messageError = message;
	}

	public String getMessageError() {
		return messageError;
	}

	@Override
	public String getMessage() {
		return this.getMessageError();
	}

}
