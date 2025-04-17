//pertenece al paquete modelo, esto quiere decir
//que es parte de la capa del modelo de la aplicación 
//esta capa normalmente es usada para representar
//las entidades de negocio o los datos que son almacenados
//en la base de datos.
package app.model;

import java.util.Date;

public class Guest extends Person {
    // Atributos
    private String nombre;
    private String apellido;
    private Date fechaInvitacion;
    private Date fechaRegistro;
    private Partner referidoPor; // Socio que invitó al invitado
    private boolean status; // Estado del invitado (activo/inactivo)
  

    // Constructor sin parámetros
    public Guest() {
    }

    // Constructor con parámetros
    public Guest(long id, String nombre, String apellido, Date fechaInvitacion, Date fechaRegistro, Partner referidoPor) {
      
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaInvitacion = fechaInvitacion;
        this.fechaRegistro = fechaRegistro;
        this.referidoPor = referidoPor;
  
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaInvitacion() {
        return fechaInvitacion;
    }

    public void setFechaInvitacion(Date fechaInvitacion) {
        this.fechaInvitacion = fechaInvitacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Partner getReferidoPor() {
        return referidoPor;
    }

    public void setReferidoPor(Partner referidoPor) {
        this.referidoPor = referidoPor;
    }

    /**
     * Indica el estado del invitado.
     *
     * @return {@code true} si el invitado está activo, {@code false} en caso contrario.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Establece el estado del invitado.
     *
     * @param status El estado a asignar.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }


 
}