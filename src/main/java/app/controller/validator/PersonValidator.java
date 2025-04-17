package app.controller.validator;

import java.util.Arrays;
import java.util.List;

/**
 * Validador específico para la entidad Person.
 * <p>
 * Esta clase extiende {@link CommonsValidator} y proporciona métodos para validar campos comunes
 * de una persona, como el nombre, la cédula, el nombre de usuario y el tipo de usuario.
 */
public class PersonValidator extends CommonsValidator {

    private static final List<String> VALID_USER_TYPES = Arrays.asList("ADMIN", "SOCIO", "INVITADO");

    /**
     * Valida que el nombre de la persona no sea nulo, vacío y cumpla con restricciones de formato.
     *
     * @param name Nombre a validar.
     * @throws Exception Si el nombre es nulo, vacío o contiene caracteres inválidos.
     */
    public void validName(String name) throws Exception {
        isValidString("El nombre de la persona", name);
        if (!name.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,50}$")) {
            throw new Exception("El nombre solo puede contener letras y espacios (mínimo 2, máximo 50 caracteres).");
        }
    }

    /**
     * Valida y convierte la cadena de texto que representa la cédula a un valor long.
     *
     * @param document Cadena que representa la cédula.
     * @return El valor numérico (long) correspondiente a la cédula.
     * @throws Exception Si la cadena es vacía, no es numérica o no cumple con la longitud esperada.
     */
    public long validDocument(String document) throws Exception {
        long docNumber = isValidLong("La cédula de la persona", document);
        if (document.length() < 5 || document.length() > 10) {
            throw new Exception("La cédula debe tener entre 5 y 10 dígitos.");
        }
        if (docNumber <= 0) {
            throw new Exception("La cédula debe ser un número positivo.");
        }
        return docNumber;
    }

    /**
     * Valida que el nombre de usuario no sea nulo ni vacío.
     *
     * @param username Nombre de usuario a validar.
     * @throws Exception Si el nombre de usuario es nulo o vacío.
     */
    public void validUsername(String username) throws Exception {
        isValidString("El nombre de usuario", username);
        if (!username.matches("^[a-zA-Z0-9._-]{3,20}$")) {
            throw new Exception("El nombre de usuario debe tener entre 3 y 20 caracteres y solo puede contener letras, números, puntos, guiones y guiones bajos.");
        }
    }

    /**
     * Valida que el tipo de usuario sea válido (ADMIN, SOCIO, INVITADO).
     *
     * @param userType Tipo de usuario a validar.
     * @throws Exception Si el tipo de usuario no es válido.
     */
    public void validUserType(String userType) throws Exception {
        isValidString("El tipo de usuario", userType);
        if (!VALID_USER_TYPES.contains(userType.toUpperCase())) {
            throw new Exception("El tipo de usuario debe ser ADMIN, SOCIO o INVITADO.");
        }
    }
}

