package app.controller;

import java.util.List;

import app.controller.validator.PartnerValidator;
import app.controller.validator.PersonValidator;
import app.dto.AdministradorDto;
import app.dto.GuestDto;
import app.dto.InvoiceDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.helpers.Utils;
import app.service.interfaces.InvoiceService;
import app.service.interfaces.MembresiaService;
import app.service.interfaces.PartnerService;
import app.service.interfaces.PersonService;

/**
 * Controlador unificado para la gestión de usuarios (Person) y socios (Partner).
 * Usa PersonService para ADMIN e INVITADO, PartnerService para SOCIO, más InvoiceService y MembresiaService.
 */
public class PersonController implements ControllerInterface {

    private final PersonService      personService;
    private final PartnerService     partnerService;
    private final InvoiceService     invoiceService;
    private final MembresiaService   membresiaService;

    private final PersonValidator    personValidator;
    private final PartnerValidator   partnerValidator;

    private PersonDto loggedInUser;

    public PersonController(PersonService personService,
                            PartnerService partnerService,
                            InvoiceService invoiceService,
                            MembresiaService membresiaService) {
        this.personService    = personService;
        this.partnerService   = partnerService;
        this.invoiceService   = invoiceService;
        this.membresiaService = membresiaService;

        this.personValidator  = new PersonValidator();
        this.partnerValidator = new PartnerValidator();
    }

    @Override
    public void session() throws Exception {
        boolean run = true;
        while (run) {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1) Ingresar");
            System.out.println("2) Registrar");
            System.out.println("3) Salir");
            switch (Utils.getReader().nextLine()) {
                case "1": login();  break;
                case "2": register(); break;
                case "3": run = false; break;
                default:  System.out.println("Opción inválida");
            }
        }
    }

    private void login() throws Exception {
        System.out.print("Documento: ");
        long doc = personValidator.validDocument(Utils.getReader().nextLine());
        System.out.print("Contraseña: ");
        String pwd = Utils.getReader().nextLine();

        // Aquí PersonService.login() debería aceptar (doc, pwd) y devolver PersonDto
        loggedInUser = personService.login(doc, pwd);
        if (loggedInUser == null) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        System.out.println("Bienvenido " + loggedInUser.getName()
                           + " (" + loggedInUser.getUserType() + ")");
        switch (loggedInUser.getUserType().toUpperCase()) {
            case "ADMIN":  adminMenu();   break;
            case "SOCIO":  partnerMenu(); break;
            default:       guestMenu();   break;
        }
    }

    private void register() throws Exception {
        System.out.println("=== REGISTRO ===");
        System.out.print("Nombre: ");
        String name = Utils.getReader().nextLine();    personValidator.validName(name);

        System.out.print("Documento: ");
        long doc = personValidator.validDocument(Utils.getReader().nextLine());

        System.out.print("Username: ");
        String user = Utils.getReader().nextLine();

        System.out.print("Contraseña: ");
        String pwd = Utils.getReader().nextLine();

        System.out.print("Rol (ADMIN, SOCIO, INVITADO): ");
        String role = Utils.getReader().nextLine().toUpperCase();

        switch (role) {
            case "ADMIN":
                AdministradorDto admin = new AdministradorDto(
                    doc, doc, name, user, pwd, role, "", ""
                );
                personService.agregarAdministrador(admin);
                break;

            case "SOCIO":
                System.out.print("ID Membresía: ");
                long mid = partnerValidator.validIdMembresia(
                    Utils.getReader().nextLine()
                );
                PartnerDto partner = new PartnerDto(
                    0, doc, name, user, pwd, role, mid
                );
                personService.agregarPersona(partner);
                break;

            case "INVITADO":
                GuestDto guest = new GuestDto(
                );
                personService.agregarInvitado(guest);
                break;

            default:
                System.out.println("Rol inválido.");
                return;
        }

        System.out.println("Registro exitoso.");
    }

    // ——— MENÚ ADMIN ———
    private void adminMenu() throws Exception {
        while (true) {
            System.out.println("\n--- MENÚ ADMIN ---");
            System.out.println("1) Listar personas");
            System.out.println("2) Editar persona");
            System.out.println("3) Eliminar persona");
            System.out.println("4) Ver facturas de usuario");
            System.out.println("5) Gestionar membresía de usuario");
            System.out.println("6) Cerrar sesión");
            switch (Utils.getReader().nextLine()) {
                case "1": editPerson();         break;
                case "2": deletePerson();       break;
                case "3": listUserInvoices();   break;
                case "4": return;
                default:  System.out.println("Opción inválida");
            }
        }
    }

  

    private void editPerson() throws Exception {
        System.out.print("Documento a editar: ");
        long doc = personValidator.validDocument(Utils.getReader().nextLine());
        PersonDto p = personService.obtenerPersonaPorId(doc);
        if (p == null) {
            System.out.println("No existe esa persona.");
            return;
        }
        System.out.print("Nuevo nombre: ");
        String name = Utils.getReader().nextLine(); personValidator.validName(name);
        p.setName(name);
        personService.updatePerson(p);
        System.out.println("Actualizado.");
    }

    private void deletePerson() throws Exception {
        System.out.print("Documento a eliminar: ");
        long doc = personValidator.validDocument(Utils.getReader().nextLine());
        personService.eliminarPersona(doc);
        System.out.println("Eliminado.");
    }

    private void listUserInvoices() throws Exception {
        System.out.print("Documento: ");
        long doc = personValidator.validDocument(Utils.getReader().nextLine());
        List<InvoiceDto> invs = invoiceService.getAllInvoices();
        invs.forEach(System.out::println);
    }

  

    // ——— MENÚ SOCIO ———
    private void partnerMenu() throws Exception {
        while (true) {
            System.out.println("\n--- MENÚ SOCIO ---");
            System.out.println("1) Ver mis consumos");
            System.out.println("2) Registrar consumo");
            System.out.println("3) Cerrar sesión");
            switch (Utils.getReader().nextLine()) {
                case "1": addConsumption();      break;
                case "2": return;
                default:  System.out.println("Opción inválida");
            }
        }
    }


    private void addConsumption() {
        System.out.println("Funcionalidad de registro de consumo...");
    }

    // ——— MENÚ INVITADO ———
    private void guestMenu() {
        System.out.println("Acceso en modo INVITADO: sólo lectura disponible.");
    }
}