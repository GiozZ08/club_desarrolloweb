package app.dto;

import java.util.Date;

/**
 * DTO que representa a un invitado.
 *
 * Este objeto se utiliza para transferir datos relacionados con un invitado entre las diferentes capas
 * de la aplicación, reflejando la estructura de la entidad {@code Guest} del modelo. Incluye información
 * personal, fechas de invitación y registro, así como el socio que lo refirió y el estado del invitado.
 */
public class GuestDto extends PersonDto {
    private long id;
    private String nombre;
    private String apellido;
    private Date fechaInvitacion;
    private Date fechaRegistro;
    private PartnerDto referidoPor; // Representa el socio que invitó al invitado
    private boolean status;

    /**
     * Constructor vacío necesario para frameworks y deserialización.
     */
    public GuestDto() {}

    /**
     * Constructor completo para inicializar todas las propiedades del DTO.
     *
     * @param id              Identificador del invitado.
     * @param nombre          Nombre del invitado.
     * @param apellido        Apellido del invitado.
     * @param fechaInvitacion Fecha en la que se realizó la invitación.
     * @param fechaRegistro   Fecha en la que se registró el invitado.
     * @param referidoPor     DTO del socio que refirió al invitado.
     * @param status          Estado del invitado (activo/inactivo).
     */
    public GuestDto(long id, String nombre, String apellido, Date fechaInvitacion, Date fechaRegistro, PartnerDto referidoPor, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaInvitacion = fechaInvitacion;
        this.fechaRegistro = fechaRegistro;
        this.referidoPor = referidoPor;
        this.status = status;
    }

    // Getters y Setters



    /**
     * Obtiene el nombre del invitado.
     *
     * @return El nombre del invitado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del invitado.
     *
     * @param nombre El nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del invitado.
     *
     * @return El apellido del invitado.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del invitado.
     *
     * @param apellido El apellido a asignar.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene la fecha de invitación del invitado.
     *
     * @return La fecha de invitación.
     */
    public Date getFechaInvitacion() {
        return fechaInvitacion;
    }

    /**
     * Establece la fecha de invitación del invitado.
     *
     * @param fechaInvitacion La fecha a asignar.
     */
    public void setFechaInvitacion(Date fechaInvitacion) {
        this.fechaInvitacion = fechaInvitacion;
    }

    /**
     * Obtiene la fecha de registro del invitado.
     *
     * @return La fecha de registro.
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del invitado.
     *
     * @param fechaRegistro La fecha a asignar.
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene el DTO del socio que refirió al invitado.
     *
     * @return El PartnerDto asociado.
     */
    public PartnerDto getReferidoPor() {
        return referidoPor;
    }

    /**
     * Establece el DTO del socio que refirió al invitado.
     *
     * @param referidoPor El PartnerDto a asignar.
     */
    public void setReferidoPor(PartnerDto referidoPor) {
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



    /**
     * Proporciona una representación en cadena del objeto GuestDto.
     *
     * @return Una cadena con la información del invitado.
     */
    @Override
    public String toString() {
        return "GuestDto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaInvitacion=" + fechaInvitacion +
                ", fechaRegistro=" + fechaRegistro +
                ", referidoPor=" + referidoPor +
                ", status=" + status +
                '}';
    }

    public String getEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }
}
