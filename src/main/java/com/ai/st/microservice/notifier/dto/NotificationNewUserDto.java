package com.ai.st.microservice.notifier.dto;

import java.io.Serializable;
import java.nio.charset.*;

public class NotificationNewUserDto implements Serializable {

    private static final long serialVersionUID = 3050930525504636650L;

    private Long userCode;
    private String email;
    private String type;
    private int status;
    private String user;
    private String password;
    private String profile;
    private String siteURL;
    private String siteEmail;

    public NotificationNewUserDto() {

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
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
        String value = new String(
                "Notificación Sistema de Transición Barrido Predial – Creación/Modificación de Usuario".getBytes(),
                StandardCharsets.UTF_8);
        return value;
    }

    public String getBody() {

        if (type.equals("sinic")) {
            return bodyIsSinicUser();
        }

        String html = "";
        html += "<div>El Sistema de Transición para el Barrido Predial en Colombia le informa:</div><br>";
        html += "<div>Se ha creado/modificado un usuario asignado a usted para que ingrese en el sistema y consulte las actividades/tareas que se le han asignado.</div>";
        html += "<div>Ingrese a la siguiente URL: <a href='" + this.siteURL + "'>" + siteURL + "</a></div><br>";
        html += "<div>Sus credenciales son las siguientes:</div>";
        html += "<div>USUARIO: " + this.user + "</div>";
        html += "<div>ENTIDAD: " + this.profile + "</div>";
        html += "<div>CONTRASEÑA: " + this.password + "</div><br>";
        html += "<div>Nota Importante: Se solicita al usuario cambiar la contraseña una vez se ingrese por primera vez al sistema.</div>";
        html += "<br><div>--</div>";
        html += "SISTEMA DE TRANSICIÓN</div>";
        return html;
    }

    private String bodyIsSinicUser() {
        String html = "";

        html += "<div>Buen día señor Gestor</div><br>";

        html += "<div>El Instituto Geográfico Agustín Codazzi (IGAC) le informa:</div><br>";

        html += "<div>Se ha creado el usuario asignado a usted para que ingrese al sistema de reporte de información\n"
                + "catastral periódica por parte de los gestores catastrales:</div><br>";

        html += "<div>Ingrese a la siguiente URL: <a href='" + this.siteURL + "'>" + siteURL + "</a></div><br>";

        html += "<div>Sus credenciales son las siguientes:</div>";
        html += "<div>USUARIO: " + this.user + "</div>";
        html += "<div>ENTIDAD: " + this.profile + "</div>";
        html += "<div>CONTRASEÑA: " + this.password + "</div><br>";

        html += "<div>Nota Importante: Se solicita al usuario cambiar la contraseña una vez se ingrese por primera vez al sistema.</div>";

        html += "<br><div>Cordialmente,</div>";
        html += "Instituto Geográfico Agustín Codazzi<br />";
        html += "Tel. 3694000<br />";
        html += "Carrera 30 Nº 48-51<br />";
        html += "Sede Central Bogotá<br />";
        html += "<a href='www.igac.gov.co'>www.igac.gov.co</a><br />";

        return html;
    }

}
