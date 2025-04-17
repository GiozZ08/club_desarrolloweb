package app.service.interfaces;

import app.dto.ConsumoDto;

/**
 * Interfaz para la gestión de consumos en el servicio.
 */
public interface ConsumoService {

    /**
     * Crea un nuevo consumo.
     *
     * @param consumoDto DTO con la información del consumo a crear.
     * @throws Exception Si ocurre un error durante la creación.
     */
    void createConsumo(ConsumoDto consumoDto) throws Exception;

    /**
     * Busca un consumo por su identificador.
     *
     * @param id Identificador del consumo.
     * @return ConsumoDto con la información del consumo encontrado, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    ConsumoDto findConsumoById(long id) throws Exception;

    /**
     * Actualiza un consumo existente.
     *
     * @param consumoDto DTO con la información actualizada del consumo.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updateConsumo(ConsumoDto consumoDto) throws Exception;

    /**
     * Elimina un consumo.
     *
     * @param consumoDto DTO con la información del consumo a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteConsumo(ConsumoDto consumoDto) throws Exception;

    /**
     * Verifica si un consumo existe por su identificador.
     *
     * @param id Identificador del consumo.
     * @return true si el consumo existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsById(long id) throws Exception;
}
