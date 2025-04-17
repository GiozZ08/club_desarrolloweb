package app.dao.interfaces;

import app.dto.MembresiaDto;

/**
 * Interfaz para la gestión de membresías en la base de datos.
 */
public interface MembresiaDao {

    /**
     * Crea una nueva membresía en la base de datos.
     *
     * @param membresiaDto DTO que contiene la información de la membresía a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createMembresia(MembresiaDto membresiaDto) throws Exception;

    /**
     * Busca una membresía en la base de datos mediante su identificador.
     *
     * @param id Identificador de la membresía.
     * @return MembresiaDto con la información de la membresía encontrada, o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    MembresiaDto findMembresiaById(long id) throws Exception;

    /**
     * Actualiza la información de una membresía existente en la base de datos.
     *
     * @param membresiaDto DTO con la información actualizada de la membresía.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updateMembresia(MembresiaDto membresiaDto) throws Exception;

    /**
     * Elimina una membresía de la base de datos.
     *
     * @param membresiaDto DTO que contiene la información de la membresía a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteMembresia(MembresiaDto membresiaDto) throws Exception;

    /**
     * Verifica si una membresía existe en la base de datos mediante su identificador.
     *
     * @param id Identificador de la membresía.
     * @return true si la membresía existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsById(long id) throws Exception;
}
