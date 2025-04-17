package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.MembresiaDao;
import app.dto.MembresiaDto;
import app.helpers.Helper;
import app.model.Membresia;

/**
 * Implementación de {@link MembresiaDao} para manejar la persistencia de membresías en la base de datos.
 */
public class MembresiaDaoImplementation implements MembresiaDao {

    /**
     * Crea una nueva membresía en la base de datos.
     *
     * @param membresiaDto DTO que contiene la información de la membresía a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    @Override
    public void createMembresia(MembresiaDto membresiaDto) throws Exception {
        // Convertir el DTO a la entidad Membresia
        Membresia membresia = Helper.parse(membresiaDto);
        // Consulta de inserción: se asume que la tabla se llama MEMBRESIA y tiene las columnas correspondientes
        String query = "INSERT INTO MEMBRESIA (ESTADO, FONDOACTUAL, LIMITEFONDO, TIPOMEMBRESIA) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, membresia.getEstado());
            ps.setDouble(2, membresia.getFondoActual());
            ps.setDouble(3, membresia.getLimiteFondo());
            ps.setString(4, membresia.getTipoMembresia());
            
            ps.executeUpdate();
        }
    }

    /**
     * Busca una membresía en la base de datos mediante su identificador.
     *
     * @param id Identificador de la membresía.
     * @return MembresiaDto con la información de la membresía encontrada, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public MembresiaDto findMembresiaById(long id) throws Exception {
        String query = "SELECT IDMEMBRESIA, ESTADO, FONDOACTUAL, LIMITEFONDO, TIPOMEMBRESIA FROM MEMBRESIA WHERE IDMEMBRESIA = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Membresia membresia = new Membresia();
                    membresia.setIdMembresia(rs.getLong("IDMEMBRESIA"));
                    membresia.setEstado(rs.getString("ESTADO"));
                    membresia.setFondoActual(rs.getDouble("FONDOACTUAL"));
                    membresia.setLimiteFondo(rs.getDouble("LIMITEFONDO"));
                    membresia.setTipoMembresia(rs.getString("TIPOMEMBRESIA"));
                    
                    return Helper.parse(membresia);
                }
            }
        }
        return null;
    }

    /**
     * Actualiza la información de una membresía existente en la base de datos.
     *
     * @param membresiaDto DTO con la información actualizada de la membresía.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    @Override
    public void updateMembresia(MembresiaDto membresiaDto) throws Exception {
        Membresia membresia = Helper.parse(membresiaDto);
        String query = "UPDATE MEMBRESIA SET ESTADO = ?, FONDOACTUAL = ?, LIMITEFONDO = ?, TIPOMEMBRESIA = ? WHERE IDMEMBRESIA = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setString(1, membresia.getEstado());
            ps.setDouble(2, membresia.getFondoActual());
            ps.setDouble(3, membresia.getLimiteFondo());
            ps.setString(4, membresia.getTipoMembresia());
            ps.setLong(5, membresia.getIdMembresia());
            
            ps.executeUpdate();
        }
    }

    /**
     * Elimina una membresía de la base de datos.
     *
     * @param membresiaDto DTO que contiene la información de la membresía a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    @Override
    public void deleteMembresia(MembresiaDto membresiaDto) throws Exception {
        String query = "DELETE FROM MEMBRESIA WHERE IDMEMBRESIA = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, membresiaDto.getIdMembresia());
            ps.executeUpdate();
        }
    }

    /**
     * Verifica si una membresía existe en la base de datos mediante su identificador.
     *
     * @param id Identificador de la membresía.
     * @return true si la membresía existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public boolean existsById(long id) throws Exception {
        String query = "SELECT 1 FROM MEMBRESIA WHERE IDMEMBRESIA = ?";
        
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
             
            ps.setLong(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
