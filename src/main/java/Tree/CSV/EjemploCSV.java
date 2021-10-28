package Tree.CSV;

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
 *
 * @author Carolina Rodriguez
 * @author Kendall Marin
 */
public class EjemploCSV {

    int y = 25;

    public static void main(String[] args) {
        List<Usuario> usuarios = new ArrayList<Usuario>();

        new Frame_historial();

        ExportarCSV(usuarios);

    }

    /**
     * Método para exportatr el archivp CVS
     *
     * @author Carolina Rodriguez
     * @author Kendall Marin
     */
    public static void ExportarCSV(List<Usuario> usuarios) {
        String salidaArchivo = "Usuarios.csv"; // Nombre del archivo
        boolean existe = new File(salidaArchivo).exists(); // Verifica si existe

        // Si existe un archivo llamado asi lo borra
        if (existe) {
            File archivoUsuarios = new File(salidaArchivo);
            /* archivoUsuarios.delete();*/
        }
        try {
            // Crea el archivo
            CsvWriter salidaCSV = new CsvWriter(new FileWriter(salidaArchivo, true), ';');

            // Datos para identificar las columnas
            /*salidaCSV.write("Fecha");
            salidaCSV.write("Operacion");
            salidaCSV.write("Respuesta");*/
            // Deja de escribir en el archivo

            // Recorremos la lista y lo insertamos en el archivo
            for (Usuario user : usuarios) {
                salidaCSV.write(user.getNombre());
                salidaCSV.write(user.getExpresion());
                salidaCSV.write(user.getResultado());
                salidaCSV.write(user.getFecha());

                salidaCSV.endRecord(); // Deja de escribir en el archivo
            }

            salidaCSV.close(); // Cierra el archivo

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
