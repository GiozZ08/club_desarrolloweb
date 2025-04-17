package app.dao.interfaces;

import java.util.List;

import app.dto.InvoiceDetailDto;

/**
 * Interfaz para la gestión de los detalles de factura en la base de datos.
 */
public interface InvoiceDetailDao {

    /**
     * Crea un nuevo detalle de factura en la base de datos.
     *
     * @param invoiceDetailDto DTO que contiene la información del detalle de factura a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;

    /**
     * Busca un detalle de factura en la base de datos mediante su identificador.
     *
     * @param id Identificador del detalle de factura.
     * @return {@link InvoiceDetailDto} con la información del detalle encontrado, o {@code null} si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    InvoiceDetailDto findInvoiceDetailById(long id) throws Exception;

    /**
     * Actualiza la información de un detalle de factura existente en la base de datos.
     *
     * @param invoiceDetailDto DTO con la información actualizada del detalle de factura.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updateInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;

    /**
     * Elimina un detalle de factura de la base de datos.
     *
     * @param invoiceDetailDto DTO con la información del detalle de factura a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;

    /**
     * Verifica si un detalle de factura existe en la base de datos mediante su identificador.
     *
     * @param id Identificador del detalle de factura.
     * @return {@code true} si el detalle existe, {@code false} en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsById(long id) throws Exception;

     /**
     * Lista todos los detalles de factura de una factura específica.
     *
     * @param invoiceId Identificador de la factura.
     * @return Lista de InvoiceDetailDto asociados a la factura.
     * @throws Exception si ocurre un error durante la consulta.
     */
    List<InvoiceDetailDto> findDetailsByInvoiceId(long invoiceId) throws Exception;
}

