package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationMunicipalityManagementDto implements Serializable {

	private static final long serialVersionUID = 3050930525504636650L;

	private Long userCode;
	private String email;
	private String type;
	private int status;
	private String mpio;
	private String dpto;
	private Date startDate;
	private String supportFile;

	public NotificationMunicipalityManagementDto() {

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getSupportFile() {
		return supportFile;
	}

	public void setSupportFile(String supportFile) {
		this.supportFile = supportFile;
	}

	public String getSubject() {
		return "Notificación Sistema de Transición Barrido Predial – Asignación Gestión Municipio";
	}

	public String getBody() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String html = "";
		html += "<div>El Sistema de Transición para el Barrido Predial en Colombia le informa:</div><br>";
		html += "<div>Se ha asignado/modificado la GESTIÓN CATASTRAL del municipio de " + this.mpio
				+ " del departamento de " + this.dpto + " A partir del " + formatter.format(this.startDate)
				+ " de acuerdo con el documento soporte que se encuentra en el sistema.</div><br>";
		html += "<div>Para mayor detalle por favor diríjase al Sistema de Transición en la siguiente URL e ingrese con su respectivo usuario y contraseña que le ha sido asignada previamente.</div>";
		html += "<div><a href='https://st-pruebas.proadmintierra.info/login'>https://st-pruebas.proadmintierra.info/login</a></div><br>";
		html += "<div>Nota: Cualquier inquietud o inconveniente en el ingreso a la plataforma por favor comunicarse con el siguiente correo: soporte_ST@proadmintierra.info</div>";
		html += "<br><div>--</div>";
		html += "SISTEMA DE TRANSICIÓN</div>";
		return html;

	}

}
