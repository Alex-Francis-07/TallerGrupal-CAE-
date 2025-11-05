package edu.unl.cc.servicio;

import edu.unl.cc.model.Ticket;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Exportador {

    public static void exportarTxt(List<Ticket> tickets, String nombreArchivo) {
        try (FileWriter writer = new FileWriter(nombreArchivo, false)) {
            for (Ticket t : tickets) {
                writer.write(t.toString() + "\n");
                for (String nota : t.getHistorialNotas().listar()) {
                    writer.write("   - " + nota + "\n");
                }
            }
            System.out.println("Reporte exportado a " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }
}