package app.service;


import app.dao.interfaces.PersonDao;
import app.dto.PersonDto;
import app.helpers.PasswordHelper;
import app.service.interfaces.LoginService;

public class LoginServiceImpl implements LoginService {

    private final PersonDao personDao;

    public LoginServiceImpl(PersonDao personDao) {
        this.personDao = personDao;
    }

    /**
     * Autentica a un usuario utilizando su nombre de usuario y contraseña.
     * Se utiliza findByUserName de PersonDao para buscar el usuario.
     * @throws Exception 
     */
    @Override
    public PersonDto autenticar(String userName, String password) throws Exception {
        // Nota: La interfaz original devuelve Person, pero aquí trabajamos con PersonDto.
        // Se puede convertir o ajustar la interfaz según se requiera.
        PersonDto personDto = personDao.findByUserName(userName); // This may throw an exception
        if (personDto != null && personDto.getPassword().equals(PasswordHelper.hashPassword(password))) {
            throw new RuntimeException("Credenciales incorrectas");
        }
        return personDto; // Autenticación correcta
    }



    /**
     * Cierra la sesión del usuario autenticado.
     */
    @Override
    public void cerrarSesion() {
        System.out.println("Sesión cerrada correctamente.");
    }

    /**
     * Autentica a un usuario utilizando su documento y contraseña.
     * Se busca al usuario por documento y se valida la contraseña.
     */
    @Override
    public PersonDto login(long document, String password) throws Exception {
        // Se crea un PersonDto con el documento para la búsqueda.
        PersonDto searchDto = new PersonDto();
        searchDto.setDocument(document);
        PersonDto person = personDao.findByDocument(searchDto);
        if (person != null && person.getPassword().equals(PasswordHelper.hashPassword(password))) {
            return person;
        }
        throw new Exception("Credenciales incorrectas");
    }

    /**
     * Actualiza la contraseña de una persona.
     */
    @Override
    public void updatePersonPassword(PersonDto person) throws Exception {
        // Se delega a PersonDao la actualización de la contraseña.
        personDao.updatePersonPassword(person);
    }
}
