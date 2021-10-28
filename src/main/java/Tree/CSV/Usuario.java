
package Tree.CSV;

/**
 * M�todo para exportatr el archivp CVS
 * @author Carolina Rodriguez
 * @author Kendall Marin
 */


public class Usuario {
    public String nombre;
    private String resultado;
    private String expresion;
    private String fecha;

    public Usuario(String nombre, String expresion, String resultado, String fecha) {
        this.nombre = nombre;
        this.expresion = expresion;
        this.resultado = resultado;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExpresion() {
        return expresion;
    }

    public void setExpresion(String expresion) {
        this.expresion = expresion;
    }
    
    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }


}



