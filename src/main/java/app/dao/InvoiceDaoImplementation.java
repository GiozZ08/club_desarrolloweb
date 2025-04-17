package app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.config.MYSQLConnection;
import app.dao.interfaces.InvoiceDao;
import app.dto.InvoiceDto;
import app.helpers.Helper;
import app.model.Invoice;

/**
 * Implementación de {@link InvoiceDao} para manejar la persistencia de facturas en la base de datos.
 */
public class InvoiceDaoImplementation implements InvoiceDao {

    /**
     * Crea una nueva factura en la base de datos.
     *
     * @param invoiceDto DTO que contiene la información de la factura a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    @Override
    public void createInvoice(InvoiceDto invoiceDto) throws Exception {
        // Convertir el DTO a la entidad Invoice
        Invoice invoice = Helper.parse(invoiceDto);

        // Consulta de inserción (ajusta nombres de columnas si difieren en tu esquema)
        // Suponiendo que ID se genera automáticamente en la base de datos
        String query = "INSERT INTO INVOICE (IDSOCIO, FECHAFACTURA, VALORTOTAL, METODOPAGO, FECHAPAGO, STATUS) "
                     + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            // Asumiendo que `invoice.getSocio()` devuelve la persona (socio) que hace la factura
            // y que su ID se guarda en la columna IDSOCIO
            ps.setLong(1, invoice.getSocio().getId());
            ps.setDate(2, new Date(invoice.getFechaFactura().getTime()));
            ps.setDouble(3, invoice.getValorTotal());
            ps.setString(4, invoice.getMetodoPago());
            
            // Manejo de fecha de pago (puede ser nulo si aún no se paga)
            if (invoice.getFechaPago() != null) {
                ps.setDate(5, new Date(invoice.getFechaPago().getTime()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }
            
            ps.setBoolean(6, invoice.isStatus());

            ps.executeUpdate();
        }
    }

    /**
     * Busca una factura en la base de datos mediante su identificador.
     *
     * @param id Identificador de la factura.
     * @return InvoiceDto con la información de la factura encontrada, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public InvoiceDto findInvoiceById(long id) throws Exception {
        String query = "SELECT ID, IDSOCIO, FECHAFACTURA, VALORTOTAL, METODOPAGO, FECHAPAGO, STATUS "
                     + "FROM INVOICE WHERE ID = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getLong("ID"));
                    // Asumiendo que `Helper.parse(...)` puede manejar Person ↔ PersonDto
                    // Debes buscar la persona/socio en la tabla PERSON o en la capa de negocio
                    // Por simplicidad, si solo guardas el ID, puedes setear un "Person" mínimo
                    // o, si tu Helper parsea la persona, haz una consulta extra. Ejemplo:
                    
                    // Opción 1 (simple): setear un Person con solo ID
                    // Person socio = new Person();
                    // socio.setId(rs.getLong("IDSOCIO"));
                    // invoice.setSocio(socio);
                    
                    // Opción 2 (consulta extra) -> a cargo del Service, no del DAO
                    // invoice.setSocio(personEncontrado);

                    invoice.setFechaFactura(rs.getDate("FECHAFACTURA"));
                    invoice.setValorTotal(rs.getDouble("VALORTOTAL"));
                    invoice.setMetodoPago(rs.getString("METODOPAGO"));
                    invoice.setFechaPago(rs.getDate("FECHAPAGO"));
                    invoice.setStatus(rs.getBoolean("STATUS"));

                    // Convertir a InvoiceDto usando Helper
                    return Helper.parse(invoice);
                }
            }
        }
        return null;
    }

    /**
     * Actualiza la información de una factura existente en la base de datos.
     *
     * @param invoiceDto DTO con la información actualizada de la factura.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    @Override
    public void updateInvoice(InvoiceDto invoiceDto) throws Exception {
        Invoice invoice = Helper.parse(invoiceDto);

        String query = "UPDATE INVOICE SET IDSOCIO = ?, FECHAFACTURA = ?, VALORTOTAL = ?, "
                     + "METODOPAGO = ?, FECHAPAGO = ?, STATUS = ? WHERE ID = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, invoice.getSocio().getId());
            ps.setDate(2, new Date(invoice.getFechaFactura().getTime()));
            ps.setDouble(3, invoice.getValorTotal());
            ps.setString(4, invoice.getMetodoPago());

            if (invoice.getFechaPago() != null) {
                ps.setDate(5, new Date(invoice.getFechaPago().getTime()));
            } else {
                ps.setNull(5, java.sql.Types.DATE);
            }

            ps.setBoolean(6, invoice.isStatus());
            ps.setLong(7, invoice.getId());

            ps.executeUpdate();
        }
    }

    /**
     * Elimina una factura de la base de datos.
     *
     * @param invoiceDto DTO que contiene la información de la factura a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    @Override
    public void deleteInvoice(InvoiceDto invoiceDto) throws Exception {
        String query = "DELETE FROM INVOICE WHERE ID = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, invoiceDto.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Verifica si una factura existe en la base de datos mediante su identificador.
     *
     * @param id Identificador de la factura.
     * @return true si la factura existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public boolean existsById(long id) throws Exception {
        String query = "SELECT 1 FROM INVOICE WHERE ID = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

   @Override
public List<InvoiceDto> findAllInvoices() throws Exception {
    List<InvoiceDto> invoices = new ArrayList<>();
    String query = "SELECT ID, IDSOCIO, FECHAFACTURA, VALORTOTAL, METODOPAGO, FECHAPAGO, STATUS FROM INVOICE";

    try (Connection connection = MYSQLConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            Invoice invoice = new Invoice();
            invoice.setId(rs.getLong("ID"));
            // Si se necesita, recuperar el socio usando un servicio o DAO adicional
            // Person socio = personDao.findById(rs.getLong("IDSOCIO"));
            // invoice.setSocio(socio);
            invoice.setFechaFactura(rs.getDate("FECHAFACTURA"));
            invoice.setValorTotal(rs.getDouble("VALORTOTAL"));
            invoice.setMetodoPago(rs.getString("METODOPAGO"));
            invoice.setFechaPago(rs.getDate("FECHAPAGO"));
            invoice.setStatus(rs.getBoolean("STATUS"));

            invoices.add(Helper.parse(invoice));
        }
    }
    return invoices;
}

}
