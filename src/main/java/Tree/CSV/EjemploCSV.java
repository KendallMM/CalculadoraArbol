
package Tree.CSV;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Tree.CSV.Frame_historial.panel;

/**
 * Clase Main del archivo CSV
 * @author Carolina Rodriguez
 * @author Kendall Marin
 */
public class EjemploCSV {
    int y = 25;

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        
        //usuarios.add(new Usuario("R", "1234567", "com"));
      //usuarios.add(new Usuario("iiiiiiiiiii", "y67", "com"));


        new Frame_historial();

        ExportarCSV(usuarios);

        ImportarCSV(panel);
    }
    /**
     * Método para exportatr el archivp CVS
     * @author Carolina Rodriguez
     * @author Kendall Marin
     */
    public static void ExportarCSV(List<Usuario> usuarios) {
        String salidaArchivo = "Usuarios.csv"; // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe
        
        // Si existe un archivo llamado asi lo borra
        if(existe) {
            File archivoUsuarios = new File(salidaArchivo);
           /* archivoUsuarios.delete();*/
        }
        try {
            // Crea el archivo
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true), ',');
            
            // Datos para identificar las columnas
            /*salidaCSV.write("Fecha");
            salidaCSV.write("Operacion");
            salidaCSV.write("Respuesta");*/
            
            salidaCSV.endRecord(); // Deja de escribir en el archivo
            
            // Recorremos la lista y lo insertamos en el archivo
            for(Usuario user : usuarios) {
                salidaCSV.write(user.getNombre());
                salidaCSV.write(user.getTelefono());
                salidaCSV.write(user.getEmail());
                
                salidaCSV.endRecord(); // Deja de escribir en el archivo
            }
            
            salidaCSV.close(); // Cierra el archivo
            
        } catch(IOException e) {
            e.printStackTrace();
        }    
    }

    public static void ImportarCSV(JPanel p) {
        /**
         * Método para importar el archivo CVS
         * @author Carolina Rodriguez
         * @author Kendall Marin
         */
        try{
            List<Usuario> usuarios = new ArrayList<Usuario>(); // Lista donde guardaremos los datos del archivo
            
            CsvReader leerUsuarios = new CsvReader("Usuarios.csv");
            leerUsuarios.readHeaders();
            
            // Mientras haya lineas obtenemos los datos del archivo
            while(leerUsuarios.readRecord()) {
                String nombre = leerUsuarios.get(0);
                String telefono = leerUsuarios.get(1);
                String email = leerUsuarios.get(2);
                
                usuarios.add(new Usuario(nombre, telefono, email)); // Añade la informacion a la lista
            }
            leerUsuarios.close(); // Cierra el archivo
            
            // Recorremos la lista y la mostramos en la pantalla
            int y = 25;
            for(Usuario user : usuarios) {

                JLabel historial;
                historial = new JLabel(user.getNombre() + " , " + user.getTelefono() + " , " +user.getEmail()+ "    ");
                historial.setFont(new Font("MV Boli", Font.PLAIN, 15));
                historial.setBounds(25, y, 1000, 40);
                y = y +25;
                panel.add(historial);
                System.out.println(user.getNombre() + " , "
                    + user.getTelefono() + " , "
                    +user.getEmail());
            }
            
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
