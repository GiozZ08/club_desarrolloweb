package app.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.ConsumoDao;
import app.dto.ConsumoDto;
import app.helpers.Helper;
import app.model.Consumo;

/**
 * Implementación de {@link ConsumoDao} para manejar la persistencia de consumos en la base de datos.
 */
public class ConsumoDaoImplementation implements ConsumoDao {

    /**
     * Crea un nuevo consumo en la base de datos.
     *
     * @param consumoDto DTO que contiene la información del consumo a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    @Override
    public void createConsumo(ConsumoDto consumoDto) throws Exception {
        // Convertir el DTO a la entidad Consumo usando el Helper
        Consumo consumo = Helper.parse(consumoDto);

        // Consulta de inserción (ajusta nombres de columnas si difieren en tu esquema)
        String query = "INSERT INTO CONSUMO (FECHACONSUMO, NOMBREPRODUCTO, VALORUNITARIO) VALUES (?, ?, ?)";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Asumiendo que IDCONSUMO es autogenerado en la base de datos
            preparedStatement.setDate(1, new Date(consumo.getFechaConsumo().getTime()));
            preparedStatement.setString(2, consumo.getNombreProducto());
            preparedStatement.setDouble(3, consumo.getValorUnitario());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Busca un consumo en la base de datos mediante su identificador.
     *
     * @param id Identificador del consumo.
     * @return ConsumoDto con la información del consumo encontrado, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public ConsumoDto findConsumoById(long id) throws Exception {
        String query = "SELECT IDCONSUMO, FECHACONSUMO, NOMBREPRODUCTO, VALORUNITARIO FROM CONSUMO WHERE IDCONSUMO = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    // Construir la entidad Consumo
                    Consumo consumo = new Consumo();
                    consumo.setIdConsumo(rs.getLong("IDCONSUMO"));
                    consumo.setFechaConsumo(rs.getDate("FECHACONSUMO"));
                    consumo.setNombreProducto(rs.getString("NOMBREPRODUCTO"));
                    consumo.setValorUnitario(rs.getDouble("VALORUNITARIO"));

                    // Convertir la entidad a DTO
                    return Helper.parse(consumo);
                }
            }
        }
        return null;
    }

    /**
     * Actualiza la información de un consumo existente en la base de datos.
     *
     * @param consumoDto DTO con la información actualizada del consumo.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    @Override
    public void updateConsumo(ConsumoDto consumoDto) throws Exception {
        // Convertir el DTO a la entidad Consumo
        Consumo consumo = Helper.parse(consumoDto);

        String query = "UPDATE CONSUMO SET FECHACONSUMO = ?, NOMBREPRODUCTO = ?, VALORUNITARIO = ? WHERE IDCONSUMO = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setDate(1, new Date(consumo.getFechaConsumo().getTime()));
            preparedStatement.setString(2, consumo.getNombreProducto());
            preparedStatement.setDouble(3, consumo.getValorUnitario());
            preparedStatement.setLong(4, consumo.getIdConsumo());

            preparedStatement.executeUpdate();
        }
    }

    /**
     * Elimina un consumo de la base de datos.
     *
     * @param consumoDto DTO con la información del consumo a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    @Override
    public void deleteConsumo(ConsumoDto consumoDto) throws Exception {
        String query = "DELETE FROM CONSUMO WHERE IDCONSUMO = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, consumoDto.getIdConsumo());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Verifica si un consumo existe en la base de datos mediante su identificador.
     *
     * @param id Identificador del consumo.
     * @return true si el consumo existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    @Override
    public boolean existsById(long id) throws Exception {
        String query = "SELECT 1 FROM CONSUMO WHERE IDCONSUMO = ?";

        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setLong(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                return rs.next();
            }
        }
    }
}
