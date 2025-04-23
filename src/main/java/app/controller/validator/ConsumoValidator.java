package app.controller.validator;

import java.util.Date;

/**
 * Validador para datos relacionados con ConsumoDto.
 */
public class ConsumoValidator extends CommonsValidator {

    /**
     * Valida y convierte el ID del consumo.
     *
     * @param value Cadena que representa el ID.
     * @return El valor como long.
     * @throws Exception Si la cadena no es válida o no se puede convertir.
     */
    public long validIdConsumo(String value) throws Exception {
        return isValidLong("ID de consumo", value);
    }

    /**
     * Valida la fecha del consumo.
     *
     * @param value Cadena con la fecha.
     * @return Un objeto Date.
     * @throws Exception Si la fecha es inválida.
     */
    public Date validFechaConsumo(String value) throws Exception {
        return isValidDate("Fecha de consumo", value);
    }

    /**
     * Valida el nombre del producto o servicio consumido.
     *
     * @param value Cadena con el nombre.
     * @return El nombre validado.
     * @throws Exception Si el nombre es inválido (vacío o nulo).
     */
    public String validNombreProducto(String value) throws Exception {
        return isValidString("Nombre del producto o servicio", value);
    }

    /**
     * Valida y convierte el valor unitario.
     *
     * @param value Cadena con el valor.
     * @return El valor como double.
     * @throws Exception Si el valor es inválido.
     */
    public double validValorUnitario(String value) throws Exception {
        return isValidDouble("Valor unitario", value);
    }
}