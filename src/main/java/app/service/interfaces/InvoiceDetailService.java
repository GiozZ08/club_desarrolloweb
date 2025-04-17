package app.service.interfaces;

import java.util.List;

import app.dto.InvoiceDetailDto;

/**
 * Servicio para la gestión de los detalles de factura.
 */
public interface InvoiceDetailService {

    /**
     * Crea un nuevo detalle de factura.
     *
     * @param invoiceDetailDto DTO con la información del detalle de factura a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;

    /**
     * Busca un detalle de factura por su identificador.
     *
     * @param id Identificador del detalle de factura.
     * @return InvoiceDetailDto con la información del detalle encontrado, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    InvoiceDetailDto findInvoiceDetailById(long id) throws Exception;

    /**
     * Actualiza un detalle de factura existente.
     *
     * @param invoiceDetailDto DTO con la información actualizada del detalle de factura.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updateInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;

    /**
     * Elimina un detalle de factura.
     *
     * @param invoiceDetailDto DTO con la información del detalle de factura a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception;

    /**
     * Verifica si un detalle de factura existe mediante su identificador.
     *
     * @param id Identificador del detalle de factura.
     * @return true si el detalle existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsById(long id) throws Exception;

    /**
     * Obtiene todos los detalles de factura asociados a una factura específica.
     *
     * @param invoiceId Identificador de la factura.
     * @return Lista de InvoiceDetailDto asociados a la factura.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    List<InvoiceDetailDto> findDetailsByInvoiceId(long invoiceId) throws Exception;
}

