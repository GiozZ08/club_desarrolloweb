package app.model;
import java.util.Date;

public class Consumo {
    // Atributos
    private long idConsumo; // IDConsumo (PK)
    private Date fechaConsumo; // FechaConsumo
    private String nombreProducto; // NombreProducto
    private double valorUnitario; // ValorUnitario

    // Constructor sin parámetros
    public Consumo() {
    }

    // Constructor con parámetros
    public Consumo(long idConsumo, Date fechaConsumo, String nombreProducto, double valorUnitario) {
        this.idConsumo = idConsumo;
        this.fechaConsumo = fechaConsumo;
        this.nombreProducto = nombreProducto;
        this.valorUnitario = valorUnitario;
    }

    // Getters y Setters
    public long getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(long idConsumo) {
        this.idConsumo = idConsumo;
    }

    public Date getFechaConsumo() {
        return fechaConsumo;
    }

    public void setFechaConsumo(Date fechaConsumo) {
        this.fechaConsumo = fechaConsumo;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}