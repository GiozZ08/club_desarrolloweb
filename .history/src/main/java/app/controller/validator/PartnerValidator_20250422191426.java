package app.controller.validator;

/**
 * Validador específico para la entidad Partner.
 *
 * Extiende {@link PersonValidator} y proporciona validaciones para los campos específicos del socio.
 */
public class PartnerValidator extends PersonValidator {

    /**
     * Valida y convierte el ID de la membresía del socio a un valor long.
     *
     * @param idMembresia Cadena que representa el ID de la membresía.
     * @return El valor long correspondiente al ID de la membresía.
     * @throws Exception Si el ID de la membresía es inválido.
     */
    public long validIdMembresia(String idMembresia) throws Exception {
        return isValidLong("ID de la membresía", idMembresia);
    }
}
