package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificationAssignmentOperationMunicipalityDto implements Serializable {

	private static final long serialVersionUID = 3050930525504636650L;

	private Long userCode;
	private String email;
	private String type;
	private int status;
	private String manager;
	private String mpio;
	private String dpto;
	private String requestNumber;
	private Date requestDateFrom;
	private Date requestDateTo;
	private String supportFile;

	public NotificationAssignmentOperationMunicipalityDto() {

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	
	public Date getRequestDateFrom() {
		return requestDateFrom;
	}

	public void setRequestDateFrom(Date requestDateFrom) {
		this.requestDateFrom = requestDateFrom;
	}

	public Date getRequestDateTo() {
		return requestDateTo;
	}

	public void setRequestDateTo(Date requestDateTo) {
		this.requestDateTo = requestDateTo;
	}

	public String getSupportFile() {
		return supportFile;
	}

	public void setSupportFile(String supportFile) {
		this.supportFile = supportFile;
	}

	public String getSubject() {
		return "Notificación Sistema de Transición Barrido Predial – Asignación Operación Municipio";
	}

	public String getBody() {
		String html = "";
		html += "<div>El Sistema De Transición para el Barrido Predial en Colombia le informa:</div>";
		html += "<div>Que el GESTOR CATASTRAL "+this.manager+" le ha asignado/modificado la OPERACIÓN CATASTRAL del municipio de "+this.mpio+" del departamento de "+this.dpto+" A partir del "+this.requestDateFrom.toString()+" Y hasta el "+this.requestDateTo.toString()+" de acuerdo con el documento soporte “"+this.supportFile+"”.</div>";
		html += "<div>Para mayor detalle por favor diríjase al Sistema de Transición en la siguiente URL e ingrese con su respectivo usuario y contraseña que le ha sido asignada previamente.</div>";
		html += "<div><a href='https://st.proadmintierra.info/login'>https://st.proadmintierra.info/login</a></div>";
		html += "<div>Nota: Cualquier inquietud o inconveniente en el ingreso a la plataforma por favor comunicarse con el siguiente correo: soporte_ST@proadmintierra.info</div>";
		html += "<div>--</div>";
		html += "SISTEMA DE TRANSICIÓN</div>";
		return html;

	}

}