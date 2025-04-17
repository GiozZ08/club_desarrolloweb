package app.dto;

/**
 * DTO que representa una membresía.
 *
 * Este objeto se utiliza para transferir datos relacionados con una membresía entre las capas de la aplicación,
 * sin incluir lógica de negocio. Contiene información sobre el estado, el fondo actual, el límite de fondo y el tipo de membresía.
 */
public class MembresiaDto {
    private long idMembresia;
    private String estado;
    private double fondoActual;
    private double limiteFondo;
    private String tipoMembresia;

    /**
     * Constructor vacío necesario para frameworks y deserialización.
     */
    public MembresiaDto() {}

    /**
     * Constructor completo para inicializar todas las propiedades del DTO.
     *
     * @param idMembresia   Identificador de la membresía.
     * @param estado        Estado de la membresía (ej: activa, inactiva).
     * @param fondoActual   Fondo actual disponible.
     * @param limiteFondo   Límite de fondo permitido.
     * @param tipoMembresia Tipo de membresía (ej: Premium, Básica).
     */
    public MembresiaDto(long idMembresia, String estado, double fondoActual, double limiteFondo, String tipoMembresia) {
        this.idMembresia = idMembresia;
        this.estado = estado;
        this.fondoActual = fondoActual;
        this.limiteFondo = limiteFondo;
        this.tipoMembresia = tipoMembresia;
    }

    public long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(long idMembresia) {
        this.idMembresia = idMembresia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getFondoActual() {
        return fondoActual;
    }

    public void setFondoActual(double fondoActual) {
        this.fondoActual = fondoActual;
    }

    public double getLimiteFondo() {
        return limiteFondo;
    }

    public void setLimiteFondo(double limiteFondo) {
        this.limiteFondo = limiteFondo;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    @Override
    public String toString() {
        return "MembresiaDto{" +
                "idMembresia=" + idMembresia +
                ", estado='" + estado + '\'' +
                ", fondoActual=" + fondoActual +
                ", limiteFondo=" + limiteFondo +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                '}';
    }
}

