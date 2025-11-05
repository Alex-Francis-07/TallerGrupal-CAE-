package edu.unl.cc.servicio;

import edu.unl.cc.model.Ticket;
import java.util.*;

public class Reportes {

    /**
     * Devuelve los K tickets con más notas.
     */
    public static List<Ticket> topKPorNotas(List<Ticket> tickets, int k) {
        // Ordenar por tamaño de historialNotas
        tickets.sort((t1, t2) -> Integer.compare(
                t2.getHistorialNotas().listar().size(),
                t1.getHistorialNotas().listar().size()
        ));

        // Sublista con los primeros k
        return tickets.subList(0, Math.min(k, tickets.size()));
    }
}