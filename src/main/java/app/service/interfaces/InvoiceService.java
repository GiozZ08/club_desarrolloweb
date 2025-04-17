package app.service.interfaces;

import java.util.List;

import app.dto.InvoiceDto;

public interface InvoiceService {

    /**
     * Crea una nueva factura.
     *
     * @param invoiceDto DTO con la información de la factura a crear.
     * @throws Exception Si ocurre un error durante la operación.
     */
    void createInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Obtiene una factura por su ID.
     *
     * @param id Identificador de la factura.
     * @return DTO con la información de la factura.
     * @throws Exception Si ocurre un error durante la operación.
     */
    InvoiceDto findInvoiceById(long id) throws Exception;

    /**
     * Actualiza una factura existente.
     *
     * @param invoiceDto DTO con la información actualizada de la factura.
     * @throws Exception Si ocurre un error durante la operación.
     */
    void updateInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Elimina una factura.
     *
     * @param invoiceDto DTO con la información de la factura a eliminar.
     * @throws Exception Si ocurre un error durante la operación.
     */
    void deleteInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Verifica si una factura existe por su ID.
     *
     * @param id Identificador de la factura.
     * @return `true` si la factura existe, `false` en caso contrario.
     * @throws Exception Si ocurre un error durante la operación.
     */
    boolean existsById(long id) throws Exception;

    /**
     * Obtiene todas las facturas registradas.
     *
     * @return Lista de DTOs con la información de las facturas.
     * @throws Exception Si ocurre un error durante la operación.
     */
    List<InvoiceDto> getAllInvoices() throws Exception;
}

