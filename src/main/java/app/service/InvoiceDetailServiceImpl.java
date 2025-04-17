package app.service;

import java.util.List;

import app.dao.interfaces.InvoiceDetailDao;
import app.dto.InvoiceDetailDto;
import app.service.interfaces.InvoiceDetailService;

/**
 * Implementación de la capa de servicio para la gestión de detalles de factura.
 */
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    private final InvoiceDetailDao invoiceDetailDao;

    /**
     * Constructor que recibe una implementación de {@link InvoiceDetailDaoImplementation}.
     *
     * @param invoiceDetailDao Implementación de acceso a datos para detalles de factura.
     */
    public InvoiceDetailServiceImpl(InvoiceDetailDao invoiceDetailDao) {
        this.invoiceDetailDao = invoiceDetailDao;
    }

    /**
     * Crea un nuevo detalle de factura.
     *
     * @param invoiceDetailDto DTO con la información del detalle a crear.
     * @throws Exception Si ocurre un error durante la creación.
     */
   @Override
   public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
    invoiceDetailDao.createInvoiceDetail(invoiceDetailDto);
}

    /**
     * Busca un detalle de factura por su ID.
     *
     * @param id Identificador del detalle de factura.
     * @return DTO con la información del detalle, o {@code null} si no existe.
     * @throws Exception Si ocurre un error durante la búsqueda.
     */
    @Override
    public InvoiceDetailDto findInvoiceDetailById(long id) throws Exception {
        return invoiceDetailDao.findInvoiceDetailById(id);
    }

    /**
     * Actualiza la información de un detalle de factura existente.
     *
     * @param invoiceDetailDto DTO con la información actualizada.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    @Override
    public void updateInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
        if (!invoiceDetailDao.existsById(invoiceDetailDto.getId())) {
            throw new Exception("El detalle de factura con ID " + invoiceDetailDto.getId() + " no existe.");
        }
        invoiceDetailDao.updateInvoiceDetail(invoiceDetailDto);
    }

    /**
     * Elimina un detalle de factura.
     *
     * @param invoiceDetailDto DTO con la información del detalle a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    @Override
    public void deleteInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
        if (!invoiceDetailDao.existsById(invoiceDetailDto.getId())) {
            throw new Exception("El detalle de factura con ID " + invoiceDetailDto.getId() + " no existe.");
        }
        invoiceDetailDao.deleteInvoiceDetail(invoiceDetailDto);
    }

    /**
     * Verifica si un detalle de factura existe en la base de datos.
     *
     * @param id Identificador del detalle de factura.
     * @return {@code true} si existe, {@code false} en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public boolean existsById(long id) throws Exception {
        return invoiceDetailDao.existsById(id);
    }

    /**
     * Obtiene todos los detalles de factura asociados a una factura específica.
     *
     * @param invoiceId Identificador de la factura.
     * @return Lista de detalles de factura en formato DTO.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public List<InvoiceDetailDto> findDetailsByInvoiceId(long invoiceId) throws Exception {
        return invoiceDetailDao.findDetailsByInvoiceId(invoiceId);
    }
}
