package edu.unl.cc.servicio;

import edu.unl.cc.model.Ticket;
import edu.unl.cc.model.TipoTicket;
import edu.unl.cc.model.Estado;
import edu.unl.cc.model.Nota;

import java.io.*;
import java.util.*;

public class Persistencia {

    public static void guardar(List<Ticket> tickets, String archivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Ticket t : tickets) {
                pw.println(t.getId() + ";" + t.getNombreEstudiante() + ";" +
                        t.getTipo() + ";" + t.getEstado() + ";" +
                        t.getDescripcion());
                for (String nota : t.getHistorialNotas().listar()) {
                    pw.println("NOTA:" + nota);
                }
                pw.println("---"); // separador
            }
            System.out.println("Datos guardados en " + archivo);
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    /**
     * Carga tickets desde archivo y además los inserta en la cola de Domain.
     */
    public static List<Ticket> cargar(String archivo, Domain sistema) {
        List<Ticket> tickets = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            Ticket actual = null;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue; // 🔹 Ignorar líneas vacías

                if (linea.startsWith("NOTA:")) {
                    if (actual != null) {
                        actual.agregarNota(linea.substring(5)); // usa método seguro
                    }
                } else if (linea.equals("---")) {
                    if (actual != null) {
                        tickets.add(actual);

                        // Reinserta según estado
                        if (actual.getEstado() == Estado.EN_COLA) {
                            sistema.getCola().offer(actual);
                        } else if (actual.getEstado() == Estado.EN_ATENCION) {
                            sistema.setTicketEnAtencion(actual);
                        }

                        actual = null;
                    }
                } else {
                    String[] partes = linea.split(";");
                    if (partes.length < 5) {
                        System.out.println("Línea inválida en archivo: " + linea);
                        continue;
                    }

                    try {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        TipoTicket tipo = TipoTicket.valueOf(partes[2]);
                        Estado estado = Estado.valueOf(partes[3]);
                        String descripcion = partes[4];
                        actual = new Ticket(id, nombre, tipo, descripcion);
                        actual.setEstado(estado);
                    } catch (Exception e) {
                        System.out.println("Error al procesar línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar archivo: " + e.getMessage());
        }
        return tickets;
    }
}