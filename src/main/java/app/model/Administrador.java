package app.model;

public class Administrador extends Person {
    // Atributos específicos del administrador
    private String nivelAcceso; // Nivel de acceso (ej: alto, medio, bajo)
    private String departamento; // Departamento al que pertenece (ej: finanzas, recursos humanos)

    // Constructor sin parámetros
    public Administrador() {
    }

    // Constructor con parámetros
    public Administrador(long id, long document, String name, String userName, String password, String userType, String nivelAcceso, String departamento) {
        super(id, document, name, userName, password, userType);
        this.nivelAcceso = nivelAcceso;
        this.departamento = departamento;
    }

    // Getters y Setters para atributos específicos del administrador
    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}