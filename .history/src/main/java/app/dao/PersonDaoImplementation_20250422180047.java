package app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import app.config.MYSQLConnection;
import app.dao.interfaces.PersonDao;
import app.dto.AdministradorDto;
import app.dto.GuestDto;
import app.dto.PartnerDto;
import app.dto.PersonDto;
import app.helpers.Helper;
import app.model.Person;

/**
 * Implementación de {@link PersonDao} para manejar la persistencia de personas en la base de datos.
 */
public class PersonDaoImplementation implements PersonDao {

    @Override
    public boolean existsByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT 1 FROM PERSON WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, personDto.getDocument());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public void findGuestById(GuestDto guestDto) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT FROM GUEST WHERE ID = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, guestDto.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    guestDto.setName(resultSet.getString("NAME"));
                    guestDto.setDocument(resultSet.getLong("DOCUMENT"));
                }
            }
        }
    }

    @Override
    public PersonDto findById(PersonDto personDto) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT FROM PERSON WHERE ID = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, personDto.getId());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getLong("ID"));
                    person.setName(resultSet.getString("NAME"));
                    person.setDocument(resultSet.getLong("DOCUMENT"));
                    return Helper.parse(person);
                }
            }
        }
        return null;
    }

    @Override
    public void createPerson(PersonDto personDto) throws Exception {
        Person person = Helper.parse(personDto);
        String query = "INSERT INTO PERSON (NAME, DOCUMENT) VALUES (?, ?)";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setLong(2, person.getDocument());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deletePerson(PersonDto personDto) throws Exception {
        String query = "DELETE FROM PERSON WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, personDto.getDocument());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public PersonDto findByDocument(PersonDto personDto) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT FROM PERSON WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, personDto.getDocument());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getLong("ID"));
                    person.setName(resultSet.getString("NAME"));
                    person.setDocument(resultSet.getLong("DOCUMENT"));
                    return Helper.parse(person);
                }
            }
        }
        return null;
    }

    /**
     * Busca una persona en la base de datos mediante su nombre de usuario.
     */
    @Override
    public PersonDto findByUserName(String userName) throws Exception {
        String query = "SELECT ID, NAME, DOCUMENT, USERNAME, PASSWORD FROM PERSON WHERE USERNAME = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Person person = new Person();
                    person.setId(resultSet.getLong("ID"));
                    person.setName(resultSet.getString("NAME"));
                    person.setDocument(resultSet.getLong("DOCUMENT"));
                    person.setUserName(resultSet.getString("USERNAME"));
                    person.setPassword(resultSet.getString("PASSWORD"));
                    return Helper.parse(person);
                }
            }
        }
        return null;
    }

    /**
     * Actualiza la contraseña de una persona en la base de datos.
     */
    @Override
    public void updatePersonPassword(PersonDto personDto) throws Exception {
        String query = "UPDATE PERSON SET PASSWORD = ? WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, personDto.getPassword());
            preparedStatement.setLong(2, personDto.getDocument());
            preparedStatement.executeUpdate();
        }
    }
