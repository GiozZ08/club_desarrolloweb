package app.controller.validator;

/**
 * Validador específico para la entidad Partner.
 *
 * Extiende {@link CommonsValidator} y proporciona validaciones para los datos específicos de un socio.
 */
public class PartnerValidator extends PersonValidator {

    /**
     * Valida y convierte el ID de la membresía del socio a un valor entero.
     *
     * @param  idMembresia ID de la membresía.
     * @return El valor numérico correspondiente al ID de la membresía.
     * @throws Exception Si el ID de la membresía es inválido.
     */
    public int validIdMembresia(String idMembresia) throws Exception {
        return isValidInteger("El ID de la membresía",  idMembresia);
    }

    /**
     * Valida que el nivel de acceso del socio sea un valor válido.
     *
     * @param accessLevel Nivel de acceso del socio.
     * @throws Exception Si el nivel de acceso es nulo o vacío.
     */
    public void validAccessLevel(String accessLevel) throws Exception {
        isValidString("El nivel de acceso del socio", accessLevel);
    }

    /**
     * Valida que el departamento del socio no sea nulo ni vacío.
     *
     * @param department Departamento del socio.
     * @throws Exception Si el departamento es inválido.
     */
    public void validDepartment(String department) throws Exception {
        isValidString("El departamento del socio", department);
    }
}
