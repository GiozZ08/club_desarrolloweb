package app.controller;

import java.util.List;
import java.util.Scanner;

import app.controller.validator.InvoiceDetailValidator;
import app.dto.ConsumoDto;
import app.dto.InvoiceDetailDto;
import app.dto.InvoiceDto;
import app.service.interfaces.InvoiceDetailService;

/**
 * Controller para gestionar los detalles de factura (InvoiceDetail).
 * Implementa un menú interactivo de consola.
 */
public class InvoiceDetailController implements ControllerInterface {

    private final InvoiceDetailService detailService;
    private final InvoiceDetailValidator validator;
    private final Scanner scanner;

    public InvoiceDetailController(InvoiceDetailService detailService) {
        this.detailService = detailService;
        this.validator = new InvoiceDetailValidator();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void session() throws Exception {
        boolean exit = false;
        while (!exit) {
            printMenu();
            String option = scanner.nextLine().trim();

            switch (option) {
                case "1":
                    createFlow();
                    break;
                case "2":
                    findByIdFlow();
                    break;
                case "3":
                    updateFlow();
                    break;
                case "4":
                    deleteFlow();
                    break;
                case "5":
                    listAllFlow();
                    break;
                case "6":
                    listByInvoiceIdFlow();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intenta de nuevo.");
            }
        }
        System.out.println("Saliendo de InvoiceDetailController.");
    }

    private void printMenu() {
        System.out.println("\n--- Gestión de Detalles de Factura ---");
        System.out.println("1. Crear detalle");
        System.out.println("2. Buscar detalle por ID");
        System.out.println("3. Actualizar detalle");
        System.out.println("4. Eliminar detalle");
        System.out.println("5. Listar todos los detalles");
        System.out.println("6. Listar detalles por factura");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private void createFlow() {
        try {
            System.out.print("ID del detalle: ");
            String idVal = scanner.nextLine();
            System.out.print("ID de la factura: ");
            String invVal = scanner.nextLine();
            System.out.print("ID del consumo: ");
            String consVal = scanner.nextLine();
            System.out.print("Cantidad: ");
            String qtyVal = scanner.nextLine();
            System.out.print("Precio unitario: ");
            String priceVal = scanner.nextLine();

            long id           = validator.validId(idVal);
            long invoiceId    = validator.validInvoiceId(invVal);
            long consumoId    = validator.validConsumoId(consVal);
            int cantidad      = validator.validCantidad(qtyVal);
            double precioUnit = validator.validPrecioUnitario(priceVal);

            InvoiceDto invoice = new InvoiceDto();
            invoice.setId(invoiceId);
            ConsumoDto consumo = new ConsumoDto();
            consumo.setIdConsumo(consumoId);

            InvoiceDetailDto detail =
                new InvoiceDetailDto(id, invoice, consumo, cantidad, precioUnit);

            detailService.createInvoiceDetail(detail);
            System.out.println("✅ Detalle creado: " + id);
        } catch (Exception e) {
            System.err.println("❌ Error al crear detalle: " + e.getMessage());
        }
    }

    private void findByIdFlow() {
        try {
            System.out.print("ID del detalle a buscar: ");
            String idVal = scanner.nextLine();
            long id = validator.validId(idVal);

            InvoiceDetailDto detail = detailService.findInvoiceDetailById(id);
            if (detail != null) {
                System.out.println(detail);
            } else {
                System.out.println("No se encontró detalle con ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al buscar detalle: " + e.getMessage());
        }
    }

    private void updateFlow() {
        try {
            System.out.print("ID del detalle a actualizar: ");
            String idVal = scanner.nextLine();
            System.out.print("Nuevo ID de la factura: ");
            String invVal = scanner.nextLine();
            System.out.print("Nuevo ID del consumo: ");
            String consVal = scanner.nextLine();
            System.out.print("Nueva cantidad: ");
            String qtyVal = scanner.nextLine();
            System.out.print("Nuevo precio unitario: ");
            String priceVal = scanner.nextLine();

            long id           = validator.validId(idVal);
            long invoiceId    = validator.validInvoiceId(invVal);
            long consumoId    = validator.validConsumoId(consVal);
            int cantidad      = validator.validCantidad(qtyVal);
            double precioUnit = validator.validPrecioUnitario(priceVal);

            InvoiceDto invoice = new InvoiceDto();
            invoice.setId(invoiceId);
            ConsumoDto consumo = new ConsumoDto();
            consumo.setIdConsumo(consumoId);

            InvoiceDetailDto detail =
                new InvoiceDetailDto(id, invoice, consumo, cantidad, precioUnit);

            detailService.updateInvoiceDetail(detail);
            System.out.println("✅ Detalle actualizado: " + id);
        } catch (Exception e) {
            System.err.println("❌ Error al actualizar detalle: " + e.getMessage());
        }
    }

    private void deleteFlow() {
        try {
            System.out.print("ID del detalle a eliminar: ");
            String idVal = scanner.nextLine();
            long id = validator.validId(idVal);

            InvoiceDetailDto detail = new InvoiceDetailDto();
            detail.setId(id);
            detailService.deleteInvoiceDetail(detail);
            System.out.println("✅ Detalle eliminado: " + id);
        } catch (Exception e) {
            System.err.println("❌ Error al eliminar detalle: " + e.getMessage());
        }
    }

    private void listAllFlow() {
        try {
            System.out.print("ID de la factura para listar todos los detalles: ");
            String invVal = scanner.nextLine();
            long invoiceId = validator.validInvoiceId(invVal);
            List<InvoiceDetailDto> all = detailService.findDetailsByInvoiceId(invoiceId);
            if (all.isEmpty()) {
                System.out.println("No hay detalles registrados.");
            } else {
                all.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar detalles: " + e.getMessage());
        }
    }

    private void listByInvoiceIdFlow() {
        try {
            System.out.print("ID de la factura para listar detalles: ");
            String invVal = scanner.nextLine();
            long invoiceId = validator.validInvoiceId(invVal);

            List<InvoiceDetailDto> list = detailService.findDetailsByInvoiceId(invoiceId);
            if (list.isEmpty()) {
                System.out.println("No hay detalles para la factura: " + invoiceId);
            } else {
                list.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("❌ Error al listar por factura: " + e.getMessage());
        }
    }
}