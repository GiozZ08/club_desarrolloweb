package app.service;

import java.util.List;

import app.dao.interfaces.InvoiceDao;
import app.dto.InvoiceDto;
import app.service.interfaces.InvoiceService;

/**
 * Implementación de la capa de servicio para gestionar las facturas.
 */
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceDao invoiceDao;

    /**
     * Constructor que recibe la implementación de InvoiceDao.
     *
     * @param invoiceDao Implementación concreta del DAO de facturas.
     */
    public InvoiceServiceImpl(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    @Override
    public void createInvoice(InvoiceDto invoiceDto) throws Exception {
        invoiceDao.createInvoice(invoiceDto);
    }

    @Override
    public InvoiceDto findInvoiceById(long id) throws Exception {
        return invoiceDao.findInvoiceById(id);
    }

    @Override
    public void updateInvoice(InvoiceDto invoiceDto) throws Exception {
        invoiceDao.updateInvoice(invoiceDto);
    }

    @Override
    public void deleteInvoice(InvoiceDto invoiceDto) throws Exception {
        invoiceDao.deleteInvoice(invoiceDto);
    }

    @Override
    public boolean existsById(long id) throws Exception {
        return invoiceDao.existsById(id);
    }

    @Override
    public List<InvoiceDto> getAllInvoices() throws Exception {
        return invoiceDao.findAllInvoices();
    }
}
