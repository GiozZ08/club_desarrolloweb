package app.controller;

import java.util.Scanner;

import app.controller.validator.MembresiaValidator;
import app.dto.MembresiaDto;
import app.service.interfaces.MembresiaService;

public class MembresiaController implements ControllerInterface {

    private final Scanner scanner;
    private final MembresiaService membresiaService;
    private final MembresiaValidator validator;

    public MembresiaController(Scanner scanner, MembresiaService membresiaService) {
        this.scanner = scanner;
        this.membresiaService = membresiaService;
        this.validator = new MembresiaValidator();
    }

    @Override
    public void session() throws Exception {
        int option = -1;
        do {
            System.out.println("\n*** Gestión de Membresías ***");
            System.out.println("1. Crear membresía");
            System.out.println("2. Consultar membresía por ID");
            System.out.println("3. Actualizar membresía");
            System.out.println("4. Eliminar membresía");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> createMembresia();
                case 2 -> findMembresiaById();
                case 3 -> updateMembresia();
                case 4 -> deleteMembresia();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (option != 0);
    }

    private void createMembresia() throws Exception {
        System.out.println("\n--- Crear nueva membresía ---");

        String estado = validator.validEstado(prompt("Estado"));
        double fondoActual = validator.validFondoActual(prompt("Fondo actual"));
        double limiteFondo = validator.validLimiteFondo(prompt("Límite de fondo"));
        String tipoMembresia = validator.validTipoMembresia(prompt("Tipo de membresía"));

        MembresiaDto dto = new MembresiaDto(0, estado, fondoActual, limiteFondo, tipoMembresia);
        membresiaService.createMembresia(dto);
        System.out.println("Membresía creada con éxito.");
    }

    private void findMembresiaById() throws Exception {
        System.out.println("\n--- Consultar membresía ---");

        long id = validator.validIdMembresia(prompt("ID de la membresía"));

        MembresiaDto dto = membresiaService.findMembresiaById(id);
        if (dto != null) {
            System.out.println(dto);
        } else {
            System.out.println("Membresía no encontrada.");
        }
    }

    private void updateMembresia() throws Exception {
        System.out.println("\n--- Actualizar membresía ---");

        long id = validator.validIdMembresia(prompt("ID de la membresía"));
        if (!membresiaService.existsById(id)) {
            System.out.println("No existe una membresía con ese ID.");
            return;
        }

        String estado = validator.validEstado(prompt("Nuevo estado"));
        double fondoActual = validator.validFondoActual(prompt("Nuevo fondo actual"));
        double limiteFondo = validator.validLimiteFondo(prompt("Nuevo límite de fondo"));
        String tipoMembresia = validator.validTipoMembresia(prompt("Nuevo tipo de membresía"));

        MembresiaDto dto = new MembresiaDto(id, estado, fondoActual, limiteFondo, tipoMembresia);
        membresiaService.updateMembresia(dto);
        System.out.println("Membresía actualizada correctamente.");
    }

    private void deleteMembresia() throws Exception {
        System.out.println("\n--- Eliminar membresía ---");

        long id = validator.validIdMembresia(prompt("ID de la membresía"));
        if (!membresiaService.existsById(id)) {
            System.out.println("No existe una membresía con ese ID.");
            return;
        }

        MembresiaDto dto = new MembresiaDto();
        dto.setIdMembresia(id);

        membresiaService.deleteMembresia(dto);
        System.out.println("Membresía eliminada correctamente.");
    }

    private String prompt(String label) {
        System.out.print(label + ": ");
        return scanner.nextLine();
    }
}