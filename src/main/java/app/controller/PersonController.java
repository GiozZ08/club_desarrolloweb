package app.controller;

import app.controller.validator.PersonValidator;
import app.controller.validator.PartnerValidator;
import app.dto.PersonDto;
import app.dto.PartnerDto;
import app.dto.InvoiceDto;
import app.dto.MembresiaDto;
import app.service.interfaces.PersonService;
import app.service.interfaces.InvoiceService;
import app.service.interfaces.MembresiaService;
import app.helpers.Utils;
import java.util.List;

/**
 * Controlador unificado para la gestión de usuarios (Person).
 * Maneja operaciones de login, registro y administración de usuarios,
 * diferenciando roles mediante el atributo userType.
 */
public class PersonController implements ControllerInterface {

    private final PersonService personService;
    private final InvoiceService invoiceService;
    private final MembresiaService membresiaService;
    private final PersonValidator validator;
    private PersonDto loggedInUser; // Usuario autenticado

    /**
     * Constructor con inyección de dependencias.
     */
    public PersonController(PersonService personService, InvoiceService invoiceService, MembresiaService membresiaService) {
        this.personService = personService;
        this.invoiceService = invoiceService;
        this.membresiaService = membresiaService;
        this.validator = new PersonValidator();
    }

    @Override
    public void session() throws Exception {
        boolean active = true;
        while (active) {
            active = showMenu();
        }
    }

