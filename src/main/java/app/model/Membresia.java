 package app.model;

/**
 * Clase que representa la entidad Membresia en la aplicación.
 *
 * Esta entidad contiene información sobre el estado de la membresía, el fondo actual,
 * el límite de fondo y el tipo de membresía (por ejemplo, Premium, Básica, etc.).
 */
public class Membresia {
    private long idMembresia;   // Identificador de la membresía (PK)
    private String estado;      // Estado de la membresía (por ejemplo, activa, inactiva)
    private double fondoActual; // Fondo actual disponible
    private double limiteFondo; // Límite de fondo permitido
    private String tipoMembresia; // Tipo de membresía (por ejemplo, Premium, Básica)

    /**
     * Constructor sin parámetros.
     */
    public Membresia() {
    }

    /**
     * Constructor con parámetros para inicializar todas las propiedades.
     *
     * @param idMembresia   Identificador de la membresía.
     * @param estado        Estado de la membresía.
     * @param fondoActual   Fondo actual disponible.
     * @param limiteFondo   Límite de fondo permitido.
     * @param tipoMembresia Tipo de membresía.
     */
    public Membresia(long idMembresia, String estado, double fondoActual, double limiteFondo, String tipoMembresia) {
        this.idMembresia = idMembresia;
        this.estado = estado;
        this.fondoActual = fondoActual;
        this.limiteFondo = limiteFondo;
        this.tipoMembresia = tipoMembresia;
    }

    // Getters y Setters

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
        return "Membresia{" +
                "idMembresia=" + idMembresia +
                ", estado='" + estado + '\'' +
                ", fondoActual=" + fondoActual +
                ", limiteFondo=" + limiteFondo +
                ", tipoMembresia='" + tipoMembresia + '\'' +
                '}';
    }
}


