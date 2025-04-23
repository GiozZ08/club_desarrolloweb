package app.controller.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase abstracta que provee métodos comunes para validar entradas de datos.
 *
 * Esta clase incluye métodos para:
 * - Validar que una cadena no sea nula ni vacía.
 * - Convertir cadenas a tipos numéricos (int, long y double) con validación,
 *   lanzando excepciones descriptivas en caso de error.
 */
public abstract class CommonsValidator {

    /**
     * Valida que el valor proporcionado no sea nulo ni una cadena vacía.
     *
     * @param fieldName Nombre descriptivo del campo a validar.
     * @param value     Valor que se desea validar.
     * @throws Exception si el valor es nulo o una cadena vacía.
     */
    public String isValidString(String fieldName, String value) throws Exception {
        if (value == null || value.trim().isEmpty()) {
            throw new Exception(fieldName + " no puede ser un valor vacío");
        }
        return value.trim();
    }

    /**
     * Valida y convierte una cadena a un entero.
     *
     * @param fieldName Nombre descriptivo del campo a validar.
     * @param value     Cadena que se desea validar y convertir.
     * @return El valor entero resultante.
     * @throws Exception si el valor es inválido o no se puede convertir a entero.
     */
    public int isValidInteger(String fieldName, String value) throws Exception {
        isValidString(fieldName, value);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new Exception(fieldName + " debe ser un valor entero válido");
        }
    }

    /**
     * Valida y convierte una cadena a un valor long.
     *
     * @param fieldName Nombre descriptivo del campo a validar.
     * @param value     Cadena que se desea validar y convertir.
     * @return El valor long resultante.
     * @throws Exception si el valor es inválido o no se puede convertir a long.
     */
    public long isValidLong(String fieldName, String value) throws Exception {
        isValidString(fieldName, value);
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new Exception(fieldName + " debe ser un valor numérico válido");
        }
    }

    /**
     * Valida y convierte una cadena a un valor double.
     *
     * @param fieldName Nombre descriptivo del campo a validar.
     * @param value     Cadena que se desea validar y convertir.
     * @return El valor double resultante.
     * @throws Exception si el valor es inválido o no se puede convertir a double.
     */
    public double isValidDouble(String fieldName, String value) throws Exception {
        isValidString(fieldName, value);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new Exception(fieldName + " debe ser un valor decimal válido");
        }
    }
    /**
     * Valida y convierte una cadena a un objeto Date.
     *
     * @param fieldName Nombre del campo a validar.
     * @param value     Cadena que representa la fecha.
     * @return Objeto Date resultante.
     * @throws Exception si el valor es nulo, vacío o tiene formato incorrecto.
     */

    public Date isValidDate(String fieldName, String value) throws Exception {
        isValidString(fieldName, value);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Puedes cambiar el formato si usás otro
        sdf.setLenient(false); // Para que no acepte fechas como 2023-02-30
        try {
            return sdf.parse(value.trim());
        } catch (ParseException e) {
            throw new Exception(fieldName + " debe tener el formato yyyy-MM-dd");
        }

}
}