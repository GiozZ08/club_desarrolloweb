package app.controller;

import app.controller.validator.PersonValidator;
import app.dto.PersonDto;
import app.helpers.Utils;
import app.service.Service;
import app.service.interfaces.PersonService;

/**
 * Controlador encargado de gestionar el proceso de inicio de sesión.
 *
 * Este controlador solicita las credenciales (por ejemplo, documento y contraseña) y utiliza la capa Service
 * para autenticar al usuario. Dependiendo del resultado, muestra un mensaje de bienvenida o de error.
 */
public class LoginController implements ControllerInterface {

    private final PersonService service;
    private final PersonValidator validator;

    /**
     * Constructor que inicializa el servicio y el validador.
     */
    public LoginController() {
        // Se asume que Service implementa PersonService
        this.service = new Service();
        this.validator = new PersonValidator();
    }

    /**
     * Método principal que inicia el flujo de login.
     *
     * @throws Exception en caso de error.
     */
    @Override
    public void session() throws Exception {
        boolean active = true;
        while (active) {
            active = showMenu();
        }
    }

    /**
     * Muestra el menú de login y procesa la entrada del usuario.
     *
     * @return false para salir del proceso de login.
     */
    private boolean showMenu() {
        try {
            System.out.println("---- LOGIN ----");
            System.out.print("Ingrese su documento: ");
            String docStr = Utils.getReader().nextLine();
            long document = validator.validDocument(docStr);

            System.out.print("Ingrese su contraseña: ");
            String password = Utils.getReader().nextLine();
            
            // Opcional: Validar que la contraseña no esté vacía.
            if (password == null || password.trim().isEmpty()) {
                System.out.println("La contraseña no puede ser vacía.");
                return true; // Permite reintentar.
            }

            // Se asume que el método login del servicio recibe documento y contraseña,
            // y devuelve un PersonDto si la autenticación es exitosa.
            PersonDto person = service.login(document, password);
            if (person != null) {
                System.out.println("Bienvenido " + person.getName() + " - Rol: " + person.getUserType());
            } else {
                System.out.println("Credenciales incorrectas. Intente nuevamente.");
            }
        } catch (Exception e) {
            System.out.println("Error en el login: " + e.getMessage());
        }
        // Finaliza el proceso de login; si deseas permitir reintentos, podrías retornar true.
        return false;
    }
}
