package app.model;

public class InvoiceDetail {
    // Atributos
    private long id; // IDDetalleFactura (PK)
    private Invoice invoice; // IDFactura (FK que referencia a Factura)
    private Consumo consumo; // IDConsumo (FK que referencia a Consumo)
    private int cantidad; // Cantidad
    private double precioUnitario; // PrecioUnitario
    private double subtotal; // Subtotal (calculado como cantidad * precioUnitario)

    // Constructor sin parámetros
    public InvoiceDetail() {
    }

    // Constructor con parámetros
    public InvoiceDetail(long id, Invoice invoice, Consumo consumo, int cantidad, double precioUnitario) {
        this.id = id;
        this.invoice = invoice;
        this.consumo = consumo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario; // Calcula el subtotal automáticamente
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Consumo getConsumo() {
        return consumo;
    }

    public void setConsumo(Consumo consumo) {
        this.consumo = consumo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = cantidad * precioUnitario; // Actualiza el subtotal al cambiar la cantidad
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal = cantidad * precioUnitario; // Actualiza el subtotal al cambiar el precio unitario
    }

    public double getSubtotal() {
        return subtotal;
    }

    // No hay setter para subtotal porque se calcula automáticamente
}