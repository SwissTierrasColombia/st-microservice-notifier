package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificationTaskAssignmentDto implements Serializable {

	private static final long serialVersionUID = 3050930525504636650L;

	private Long userCode;
	private String email;
	private String type;
	private int status;
	private String task;
	private String mpio;
	private String dpto;
	private Date taskDate;

	public NotificationTaskAssignmentDto() {

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

	public String getMpio() {
		return mpio;
	}

	public void setMpio(String mpio) {
		this.mpio = mpio;
	}

	public String getDpto() {
		return dpto;
	}

	public void setDpto(String dpto) {
		this.dpto = dpto;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public String getSubject() {
		return "Notificación Sistema de Transición Barrido Predial – Asignación de Tarea " + this.task;
	}

	public String getBody() {
		String html = "";
		html += "<div>El Sistema De Transición para el Barrido Predial en Colombia le informa:</div>";
		html += "<div>Que el " + this.taskDate.toString() + " se le ha asignado la tarea " + this.task
				+ " del municipio de " + this.mpio + " del departamento de " + this.dpto + ".</div>";
		html += "<div>Para mayor detalle por favor diríjase al Sistema de Transición en la siguiente URL e ingrese con su respectivo usuario y contraseña que le ha sido asignada previamente o si es el caso desarróllela directamente en el Asistente LADM_COL del programa QGIS utilizando las mismas credenciales.</div>";
		html += "<div><a href='https://st-pruebas.proadmintierra.info/login'>https://st-pruebas.proadmintierra.info/login</a></div>";
		html += "<div>Nota: Cualquier inquietud o inconveniente en el ingreso a la plataforma por favor comunicarse con el siguiente correo: soporte_ST@proadmintierra.info</div>";
		html += "<div>--</div>";
		html += "SISTEMA DE TRANSICIÓN</div>";
		return html;
	}

}
