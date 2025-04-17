package app.model;

public class Partner extends Person{
    // Atributos

  
    private long  idMembresia; // IDMembresia (FK que referencia a Membresia)


    // Constructor sin parámetros
    public Partner() {
    }

    // Constructor con parámetros
    public Partner(long id, long document, String name, String userName, String password, String userType, long idMembresia) {
        super(id, document, name, userName, password, userType);

        this.idMembresia = idMembresia;
    }

    // Getters y Setters

    public long getIdMembresia() {
        return idMembresia;
    }

    public void setIdMembresia(long idMembresia) {
        this.idMembresia = idMembresia;
    }

    
    @Override
    public String toString() {
        return "PartnerDto{" +
                "id=" + getId() +
                ", document=" + getDocument() +
                ", name='" + getName() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", userType='" + getUserType() + '\'' +
                ", idMembresia=" + idMembresia +
                '}';
    }
    

}