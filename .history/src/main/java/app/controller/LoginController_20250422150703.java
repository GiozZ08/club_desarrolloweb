package app.controller;

import app.controller.validator.PersonValidator;
import app.dto.PersonDto;
import app.helpers.Utils;
import app.service.interfaces.LoginService;
import app.service.interfaces.PersonService;
import app.service.LoginServiceImpl; // o tu implementación concreta
// import app.service.LoginServiceImpl; // si usas inyección manual

/**
 * Controlador encargado de gestionar el proceso de inicio de sesión.
 */
public class LoginController implements ControllerInterface {

    private final LoginService loginService;
    private final PersonValidator validator;

    /**
     * Constructor por defecto, inyecta la implementación de LoginService.
     */
    public LoginController() {
        this.loginService = new LoginServiceImpl(null); // asume que Service implementa LoginService
        this.validator    = new PersonValidator();
    }

    @Override
    public void session() throws Exception {
        PersonDto user = null;

        // Bucle de login: hasta que se autentique o decida salir
        while (user == null) {
            user = attemptLogin();
            if (user == null) {
                System.out.println("¿Deseas reintentar? (S/N)");
                String resp = Utils.getReader().nextLine();
                if (!resp.equalsIgnoreCase("S")) {
                    System.out.println("Saliendo del login...");
                    return;
                }
            }
        }

        // Una vez autenticado, despliega el menú general
        System.out.println("Sesión iniciada correctamente.");
        PersonController pc = new PersonController(
            (PersonService) loginService, // o inyecta el PersonService adecuado
            /* ... otros servicios ... */
        );
        pc.session();
    }

    /**
     * Intenta un login y devuelve el DTO del usuario o null si falla.
     */
    private PersonDto attemptLogin() {
        try {
            System.out.println("---- LOGIN ----");
            System.out.print("Documento: ");
            String docStr = Utils.getReader().nextLine();
            long document = validator.validDocument(docStr);

            System.out.print("Contraseña: ");
            String password = Utils.getReader().nextLine();
            if (password.trim().isEmpty()) {
                System.out.println("La contraseña no puede estar vacía.");
                return null;
            }

            PersonDto person = loginService.login(document, password);
            if (person == null) {
                System.out.println("Credenciales incorrectas.");
                return null;
            }
            System.out.println("¡Bienvenido " + person.getName() + "!");
            return person;

        } catch (Exception e) {
            System.out.println("Error durante el login: " + e.getMessage());
            return null;
        }
    }
}
