package app.dto;

/**
 * DTO que representa un detalle de factura.
 *
 * Este objeto se usa para transferir datos relacionados con los detalles de una factura entre las capas de la aplicación.
 * Contiene información sobre la factura asociada, el consumo relacionado, la cantidad, el precio unitario y el subtotal.
 */
public class InvoiceDetailDto {
    private long id;
    private InvoiceDto invoice; // Representa la factura a la que pertenece el detalle
    private ConsumoDto consumo; // Representa el consumo asociado al detalle
    private int cantidad;
    private double precioUnitario;
    private double subtotal; // Se calcula automáticamente

    /**
     * Constructor vacío necesario para frameworks y deserialización.
     */
    public InvoiceDetailDto() {}

    /**
     * Constructor completo para inicializar todas las propiedades del DTO.
     *
     * @param id            Identificador del detalle de factura.
     * @param invoice       DTO de la factura asociada.
     * @param consumo       DTO del consumo asociado.
     * @param cantidad      Cantidad del producto/servicio consumido.
     * @param precioUnitario Precio unitario del producto/servicio.
     */
    public InvoiceDetailDto(long id, InvoiceDto invoice, ConsumoDto consumo, int cantidad, double precioUnitario) {
        this.id = id;
        this.invoice = invoice;
        this.consumo = consumo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario; // Calcula el subtotal automáticamente
    }

    // Getters y Setters

    /**
     * Obtiene el identificador del detalle de factura.
     *
     * @return El id del detalle de factura.
     */
    public long getId() {
        return id;
    }

    /**
     * Establece el identificador del detalle de factura.
     *
     * @param id El id a asignar.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene la factura asociada al detalle.
     *
     * @return La factura en forma de DTO.
     */
    public InvoiceDto getInvoice() {
        return invoice;
    }

    /**
     * Establece la factura asociada al detalle.
     *
     * @param invoice La factura a asignar en forma de DTO.
     */
    public void setInvoice(InvoiceDto invoice) {
        this.invoice = invoice;
    }

    /**
     * Obtiene el consumo asociado al detalle.
     *
     * @return El consumo en forma de DTO.
     */
    public ConsumoDto getConsumo() {
        return consumo;
    }

    /**
     * Establece el consumo asociado al detalle.
     *
     * @param consumo El consumo a asignar en forma de DTO.
     */
    public void setConsumo(ConsumoDto consumo) {
        this.consumo = consumo;
    }

    /**
     * Obtiene la cantidad de productos/servicios consumidos.
     *
     * @return La cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos/servicios consumidos.
     * También actualiza el subtotal automáticamente.
     *
     * @param cantidad La cantidad a asignar.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = cantidad * this.precioUnitario; // Recalcula el subtotal
    }

    /**
     * Obtiene el precio unitario del producto/servicio.
     *
     * @return El precio unitario.
     */
    public double getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto/servicio.
     * También actualiza el subtotal automáticamente.
     *
     * @param precioUnitario El precio unitario a asignar.
     */
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = this.cantidad * precioUnitario; // Recalcula el subtotal
    }

    /**
     * Obtiene el subtotal calculado.
     *
     * @return El subtotal.
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Proporciona una representación en cadena del objeto InvoiceDetailDto.
     *
     * @return Una cadena con la información del detalle de factura.
     */
    @Override
    public String toString() {
        return "InvoiceDetailDto{" +
                "id=" + id +
                ", invoice=" + invoice +
                ", consumo=" + consumo +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", subtotal=" + subtotal +
                '}';
    }
}
