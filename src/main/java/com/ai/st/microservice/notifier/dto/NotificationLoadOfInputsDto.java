package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationLoadOfInputsDto implements Serializable {

	private static final long serialVersionUID = 3050930525504636650L;

	private Long userCode;
	private String email;
	private String type;
	private int status;
	private boolean loadStatus;
	private String mpio;
	private String dpto;
	private String requestNumber;
	private Date loadDate;
	private String supportFile;
	private String siteURL;
	private String siteEmail;

	public NotificationLoadOfInputsDto() {

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

	public String getSupportFile() {
		return supportFile;
	}

	public void setSupportFile(String supportFile) {
		this.supportFile = supportFile;
	}

	public boolean isLoadStatus() {
		return loadStatus;
	}

	public void setLoadStatus(boolean loadStatus) {
		this.loadStatus = loadStatus;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public Date getLoadDate() {
		return loadDate;
	}

	public void setLoadDate(Date loadDate) {
		this.loadDate = loadDate;
	}

	public String getSiteURL() {
		return siteURL;
	}

	public void setSiteURL(String siteURL) {
		this.siteURL = siteURL;
	}

	public String getSiteEmail() {
		return siteEmail;
	}

	public void setSiteEmail(String siteEmail) {
		this.siteEmail = siteEmail;
	}

	public String getSubject() {
		return "Notificación Sistema de Transición Barrido Predial – Cargue de Insumo XTF";
	}

	public String getBody() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String msgStatus = "ha sido validado y cargado SATISFACTORIAMENTE.";
		if (!this.loadStatus) {
			msgStatus = "ha sido validado y ha FALLADO en su carga, por lo cual se solicita volver a subirlo al sistema.";
		}
		String html = "";
		html += "<div>El Sistema de Transición para el Barrido Predial en Colombia le informa:</div><br>";
		html += "<div>Que el insumo “" + this.supportFile + "” del municipio de " + this.mpio + " del departamento de "
				+ this.dpto + " con el número de SOLICITUD <b>" + this.requestNumber + "</b> cargado el "
				+ formatter.format(this.loadDate) + ", " + msgStatus + "</div><br>";
		html += "<div>Para mayor detalle por favor diríjase al Sistema de Transición en la siguiente URL e ingrese con su respectivo usuario y contraseña que le ha sido asignada previamente.</div>";
		html += "<div><a href='" + siteURL + "'>" + siteURL + "</a></div><br>";
		html += "<div>Nota: Cualquier inquietud o inconveniente en el ingreso a la plataforma por favor comunicarse con el siguiente correo: "
				+ siteEmail + "</div>";
		html += "<br><div>--</div>";
		html += "SISTEMA DE TRANSICIÓN</div>";
		return html;

	}

}
