package app.controller;

import java.util.Scanner;

import app.controller.validator.ConsumoValidator;
import app.dto.ConsumoDto;
import app.service.interfaces.ConsumoService;

/**
 * Controlador para gestionar las operaciones relacionadas con los consumos.
 */
public class ConsumoController implements ControllerInterface {

    private final ConsumoService consumoService;
    private final ConsumoValidator validator;
    private final Scanner scanner;

    /**
     * Constructor del controlador de consumo.
     *
     * @param consumoService Servicio que gestiona la lógica de consumo.
     */
    public ConsumoController(ConsumoService consumoService) {
        this.consumoService = consumoService;
        this.validator = new ConsumoValidator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void session() throws Exception {
        int option;
        do {
            System.out.println("\n--- GESTIÓN DE CONSUMOS ---");
            System.out.println("1. Crear consumo");
            System.out.println("2. Buscar consumo por ID");
            System.out.println("3. Actualizar consumo");
            System.out.println("4. Eliminar consumo");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1 -> create();
                case 2 -> findById();
                case 3 -> update();
                case 4 -> delete();
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 0);
    }

    private void create() throws Exception {
        System.out.println("\n--- CREAR CONSUMO ---");
        System.out.print("Fecha de consumo (yyyy-MM-dd): ");
        var fecha = validator.validFechaConsumo(scanner.nextLine());
        System.out.print("Nombre del producto/servicio: ");
        var nombre = validator.validNombreProducto(scanner.nextLine());
        System.out.print("Valor unitario: ");
        var valor = validator.validValorUnitario(scanner.nextLine());

        ConsumoDto consumo = new ConsumoDto(0, fecha, nombre, valor);
        consumoService.createConsumo(consumo);
        System.out.println("Consumo creado exitosamente.");
    }

    private void findById() throws Exception {
        System.out.println("\n--- CONSULTAR CONSUMO ---");
        System.out.print("ID de consumo: ");
        long id = validator.validIdConsumo(scanner.nextLine());

        ConsumoDto consumo = consumoService.findConsumoById(id);
        if (consumo != null) {
            System.out.println("Resultado: " + consumo);
        } else {
            System.out.println("No se encontró un consumo con ese ID.");
        }
    }

    private void update() throws Exception {
        System.out.println("\n--- ACTUALIZAR CONSUMO ---");
        System.out.print("ID de consumo a actualizar: ");
        long id = validator.validIdConsumo(scanner.nextLine());

        if (!consumoService.existsById(id)) {
            System.out.println("El consumo no existe.");
            return;
        }

        System.out.print("Nueva fecha de consumo (yyyy-MM-dd): ");
        var fecha = validator.validFechaConsumo(scanner.nextLine());
        System.out.print("Nuevo nombre del producto/servicio: ");
        var nombre = validator.validNombreProducto(scanner.nextLine());
        System.out.print("Nuevo valor unitario: ");
        var valor = validator.validValorUnitario(scanner.nextLine());

        ConsumoDto consumo = new ConsumoDto(id, fecha, nombre, valor);
        consumoService.updateConsumo(consumo);
        System.out.println("Consumo actualizado exitosamente.");
    }

    private void delete() throws Exception {
        System.out.println("\n--- ELIMINAR CONSUMO ---");
        System.out.print("ID de consumo a eliminar: ");
        long id = validator.validIdConsumo(scanner.nextLine());

        if (!consumoService.existsById(id)) {
            System.out.println("El consumo no existe.");
            return;
        }

        ConsumoDto consumo = consumoService.findConsumoById(id);
        consumoService.deleteConsumo(consumo);
        System.out.println("Consumo eliminado exitosamente.");
    }
}