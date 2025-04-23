package app.service;



import app.dao.interfaces.PersonDao;
import app.dto.AdministradorDto;
import app.dto.GuestDto;
import app.dto.PersonDto;
import app.service.interfaces.PersonService;

/**
 * Implementación de {@link PersonService} que orquesta la lógica de negocio
 * y delega la persistencia en {@link PersonDao}.
 */
public class PersonServiceImpl implements PersonService {

    private final PersonDao personDao;

    public PersonServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    // --- Operaciones generales para PersonDto ---

    @Override
    public void agregarPersona(PersonDto person) throws Exception {
        if (personDao.existsByDocument(person)) {
            throw new Exception("La cédula ya está registrada");
        }
        personDao.createPerson(person);
    }


    @Override
    public void eliminarPersona(long document) throws Exception {
        PersonDto dto = new PersonDto();
        dto.setDocument(document);
        personDao.deletePerson(dto);
    }

    @Override
    public PersonDto obtenerPersonaPorId(long document) throws Exception {
        PersonDto dto = new PersonDto();
        dto.setDocument(document);
        return personDao.findByDocument(dto);
    }

    @Override
    public void updatePerson(PersonDto person) throws Exception {
        if (personDao.existsByDocument(person)) {
            throw new Exception("La cédula ya está registrada");
        }
        personDao.updatePerson(person);
    }
    @Override
    public PersonDto findByUserName(String userName) throws Exception {
        return personDao.findByUserName(userName);
    }


    // --- Operaciones específicas para administradores ---

    @Override
    public void agregarAdministrador(AdministradorDto administrador) throws Exception {
        if (personDao.existsByUserName(administrador.getUserName())) {
            throw new Exception("El nombre de usuario ya existe");
        }
        personDao.createAdministrador(administrador);
    }

 
    @Override
    public void eliminarAdministrador(long document) throws Exception {
        AdministradorDto dto = new AdministradorDto();
        dto.setDocument(document);
        personDao.deleteAdministrador(dto);
    }




    // --- Operaciones específicas para invitados ---

    @Override
    public void agregarInvitado(GuestDto invitado) throws Exception {
        personDao.createGuest(invitado);
    }


    @Override
    public void eliminarInvitado(long id) throws Exception {
        GuestDto dto = new GuestDto();
        dto.setId(id);
        personDao.disableGuest(dto);
    }

    @Override
    public void disableGuest(GuestDto guest) throws Exception {
        personDao.disableGuest(guest);
    }

    @Override
    public void enableGuest(GuestDto guest) throws Exception {
        personDao.enableGuest(guest);
    }
    @Override
    public void updateGuest(GuestDto guest) throws Exception {
        personDao.updateGuest(guest);
    }
    @Override
    public GuestDto findGuestById(long id) throws Exception {
        GuestDto dto = new GuestDto();
        dto.setId(id);
        return personDao.findGuestById(dto);
    }

  

 

    // --- Autenticación y datos sensibles ---

    @Override
    public PersonDto login(long document, String password) throws Exception {
        PersonDto dto = new PersonDto();
        dto.setDocument(document);
        PersonDto person = personDao.findByDocument(dto);
        if (person == null || !person.getPassword().equals(password)) {
            throw new Exception("Credenciales incorrectas");
        }
        return person;
    }

    @Override
    public void updatePersonPassword(PersonDto person) throws Exception {
        personDao.updatePersonPassword(person);
    }

  
}
