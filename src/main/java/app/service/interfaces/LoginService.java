package app.service.interfaces;

import app.dto.PersonDto;
import app.model.Person;

/**
 * Interfaz para el servicio de autenticación.
 * Define métodos para autenticar a un usuario y cerrar la sesión.
 */
public interface LoginService {

    /**
     * Autentica a un usuario utilizando su nombre de usuario y contraseña.
     *
     * @param userName el nombre de usuario.
     * @param password la contraseña.
     * @return Un objeto {@link Person} si la autenticación es exitosa; 
     *         de lo contrario, se retorna null o se lanza una excepción según la implementación.
     */
    PersonDto autenticar(String userName, String password)throws Exception;

    /**
     * Cierra la sesión del usuario autenticado.
     */
    void cerrarSesion();

    PersonDto login(long document, String password) throws Exception;

    void updatePersonPassword(PersonDto person) throws Exception;
}
