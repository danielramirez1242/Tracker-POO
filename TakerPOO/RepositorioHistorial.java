import java.io.*;
import java.util.Scanner;
public class RepositorioHistorial {
    private final String ruta = "historial_entrenamientos.txt";
    public void guardarSesion(SesionEntrenamiento sesion) {
        try (FileWriter w = new FileWriter(ruta, true)) {
            w.write("SESION|" + sesion.getFecha() + "|" + sesion.calcularVolumenTotalSesion() + "\n");
            for (Entreno e : sesion.getEntrenos()) w.write(e.aFormatoArchivo() + "\n");
        } catch (IOException ex) {
            System.out.println("Error guardando: " + ex.getMessage());
        }
    }
}