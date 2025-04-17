package app.model;

import java.util.Date;

public class Invoice {
    // Atributos
    private long id; // IDFactura
    private Person socio; // IDSocio (referencia a Persona)
    private Date fechaFactura; // FechaFactura
    private double valorTotal; // ValorTotal
    private String metodoPago; // MetodoPago
    private Date fechaPago; // FechaPago
    private boolean status; // Estado de la factura (opcional, no en el modelo relacional)

    // Constructor sin parámetros
    public Invoice() {
    }

    // Constructor con parámetros
    public Invoice(long id, Person socio, Date fechaFactura, double valorTotal, String metodoPago, Date fechaPago, boolean status) {
        this.id = id;
        this.socio = socio;
        this.fechaFactura = fechaFactura;
        this.valorTotal = valorTotal;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
        this.status = status;
    }

    // Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getSocio() {
        return socio;
    }

    public void setSocio(Person socio) {
        this.socio = socio;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}