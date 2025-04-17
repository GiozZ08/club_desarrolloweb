package app.controller.validator;

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
    public void isValidString(String fieldName, String value) throws Exception {
        if (value == null || value.trim().isEmpty()) {
            throw new Exception(fieldName + " no puede ser un valor vacío");
        }
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
}
