package app.dto;

import java.util.Date;

/**
 * DTO que representa una factura.
 *
 * Este objeto se usa para transferir datos relacionados con una factura entre las capas de la aplicación.
 * Contiene información del socio asociado, fechas relevantes, método de pago y estado de la factura.
 */
public class InvoiceDto {
    private long id;
    private PersonDto socio; // Representa el socio asociado a la factura
    private Date fechaFactura;
    private double valorTotal;
    private String metodoPago;
    private Date fechaPago;
    private boolean status;

    /**
     * Constructor vacío necesario para frameworks y deserialización.
     */
    public InvoiceDto() {}

    /**
     * Constructor completo para inicializar todas las propiedades del DTO.
     *
     * @param id           Identificador de la factura.
     * @param socio        DTO de la persona asociada a la factura.
     * @param fechaFactura Fecha en la que se generó la factura.
     * @param valorTotal   Monto total de la factura.
     * @param metodoPago   Método de pago utilizado.
     * @param fechaPago    Fecha en la que se realizó el pago.
     * @param status       Estado de la factura (pagada/no pagada).
     */
    public InvoiceDto(long id, PersonDto socio, Date fechaFactura, double valorTotal, String metodoPago, Date fechaPago, boolean status) {
        this.id = id;
        this.socio = socio;
        this.fechaFactura = fechaFactura;
        this.valorTotal = valorTotal;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.status = status;
    }

    // Getters y Setters

    /**
     * Obtiene el identificador de la factura.
     *
     * @return El id de la factura.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador de la factura.
     *
     * @param id El id a asignar.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el socio asociado a la factura.
     *
     * @return El socio en forma de DTO.
     */
    public PersonDto getSocio() {
        return socio;
    }

    /**
     * Establece el socio asociado a la factura.
     *
     * @param socio El socio a asignar en forma de DTO.
     */
    public void setSocio(PersonDto socio) {
        this.socio = socio;
    }

    /**
     * Obtiene la fecha de emisión de la factura.
     *
     * @return La fecha de la factura.
     */
    public Date getFechaFactura() {
        return fechaFactura;
    }

    /**
     * Establece la fecha de emisión de la factura.
     *
     * @param fechaFactura La fecha a asignar.
     */
    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    /**
     * Obtiene el valor total de la factura.
     *
     * @return El valor total.
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * Establece el valor total de la factura.
     *
     * @param valorTotal El valor total a asignar.
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * Obtiene el método de pago utilizado.
     *
     * @return El método de pago.
     */
    public String getMetodoPago() {
        return metodoPago;
    }

    /**
     * Establece el método de pago utilizado.
     *
     * @param metodoPago El método de pago a asignar.
     */
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    /**
     * Obtiene la fecha en la que se realizó el pago.
     *
     * @return La fecha de pago.
     */
    public Date getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece la fecha en la que se realizó el pago.
     *
     * @param fechaPago La fecha de pago a asignar.
     */
    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Indica el estado de la factura.
     *
     * @return {@code true} si la factura está pagada, {@code false} en caso contrario.
     */
    public boolean isStatus() {
        return status;
    }

    /**
     * Establece el estado de la factura.
     *
     * @param status El estado a asignar.
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * Proporciona una representación en cadena del objeto InvoiceDto.
     *
     * @return Una cadena con la información de la factura.
     */
    @Override
    public String toString() {
        return "InvoiceDto{" +
                "id=" + id +
                ", socio=" + socio +
                ", fechaFactura=" + fechaFactura +
                ", valorTotal=" + valorTotal +
                ", metodoPago='" + metodoPago + '\'' +
                ", fechaPago=" + fechaPago +
                ", status=" + status +
                '}';
    }
}
