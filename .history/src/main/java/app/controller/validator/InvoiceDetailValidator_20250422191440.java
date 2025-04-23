package app.controller.validator;

/**
 * Validador específico para los detalles de factura.
 * Extiende {@link CommonsValidator} para reutilizar validaciones básicas.
 */
public class InvoiceDetailValidator extends CommonsValidator {

    /**
     * Valida y convierte el identificador del detalle de factura a long.
     *
     * @param value Cadena con el ID del detalle.
     * @return El valor numérico (long) correspondiente al ID.
     * @throws Exception Si la cadena es inválida o no se puede convertir.
     */
    public long validId(String value) throws Exception {
        return isValidLong("ID del detalle de factura", value);
    }

    /**
     * Valida y convierte el identificador de la factura asociada a long.
     *
     * @param value Cadena con el ID de la factura.
     * @return El valor numérico (long) correspondiente al ID de la factura.
     * @throws Exception Si la cadena es inválida o no se puede convertir.
     */
    public long validInvoiceId(String value) throws Exception {
        return isValidLong("ID de la factura", value);
    }

    /**
     * Valida y convierte el identificador del consumo asociado a long.
     *
     * @param value Cadena con el ID del consumo.
     * @return El valor numérico (long) correspondiente al ID de consumo.
     * @throws Exception Si la cadena es inválida o no se puede convertir.
     */
    public long validConsumoId(String value) throws Exception {
        return isValidLong("ID de consumo", value);
    }

    /**
     * Valida y convierte la cantidad de unidades a int.
     *
     * @param value Cadena con la cantidad.
     * @return El valor numérico (int) correspondiente a la cantidad.
     * @throws Exception Si la cadena es inválida o no se puede convertir.
     */
    public int validCantidad(String value) throws Exception {
        return isValidInteger("Cantidad", value);
    }

    /**
     * Valida y convierte el precio unitario a double.
     *
     * @param value Cadena con el precio unitario.
     * @return El valor numérico (double) correspondiente al precio unitario.
     * @throws Exception Si la cadena es inválida o no se puede convertir.
     */
    public double validPrecioUnitario(String value) throws Exception {
        return isValidDouble("Precio unitario", value);
    }
}
