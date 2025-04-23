package app.controller.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Validador específico para la entidad Invoice (factura).
 * Extiende {@link CommonsValidator} para reutilizar validaciones básicas.
 */
public class InvoiceValidator extends CommonsValidator {
    // Formato de fecha esperado: "yyyy-MM-dd"
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * Valida y convierte el ID de la factura a long.
     */
    public long validId(String value) throws Exception {
        return isValidLong("ID de factura", value);
    }

    /**
     * Valida y convierte el documento del socio (PersonDto.document) a long.
     */
    public long validSocioDocument(String value) throws Exception {
        return isValidLong("Documento de socio", value);
    }

    /**
     * Valida y convierte la fecha de factura a {@link Date}.
     */
    public Date validFechaFactura(String value) throws Exception {
        isValidString("Fecha de factura", value);
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(value);
        } catch (ParseException e) {
            throw new Exception("Fecha de factura debe tener formato " + DATE_PATTERN);
        }
    }

    /**
     * Valida y convierte el valor total de la factura a double.
     */
    public double validValorTotal(String value) throws Exception {
        return isValidDouble("Valor total", value);
    }

    /**
     * Valida el método de pago (no puede ser vacío).
     */
    public void validMetodoPago(String value) throws Exception {
        isValidString("Método de pago", value);
    }

    /**
     * Valida y convierte la fecha de pago a {@link Date}.
     * Es opcional: si está vacío devuelve null.
     */
    public Date validFechaPago(String value) throws Exception {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(value);
        } catch (ParseException e) {
            throw new Exception("Fecha de pago debe tener formato " + DATE_PATTERN);
        }
    }

    /**
     * Valida el estado de la factura (true/false).
     */
    public boolean validStatus(String value) throws Exception {
        isValidString("Estado de la factura", value);
        if ("true".equalsIgnoreCase(value) || "false".equalsIgnoreCase(value)) {
            return Boolean.parseBoolean(value);
        }
        throw new Exception("Estado de la factura debe ser 'true' o 'false'");
    }
}