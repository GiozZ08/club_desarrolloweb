package app.dao.interfaces;

import app.dto.ConsumoDto;

/**
 * Interfaz para la gestión de consumos en la base de datos.
 */
public interface ConsumoDao {

    /**
     * Crea un nuevo consumo en la base de datos.
     *
     * @param consumoDto DTO que contiene la información del consumo a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createConsumo(ConsumoDto consumoDto) throws Exception;

    /**
     * Busca un consumo en la base de datos mediante su identificador.
     *
     * @param id Identificador del consumo.
     * @return ConsumoDto con la información del consumo encontrado, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    ConsumoDto findConsumoById(long id) throws Exception;

    /**
     * Actualiza la información de un consumo existente en la base de datos.
     *
     * @param consumoDto DTO con la información actualizada del consumo.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updateConsumo(ConsumoDto consumoDto) throws Exception;

    /**
     * Elimina un consumo de la base de datos.
     *
     * @param consumoDto DTO con la información del consumo a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteConsumo(ConsumoDto consumoDto) throws Exception;

    /**
     * Verifica si un consumo existe en la base de datos mediante su identificador.
     *
     * @param id Identificador del consumo.
     * @return true si el consumo existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsById(long id) throws Exception;
}
