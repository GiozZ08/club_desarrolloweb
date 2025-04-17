package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import app.config.MYSQLConnection;
import app.dao.interfaces.InvoiceDetailDao;
import app.dto.InvoiceDetailDto;
import app.helpers.Helper;
import app.model.Consumo;
import app.model.Invoice;
import app.model.InvoiceDetail;

/**
 * Implementación de {@link InvoiceDetailDao} para manejar la persistencia
 * de los detalles de factura en la base de datos.
 */
public class InvoiceDetailDaoImplementation implements InvoiceDetailDao {

    /**
     * Crea un nuevo detalle de factura en la base de datos.
     *
     * @param invoiceDetailDto DTO que contiene la información del detalle a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    @Override
    public void createInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
        // Convertir el DTO a la entidad InvoiceDetail
        InvoiceDetail invoiceDetail = Helper.parse(invoiceDetailDto);
        
        // Consulta de inserción: se asume que la tabla se llama INVOICEDETAIL
        String query = "INSERT INTO INVOICEDETAIL (IDINVOICE, IDCONSUMO, CANTIDAD, PRECIOUNITARIO, SUBTOTAL) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            // Se asume que la entidad InvoiceDetail tiene referencia a una Invoice y a un Consumo
            ps.setLong(1, invoiceDetail.getInvoice().getId());
            ps.setLong(2, invoiceDetail.getConsumo().getIdConsumo());
            ps.setInt(3, invoiceDetail.getCantidad());
            ps.setDouble(4, invoiceDetail.getPrecioUnitario());
            ps.setDouble(5, invoiceDetail.getSubtotal());
            
            ps.executeUpdate();
        }
    }

    /**
     * Busca un detalle de factura en la base de datos mediante su identificador.
     *
     * @param id Identificador del detalle de factura.
     * @return InvoiceDetailDto con la información encontrada, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public InvoiceDetailDto findInvoiceDetailById(long id) throws Exception {
        String query = "SELECT ID, IDINVOICE, IDCONSUMO, CANTIDAD, PRECIOUNITARIO, SUBTOTAL FROM INVOICEDETAIL WHERE ID = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Crear una entidad InvoiceDetail con los datos recuperados
                    InvoiceDetail invoiceDetail = new InvoiceDetail();
                    invoiceDetail.setId(rs.getLong("ID"));
                    
                    // Para la Invoice: se crea un objeto mínimo con el ID
                    Invoice invoice = new Invoice();
                    invoice.setId(rs.getLong("IDINVOICE"));
                    invoiceDetail.setInvoice(invoice);
                    
                    // Para el Consumo: se crea un objeto mínimo con el IDConsumo
                    Consumo consumo = new Consumo();
                    consumo.setIdConsumo(rs.getLong("IDCONSUMO"));
                    invoiceDetail.setConsumo(consumo);
                    
                    invoiceDetail.setCantidad(rs.getInt("CANTIDAD"));
                    invoiceDetail.setPrecioUnitario(rs.getDouble("PRECIOUNITARIO"));
      
                    
                    // Convertir la entidad a DTO
                    return Helper.parse(invoiceDetail);
                }
            }
        }
        return null;
    }

    /**
     * Actualiza la información de un detalle de factura existente en la base de datos.
     *
     * @param invoiceDetailDto DTO con la información actualizada del detalle.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    @Override
    public void updateInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
        InvoiceDetail invoiceDetail = Helper.parse(invoiceDetailDto);
        String query = "UPDATE INVOICEDETAIL SET IDINVOICE = ?, IDCONSUMO = ?, CANTIDAD = ?, PRECIOUNITARIO = ?, SUBTOTAL = ? WHERE ID = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, invoiceDetail.getInvoice().getId());
            ps.setLong(2, invoiceDetail.getConsumo().getIdConsumo());
            ps.setInt(3, invoiceDetail.getCantidad());
            ps.setDouble(4, invoiceDetail.getPrecioUnitario());
            ps.setDouble(5, invoiceDetail.getSubtotal());
            ps.setLong(6, invoiceDetail.getId());
            
            ps.executeUpdate();
        }
    }

    /**
     * Elimina un detalle de factura de la base de datos.
     *
     * @param invoiceDetailDto DTO que contiene la información del detalle a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    @Override
    public void deleteInvoiceDetail(InvoiceDetailDto invoiceDetailDto) throws Exception {
        String query = "DELETE FROM INVOICEDETAIL WHERE ID = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, invoiceDetailDto.getId());
            ps.executeUpdate();
        }
    }

    /**
     * Verifica si un detalle de factura existe en la base de datos mediante su identificador.
     *
     * @param id Identificador del detalle.
     * @return true si el detalle existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public boolean existsById(long id) throws Exception {
        String query = "SELECT 1 FROM INVOICEDETAIL WHERE ID = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Override
public List<InvoiceDetailDto> findDetailsByInvoiceId(long invoiceId) throws Exception {
    String query = "SELECT ID, IDINVOICE, IDCONSUMO, CANTIDAD, PRECIOUNITARIO, SUBTOTAL FROM INVOICEDETAIL WHERE IDINVOICE = ?";
    List<InvoiceDetailDto> invoiceDetails = new ArrayList<>();

    try (Connection connection = MYSQLConnection.getConnection();
         PreparedStatement ps = connection.prepareStatement(query)) {

        ps.setLong(1, invoiceId);

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                InvoiceDetail invoiceDetail = new InvoiceDetail();
                invoiceDetail.setId(rs.getLong("ID"));

                Invoice invoice = new Invoice();
                invoice.setId(rs.getLong("IDINVOICE"));
                invoiceDetail.setInvoice(invoice);

                Consumo consumo = new Consumo();
                consumo.setIdConsumo(rs.getLong("IDCONSUMO"));
                invoiceDetail.setConsumo(consumo);

                invoiceDetail.setCantidad(rs.getInt("CANTIDAD"));
                invoiceDetail.setPrecioUnitario(rs.getDouble("PRECIOUNITARIO"));
                

                // Convertimos la entidad a DTO y lo agregamos a la lista
                invoiceDetails.add(Helper.parse(invoiceDetail));
            }
        }
    }

    return invoiceDetails;
}

}

