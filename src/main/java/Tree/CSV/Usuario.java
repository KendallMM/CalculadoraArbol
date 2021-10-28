
package Tree.CSV;

/**
 * Método para exportatr el archivp CVS
 * @author Carolina Rodriguez
 * @author Kendall Marin
 */


public class Usuario {
    public String nombre;
    private String telefono;
    private String email;

    public Usuario(String nombre, String telefono, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}



