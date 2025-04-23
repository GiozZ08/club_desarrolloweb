package app.service.interfaces;

import app.dto.AdministradorDto;
import app.dto.GuestDto;
import app.dto.PersonDto;

/**
 * Servicio unificado para gestionar personas y sus operaciones relacionadas en el sistema.
 * Esto incluye operaciones generales para Person y métodos específicos para administradores,
 * socios e invitados, además de la gestión de facturas y detalles de factura.
 */
public interface PersonService {

    // Operaciones generales para personas (todos los tipos de usuario)
    void agregarPersona(PersonDto person) throws Exception;
    void eliminarPersona(long id) throws Exception;
    PersonDto obtenerPersonaPorId(long id) throws Exception;
  
    
    // Operaciones específicas para administradores (se espera que el objeto tenga userType="ADMIN")
    void agregarAdministrador(AdministradorDto administrador) throws Exception;
    void eliminarAdministrador(long id) throws Exception;
 
    
    
    // Operaciones específicas para invitados (userType="INVITADO")
    void agregarInvitado(GuestDto invitado) throws Exception;
    void eliminarInvitado(long id) throws Exception;

    
   
    // Operaciones de autenticación y actualización de datos sensibles
    PersonDto login(long document, String password) throws Exception;
    void updatePersonPassword(PersonDto person) throws Exception;

    
  
}