/** 
 * actualiza el nombre de una persona en la base de datos.
 */
    @Override
    public void updatePerson(PersonDto personDto) throws Exception {
        String query = "UPDATE PERSON SET NAME = ? WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, personDto.getName());
            preparedStatement.setLong(2, personDto.getDocument());
            preparedStatement.executeUpdate();
        }
    }


    /**
     * Actualiza la información de un invitado en la base de datos.
     */
    @Override
    public void updateGuest(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET NAME = ?, DOCUMENT = ? WHERE ID = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, guestDto.getName());
            preparedStatement.setLong(2, guestDto.getDocument());
            preparedStatement.setLong(3, guestDto.getId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * elimina un invitado en la base de datos mediante su ID.
     */
    @Override
    public void deleteGuest(GuestDto guestDto) throws Exception {
        String query = "DELETE FROM GUEST WHERE ID = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, guestDto.getId());
            preparedStatement.executeUpdate();
        }
    }

 
    

    @Override
    public void createAdministrador(AdministradorDto administradorDto) throws Exception {
        String query = "INSERT INTO ADMINISTRADOR (NAME, DOCUMENT, USERNAME) VALUES (?, ?, ?)";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, administradorDto.getName());
            preparedStatement.setLong(2, administradorDto.getDocument());
            preparedStatement.setString(3, administradorDto.getUserName());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void deleteAdministrador(AdministradorDto administradorDto) throws Exception {
        String query = "DELETE FROM ADMINISTRADOR WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, administradorDto.getDocument());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean existsByUserName(String userName) throws Exception {
        String query = "SELECT 1 FROM ADMINISTRADOR WHERE USERNAME = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userName);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public void enableGuest(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET ENABLED = TRUE WHERE ID = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, guestDto.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void disableGuest(GuestDto guestDto) throws Exception {
        String query = "UPDATE GUEST SET ENABLED = FALSE WHERE ID = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, guestDto.getId());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updatePartner(PartnerDto partnerDto) throws Exception {
        String query = "UPDATE PERSON SET NAME = ?, DOCUMENT = ? WHERE DOCUMENT = ?";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, partnerDto.getName());
            preparedStatement.setLong(2, partnerDto.getDocument());
            preparedStatement.setLong(3, partnerDto.getDocument());
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public boolean existsPartnerByDocument(long document) throws Exception {
        String query = "SELECT 1 FROM PERSON WHERE DOCUMENT = ? AND USER_TYPE = 'PARTNER'";
        try (Connection connection = MYSQLConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, document);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    @Override
    public AdministradorDto findByDocument(AdministradorDto administradorDto) throws Exception {
        String sql = "SELECT * FROM Administrador WHERE document = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, administradorDto.getDocument());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new AdministradorDto(
                        rs.getLong("id"),
                        rs.getLong("document"),
                        rs.getString("name"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("nivelAcceso"),
                        rs.getString("departamento")
                );
            }
        }
        return null; // Si no se encuentra el administrador
    }

    @Override
    public void createGuest(GuestDto guestDto) throws Exception {
        String query = "INSERT INTO Guest (document, name, userName, password, tipoUsuario, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, guestDto.getDocument());
            stmt.setString(2, guestDto.getName());
            stmt.setString(3, guestDto.getUserName());
            stmt.setString(4, guestDto.getPassword());
            stmt.setString(5, guestDto.getUserType());
            stmt.executeUpdate();
        }
    }
    @Override
    public PartnerDto findPartnerByUserId(PartnerDto partnerDto) throws Exception {
        String query = "SELECT id, document, name, userName, password, userType, idMembresia FROM Partner WHERE userName = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, partnerDto.getUserName());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new PartnerDto(
                        rs.getLong("id"),
                        rs.getLong("document"),
                        rs.getString("name"),
                        rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("userType"),
                        rs.getLong("idMembresia")
                    );
                }
            }
        }
        return null;
    }
    

    @Override
    public void createPartner(PartnerDto partnerDto) throws Exception {
        String query = "INSERT INTO Partner (document, name, userName, password, userType, idMembresia) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            // Utilizamos los getters de PartnerDto (heredados de PersonDto para document, name, userName, password, userType)
            stmt.setLong(1, partnerDto.getDocument());
            stmt.setString(2, partnerDto.getName());
            stmt.setString(3, partnerDto.getUserName());
            stmt.setString(4, partnerDto.getPassword());
            stmt.setString(5, partnerDto.getUserType());
            stmt.setLong(6, partnerDto.getIdMembresia());
            
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void deletePartner(PartnerDto partnerDto) throws Exception {
        // Se asume que la clave primaria en la tabla Partner es "id"
        String query = "DELETE FROM Partner WHERE id = ?";
        try (Connection conn = MYSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setLong(1, partnerDto.getId());
            stmt.executeUpdate();
        }
    }


    
}
