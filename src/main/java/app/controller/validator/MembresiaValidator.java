package app.controller.validator;

/**
 * Validador para datos relacionados con MembresiaDto.
 */
public class MembresiaValidator extends CommonsValidator {

    /**
     * Valida y convierte el ID de la membresía.
     *
     * @param value Cadena que representa el ID.
     * @return El valor como long.
     * @throws Exception Si la cadena no es válida o no se puede convertir.
     */
    public long validIdMembresia(String value) throws Exception {
        return isValidLong("ID de membresía", value);
    }

    /**
     * Valida el estado de la membresía.
     *
     * @param value Estado en forma de cadena.
     * @return Estado validado.
     * @throws Exception Si es nulo o vacío.
     */
    public String validEstado(String value) throws Exception {
        return isValidString("Estado de la membresía", value);
    }

    /**
     * Valida y convierte el fondo actual.
     *
     * @param value Fondo actual en forma de cadena.
     * @return Valor double validado.
     * @throws Exception Si es inválido.
     */
    public double validFondoActual(String value) throws Exception {
        return isValidDouble("Fondo actual", value);
    }

    /**
     * Valida y convierte el límite de fondo.
     *
     * @param value Límite de fondo en forma de cadena.
     * @return Valor double validado.
     * @throws Exception Si es inválido.
     */
    public double validLimiteFondo(String value) throws Exception {
        return isValidDouble("Límite de fondo", value);
    }

    /**
     * Valida el tipo de membresía.
     *
     * @param value Tipo en forma de cadena.
     * @return Tipo validado.
     * @throws Exception Si es nulo o vacío.
     */
    public String validTipoMembresia(String value) throws Exception {
        return isValidString("Tipo de membresía", value);
    }
}