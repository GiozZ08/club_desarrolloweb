package app.dto;

import java.util.Date;

/**
 * DTO que representa un consumo.
 *
 * Este objeto se utiliza para transferir datos relacionados con un consumo (por ejemplo, un producto o servicio adquirido)
 * entre las diferentes capas de la aplicación, sin exponer la lógica de negocio.
 */
public class ConsumoDto {
    private long idConsumo;       // Identificador del consumo (clave primaria)
    private Date fechaConsumo;    // Fecha en que se realizó el consumo
    private String nombreProducto; // Nombre del producto o servicio consumido
    private double valorUnitario; // Valor unitario del producto o servicio

    /**
     * Constructor vacío necesario para frameworks y deserialización.
     */
    public ConsumoDto() {}

    /**
     * Constructor completo para inicializar todas las propiedades del DTO.
     *
     * @param idConsumo       Identificador del consumo.
     * @param fechaConsumo    Fecha del consumo.
     * @param nombreProducto  Nombre del producto o servicio.
     * @param valorUnitario   Valor unitario del producto o servicio.
     */
    public ConsumoDto(long idConsumo, Date fechaConsumo, String nombreProducto, double valorUnitario) {
        this.idConsumo = idConsumo;
        this.fechaConsumo = fechaConsumo;
        this.nombreProducto = nombreProducto;
        this.valorUnitario = valorUnitario;
    }

    /**
     * Obtiene el identificador del consumo.
     *
     * @return El id del consumo.
     */
    public long getIdConsumo() {
        return idConsumo;
    }

    /**
     * Establece el identificador del consumo.
     *
     * @param idConsumo El id a asignar.
     */
    public void setIdConsumo(long idConsumo) {
        this.idConsumo = idConsumo;
    }

    /**
     * Obtiene la fecha en que se realizó el consumo.
     *
     * @return La fecha del consumo.
     */
    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    /**
     * Establece la fecha del consumo.
     *
     * @param fechaConsumo La fecha a asignar.
     */
    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    /**
     * Obtiene el nombre del producto o servicio consumido.
     *
     * @return El nombre del producto o servicio.
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * Establece el nombre del producto o servicio consumido.
     *
     * @param nombreProducto El nombre a asignar.
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    /**
     * Obtiene el valor unitario del producto o servicio.
     *
     * @return El valor unitario.
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * Establece el valor unitario del producto o servicio.
     *
     * @param valorUnitario El valor a asignar.
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * Proporciona una representación en cadena del objeto ConsumoDto.
     *
     * @return Una cadena con la información del consumo.
     */
    @Override
    public String toString() {
        return "ConsumoDto{" +
                "idConsumo=" + idConsumo +
                ", fechaConsumo=" + fechaConsumo +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}
