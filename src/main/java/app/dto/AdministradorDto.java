package app.dto;

public class AdministradorDto extends PersonDto {
    private String nivelAcceso;
    private String departamento;

    // Constructor sin parámetros
    public AdministradorDto() {
    }

    // Constructor con parámetros
    public AdministradorDto(long id, long document, String name, String userName, String password, String role, String nivelAcceso, String departamento) {
        super(id, document, name, userName, password, role);
        this.nivelAcceso = nivelAcceso;
        this.departamento = departamento;
    }

    // Getters y Setters
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
