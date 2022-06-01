package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;

public class NotificationRecoverAccountDto implements Serializable {

    private static final long serialVersionUID = -3073283184260792970L;

    private Long userCode;
    private String email;
    private String type;
    private int status;

    private String code;
    private String expirationDate;
    private String username;

    private String siteURL;
    private String siteEmail;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubject() {
        return "Notificación Sistema de Transición Barrido Predial – Recuperar Cuenta";
    }

    public String getBody() {
        String html = "";
        html += "<div>El Sistema de Transición para el Barrido Predial en Colombia le informa:</div><br>";

        html += "<div>Que se realizó una solicitud de recuperación de usuario y/o contraseña para el siguiente usuario:</div><br />";

        html += "<div><b>Usuario:</b>" + this.username + "</div>";
        html += "<div><b>Código de recuperación:</b>" + this.code + "</div>";
        html += "<div><b>Fecha expiración:</b>" + this.expirationDate + "</div><br />";

        html += "<div>En la interfaz de recuperación de contraseña del Sistema, por favor introduzca el nombre de usuario y Token para restablecer la contraseña e ingresar.</div><br />";
        html += "<div>En caso de solo haber olvidado el usuario, ingrese normalmente sin utilizar el Token en la página de autenticación del Sistema.</div><br />";

        html += "<div>Nota: Cualquier inquietud o inconveniente en el ingreso a la plataforma por favor comunicarse con el siguiente correo: "
                + siteEmail + "</div>";
        html += "<br><div>--</div>";
        html += "SISTEMA DE TRANSICIÓN</div>";
        return html;
    }

    @Override
    public String toString() {
        return "NotificationRecoverAccountDto{" + "userCode=" + userCode + ", email='" + email + '\'' + ", type='"
                + type + '\'' + ", status=" + status + ", code='" + code + '\'' + ", expirationDate='" + expirationDate
                + '\'' + ", username='" + username + '\'' + ", siteURL='" + siteURL + '\'' + ", siteEmail='" + siteEmail
                + '\'' + '}';
    }
}
