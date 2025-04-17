package app.dao.interfaces;

import java.util.List;

import app.dto.InvoiceDto;

/**
 * Interfaz para la gestión de facturas en la base de datos.
 */
public interface InvoiceDao {

    /**
     * Crea una nueva factura en la base de datos.
     *
     * @param invoiceDto DTO que contiene la información de la factura a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Busca una factura en la base de datos mediante su identificador.
     *
     * @param id Identificador de la factura.
     * @return {@link InvoiceDto} con la información de la factura encontrada, o {@code null} si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    InvoiceDto findInvoiceById(long id) throws Exception;

    /**
     * Actualiza la información de una factura existente en la base de datos.
     *
     * @param invoiceDto DTO con la información actualizada de la factura.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updateInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Elimina una factura de la base de datos.
     *
     * @param invoiceDto DTO que contiene la información de la factura a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteInvoice(InvoiceDto invoiceDto) throws Exception;

    /**
     * Verifica si una factura existe en la base de datos mediante su identificador.
     *
     * @param id Identificador de la factura.
     * @return {@code true} si la factura existe, {@code false} en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsById(long id) throws Exception;

    /**
     * Lista todas las facturas registradas en el sistema.
     *
     * @return Lista de InvoiceDto.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<InvoiceDto> findAllInvoices() throws Exception;
}
