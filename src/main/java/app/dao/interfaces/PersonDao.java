package app.dao.interfaces;

import app.dto.AdministradorDto;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;

/**
 * Interfaz para la gestión de personas en la base de datos.
 */
public interface PersonDao {

   /**
     * Busca una persona en la base de datos mediante su nombre de usuario.
     *
     * @param userName Nombre de usuario de la persona a buscar.
     * @return PersonDto con la información de la persona si se encuentra, null en caso contrario.
     * @throws Exception Si ocurre un error en la consulta.
     */
    PersonDto findByUserName(String userName) throws Exception;

    /**
     * Actualiza la contraseña de una persona en la base de datos.
     *
     * @param personDto DTO con la información de la persona y la nueva contraseña.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void updatePersonPassword(PersonDto personDto) throws Exception;


    /**
     * Verifica si una persona existe en la base de datos mediante su documento de identidad.
     *
     * @param personDto DTO con el documento de la persona a verificar.
     * @return true si la persona existe, false en caso contrario.
     * @throws Exception Si ocurre un error en la consulta.
     */
    boolean existsByDocument(PersonDto personDto) throws Exception;

    /**
     * Registra una nueva persona en la base de datos.
     *
     * @param personDto DTO con la información de la persona a registrar.
     * @throws Exception Si ocurre un error en la inserción.
     */
    void createPerson(PersonDto personDto) throws Exception;

    /**
     * Elimina una persona de la base de datos mediante su documento de identidad.
     *
     * @param personDto DTO con la información de la persona a eliminar.
     * @throws Exception Si ocurre un error en la eliminación.
     */
    void deletePerson(PersonDto personDto) throws Exception;

    /**
     * Busca una persona en la base de datos mediante su documento de identidad.
     *
     * @param personDto DTO con el documento de la persona a buscar.
     * @return PersonDto con la información de la persona si se encuentra, null en caso contrario.
     * @throws Exception Si ocurre un error en la consulta.
     */
    PersonDto findByDocument(PersonDto personDto) throws Exception;


      /**
     * Crea un nuevo administrador en la base de datos.
     *
     * @param administradorDto DTO con la información del administrador a crear.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createAdministrador(AdministradorDto administradorDto) throws Exception;

    /**
     * Elimina un administrador de la base de datos.
     *
     * @param administradorDto DTO que contiene la información del administrador a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deleteAdministrador(AdministradorDto administradorDto) throws Exception;

    /**
     * Verifica si un administrador existe en la base de datos mediante su nombre de usuario.
     *
     * @param userName DTO que contiene el nombre de usuario del administrador.
     * @return true si el administrador existe, false en caso contrario.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    boolean existsByUserName(String userName) throws Exception;

    /**
     * Busca un administrador en la base de datos mediante su documento de identidad.
     *
     * @param administradorDto DTO que contiene el documento del administrador a buscar.
     * @return AdministradorDto con la información del administrador encontrado o null si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    AdministradorDto findByDocument(AdministradorDto administradorDto) throws Exception;

     /**
     * Crea un nuevo invitado en la base de datos.
     *
     * @param guestDto DTO con la información del invitado.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createGuest(GuestDto guestDto) throws Exception;

    /**
     * Busca un socio asociado a un usuario específico.
     *
     * @param partnerDto DTO con el ID del usuario a buscar.
     * @return {@link PartnerDto} con la información del socio, o {@code null} si no existe.
     * @throws Exception Si ocurre un error durante la consulta.
     */
    PartnerDto findPartnerByUserId(PartnerDto partnerDto) throws Exception;

    /**
     * Deshabilita un invitado en la base de datos.
     *
     * @param guestDto DTO con la información del invitado a deshabilitar.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void disableGuest(GuestDto guestDto) throws Exception;

    /**
     * Habilita un invitado en la base de datos.
     *
     * @param guestDto DTO con la información del invitado a habilitar.
     * @throws Exception Si ocurre un error durante la actualización.
     */
    void enableGuest(GuestDto guestDto) throws Exception;

       /**
     * Crea un nuevo socio en la base de datos.
     *
     * @param partnerDto DTO con la información del socio.
     * @throws Exception Si ocurre un error durante la inserción.
     */
    void createPartner(PartnerDto partnerDto) throws Exception;

    /**
     * Elimina un socio de la base de datos.
     *
     * @param partnerDto DTO con la información del socio a eliminar.
     * @throws Exception Si ocurre un error durante la eliminación.
     */
    void deletePartner(PartnerDto partnerDto) throws Exception;

/**
 * Actualiza la información de un socio en la base de datos.
 *
 * @param partnerDto DTO con la información actualizada del socio.
 * @throws Exception Si ocurre un error durante la actualización.
 */
void updatePartner(PartnerDto partnerDto) throws Exception;

/**
 * Verifica si un socio existe en la base de datos mediante su documento de identidad.
 *
 * @param document Documento del socio a verificar.
 * @return true si el socio existe, false en caso contrario.
 * @throws Exception Si ocurre un error durante la consulta.
 */
boolean existsPartnerByDocument(long document) throws Exception;


}



