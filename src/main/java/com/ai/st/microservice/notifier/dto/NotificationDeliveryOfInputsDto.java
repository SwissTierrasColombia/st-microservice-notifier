package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.util.Date;

public class NotificationDeliveryOfInputsDto implements Serializable {

    private static final long serialVersionUID = 3050930525504636650L;

    private Long userCode;
    private String email;
    private String type;
    private int status;
    private String manager;
    private String mpio;
    private String dpto;
    private String supportFile;
    private Date requestDate;
    private String siteURL;
    private String siteEmail;

    public NotificationDeliveryOfInputsDto() {

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

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getSupportFile() {
        return supportFile;
    }

    public void setSupportFile(String supportFile) {
        this.supportFile = supportFile;
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
        return "Notificación Sistema de Transición Barrido Predial – Entrega de Insumos";
    }

    public String getBody() {
        String html = "";
        html += "<div>El Sistema de Transición para el Barrido Predial en Colombia le informa:</div><br>";
        html += "<div>Que el " + this.requestDate.toString() + " el GESTOR CATASTRAL " + this.manager
                + " le ha dispuesto los INSUMOS NECESARIOS para el desarrollo de las actividades asignadas en el municipio de "
                + this.mpio + " del departamento de " + this.dpto
                + " de acuerdo con lo definido en la asignación para este municipio.</div><br>";
        html += "<div>Para mayor detalle por favor diríjase al Sistema de Transición en la siguiente URL e ingrese con su respectivo usuario y contraseña que le ha sido asignada previamente o si es el caso desarróllela directamente en el Asistente LADM_COL del programa QGIS utilizando las mismas credenciales.</div>";
        html += "<div><a href='" + siteURL + "'>" + siteURL + "</a></div><br>";
        html += "<div>Nota: Cualquier inquietud o inconveniente en el ingreso a la plataforma por favor comunicarse con el siguiente correo: "
                + siteEmail + "</div>";
        html += "<br><div>--</div>";
        html += "SISTEMA DE TRANSICIÓN</div>";
        return html;
    }

    @Override
    public String toString() {
        return "NotificationDeliveryOfInputsDto{" + "userCode=" + userCode + ", email='" + email + '\'' + ", type='"
                + type + '\'' + ", status=" + status + ", manager='" + manager + '\'' + ", mpio='" + mpio + '\''
                + ", dpto='" + dpto + '\'' + ", supportFile='" + supportFile + '\'' + ", requestDate=" + requestDate
                + ", siteURL='" + siteURL + '\'' + ", siteEmail='" + siteEmail + '\'' + '}';
    }
}
