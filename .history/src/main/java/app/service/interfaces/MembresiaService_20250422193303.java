package app.service.interfaces;

import app.dto.MembresiaDto;

/**
 * Interfaz para la gestión de membresías en el servicio.
 */
public interface MembresiaService {

    void createMembresia(MembresiaDto membresiaDto) throws Exception;

    MembresiaDto findMembresiaById(long id) throws Exception;

    void updateMembresia(MembresiaDto membresiaDto) throws Exception;

    void deleteMembresia(MembresiaDto membresiaDto) throws Exception;

    boolean existsById(long id) throws Exception;

}