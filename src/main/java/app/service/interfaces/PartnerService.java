package app.service.interfaces;

import app.dto.PartnerDto;

/**
 * Servicio para la gestión de socios (partners).
 */
public interface PartnerService {

    /**
     * Crea un nuevo socio en el sistema.
     *
     * @param partnerDto DTO con la información del socio.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createPartner(PartnerDto partnerDto) throws Exception;

    /**
     * Elimina un socio del sistema.
     *
     * @param partnerDto DTO con la información del socio a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deletePartner(PartnerDto partnerDto) throws Exception;

    /**
     * Busca un socio en la base de datos asociado a un usuario específico.
     *
     * @param partnerDto DTO con el ID del usuario a buscar.
     * @return {@link PartnerDto} con la información del socio, o {@code null} si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    PartnerDto findPartnerByUserId(PartnerDto partnerDto) throws Exception;

    /**
     * Actualiza la información de un socio en la base de datos.
     *
     * @param partnerDto DTO con la nueva información del socio.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updatePartner(PartnerDto partnerDto) throws Exception;

    /**
     * Verifica si un socio existe en la base de datos mediante su ID de usuario.
     *
     * @param userId ID del usuario asociado al socio.
     * @return true si el socio existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsPartnerByDocument(long document) throws Exception;
}