    private boolean showMenu() {
        try {
            System.out.println("==== MENÚ ====");
            System.out.println("1. Ingresar");
            System.out.println("2. Registrar");
            System.out.println("3. Salir");
            String option = Utils.getReader().nextLine();

            switch (option) {
                case "1":
                    login();
                    if (isAdmin()) {
                        adminMenu();
                    }
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    System.out.println("Cerrando sesión...");
                    return false;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return true;
    }

    private void login() throws Exception {
        System.out.println("Ingrese su documento:");
        String docStr = Utils.getReader().nextLine();
        long document = validator.validDocument(docStr);
        loggedInUser = personService.findByDocument(new PersonDto(0, document, null, null, null));

        if (loggedInUser == null) {
            System.out.println("Usuario no encontrado.");
        } else {
            System.out.println("Bienvenido " + loggedInUser.getName() + " - Rol: " + loggedInUser.getUserType());
        }
    }

    /**
     * Realiza el registro de un nuevo usuario.
     */
    private void register() throws Exception {
        PersonDto newPerson = readPersonData();
        // Si el usuario es SOCIO, se asume que el DTO es de tipo PartnerDto.
        if (newPerson instanceof PartnerDto) {
            personService.createPartner((PartnerDto) newPerson);
        } else {
            personService.createPerson(newPerson);
        }
        System.out.println("Registro exitoso!");
    }

    /**
     * Lee los datos de un usuario desde la entrada estándar.
     * Si el rol es SOCIO, pide datos adicionales específicos.
     */
    private PersonDto readPersonData() throws Exception {
        System.out.println("Ingrese su nombre:");
        String name = Utils.getReader().nextLine();
        validator.validName(name);

        System.out.println("Ingrese su documento:");
        String docStr = Utils.getReader().nextLine();
        long document = validator.validDocument(docStr);

        System.out.println("Ingrese su nombre de usuario:");
        String username = Utils.getReader().nextLine();

        System.out.println("Ingrese su rol (ADMIN, SOCIO, INVITADO):");
        String role = Utils.getReader().nextLine();

        // Si el rol es SOCIO, se solicitan datos adicionales y se utiliza PartnerValidator.
        if ("SOCIO".equalsIgnoreCase(role)) {
            PartnerValidator partnerValidator = new PartnerValidator();
            System.out.println("Ingrese el ID de la membresía:");
            String membresiaStr = Utils.getReader().nextLine();
            int idMembresia = partnerValidator.validIdMembresia(membresiaStr);

            System.out.println("Ingrese el nivel de acceso:");
            String accessLevel = Utils.getReader().nextLine();
            partnerValidator.validAccessLevel(accessLevel);

            System.out.println("Ingrese el departamento:");
            String department = Utils.getReader().nextLine();
            partnerValidator.validDepartment(department);

            // Crear un PartnerDto (asumiendo que PartnerDto extiende PersonDto y tiene estos campos)
            PartnerDto partner = new PartnerDto();
            partner.setName(name);
            partner.setDocument(document);
            partner.setUserName(username);
            partner.setUserType(role);
            // Se asume que PartnerDto tiene setters para estos campos adicionales:
            partner.setIdMembresia(idMembresia);
            partner.setAccessLevel(accessLevel);
            partner.setDepartment(department);

            return partner;
        } else {
            // Para ADMIN o INVITADO, se crea un PersonDto normal.
            PersonDto person = new PersonDto();
            person.setName(name);
            person.setDocument(document);
            person.setUserName(username);
            person.setUserType(role);
            return person;
        }
    }

    // --- Métodos administrativos ya existentes en PersonController ---
    private void adminMenu() {
        boolean active = true;
        while (active) {
            System.out.println("\n==== MENÚ ADMINISTRADOR ====");
            System.out.println("1. Listar usuarios");
            System.out.println("2. Crear usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Ver facturas de un usuario");
            System.out.println("6. Gestionar membresías");
            System.out.println("7. Restablecer contraseña");
            System.out.println("8. Habilitar/Deshabilitar usuario");
            System.out.println("9. Generar reportes");
            System.out.println("10. Notificar a usuarios");
            System.out.println("11. Cerrar sesión");
            String option = Utils.getReader().nextLine();

            try {
                switch (option) {
                    case "1":
                        listUsersByType();
                        break;
                    case "2":
                        register();
                        break;
                    case "3":
                        updateUser();
                        break;
                    case "4":
                        deleteUser();
                        break;
                    case "5":
                        listInvoices();
                        break;
                    case "6":
                        manageMembership();
                        break;
                    case "7":
                        resetPassword();
                        break;
                    case "8":
                        toggleUserStatus();
                        break;
                    case "9":
                        generateReports();
                        break;
                    case "10":
                        sendNotification();
                        break;
                    case "11":
                        System.out.println("Cerrando sesión de administrador...");
                        active = false;
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private boolean isAdmin() {
        return loggedInUser != null && "ADMIN".equalsIgnoreCase(loggedInUser.getUserType());
    }

    private void listUsersByType() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el tipo de usuario a listar (ADMIN, SOCIO, INVITADO):");
        String userType = Utils.getReader().nextLine();
        List<PersonDto> users = personService.findByUserType(userType);
        users.forEach(System.out::println);
    }

    private void updateUser() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el documento del usuario a modificar:");
        long document = validator.validDocument(Utils.getReader().nextLine());

        PersonDto updatedPerson = readPersonData();
        updatedPerson.setDocument(document);

        personService.updatePerson(updatedPerson);
        System.out.println("Usuario actualizado.");
    }

    private void deleteUser() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el documento del usuario a eliminar:");
        long document = validator.validDocument(Utils.getReader().nextLine());
        personService.deletePerson(document);
        System.out.println("Usuario eliminado.");
    }

    private void listInvoices() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el documento del usuario:");
        long document = validator.validDocument(Utils.getReader().nextLine());
        List<InvoiceDto> invoices = invoiceService.findByUser(document);
        invoices.forEach(System.out::println);
    }

    private void manageMembership() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el documento del usuario:");
        long document = validator.validDocument(Utils.getReader().nextLine());

        System.out.println("Ingrese el ID de la membresía:");
        long idMembresia = validator.isValidLong("ID de Membresía", Utils.getReader().nextLine());

        membresiaService.assignMembresia(document, idMembresia);
        System.out.println("Membresía asignada/actualizada.");
    }

    private void resetPassword() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el documento del usuario para restablecer la contraseña:");
        long document = validator.validDocument(Utils.getReader().nextLine());
        PersonDto person = personService.findByDocument(new PersonDto(0, document, null, null, null));
        if (person == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        String tempPassword = Utils.generateRandomPassword();
        person.setPassword(tempPassword);
        personService.updatePersonPassword(person);
        System.out.println("Contraseña restablecida. Nueva contraseña temporal: " + tempPassword);
    }

    private void toggleUserStatus() throws Exception {
        ensureAdmin();

        System.out.println("Ingrese el documento del usuario:");
        long document = validator.validDocument(Utils.getReader().nextLine());
        PersonDto person = personService.findByDocument(new PersonDto(0, document, null, null, null));
        if (person == null) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.println("Estado actual del usuario: " + (person.isActive() ? "Activo" : "Inactivo"));
        System.out.println("Ingrese '1' para habilitar o '2' para deshabilitar el usuario:");
        String option = Utils.getReader().nextLine();
        if ("1".equals(option)) {
            person.setActive(true);
        } else if ("2".equals(option)) {
            person.setActive(false);
        } else {
            System.out.println("Opción inválida.");
            return;
        }
        personService.updatePersonStatus(person);
        System.out.println("Estado del usuario actualizado correctamente.");
    }

    private void generateReports() throws Exception {
        ensureAdmin();

        System.out.println("=== Reporte de Usuarios ===");
        personService.getUserReport().forEach((role, count) ->
                System.out.println("Rol: " + role + " - Cantidad: " + count));

        System.out.println("\n=== Reporte de Membresías ===");
        membershipService.getMembershipReport().forEach((status, count) ->
                System.out.println("Estado: " + status + " - Cantidad: " + count));
    }

    private void sendNotification() throws Exception {
        ensureAdmin();

        System.out.println("¿Enviar notificación a un usuario específico (1) o a todos (2)?");
        String option = Utils.getReader().nextLine();
        if ("1".equals(option)) {
            System.out.println("Ingrese el documento del usuario:");
            long document = validator.validDocument(Utils.getReader().nextLine());
            PersonDto person = personService.findByDocument(new PersonDto(0, document, null, null, null));
            if (person == null) {
                System.out.println("Usuario no encontrado.");
                return;
            }
            System.out.println("Ingrese el mensaje a enviar:");
            String message = Utils.getReader().nextLine();
            personService.sendNotification(person, message);
            System.out.println("Notificación enviada a " + person.getName());
        } else if ("2".equals(option)) {
            System.out.println("Ingrese el mensaje a enviar a todos los usuarios:");
            String message = Utils.getReader().nextLine();
            personService.sendNotificationToAll(message);
            System.out.println("Notificación enviada a todos los usuarios.");
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private void ensureAdmin() throws Exception {
        if (!isAdmin()) {
            throw new Exception("Acceso denegado. Solo administradores pueden realizar esta acción.");
        }
    }
}
