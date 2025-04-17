package  app.model;



public class Person{
    // Atributos de Persona
    private long id; // ID (PK)
    private long document; // Documento de identidad
    private String name; // Nombre
    // Atributos de User
    private String userName; // Nombre de usuario
    private String password; // Contraseña
    private String userType; // Rol del usuario (ej: admin, socio, invitado)

    // Constructor sin parámetros
    public Person() {
    }

    // Constructor con parámetros
    public Person(long id, long document, String name, String userName, String password, String userType) {
        this.id = id;
        this.document = document;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = userType;  // Se usa "role" en lugar de "userType"
    }

    // Getters y Setters para atributos de Persona
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDocument() {
        return document;
    }

    public void setDocument(long document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters y Setters para atributos de User
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}