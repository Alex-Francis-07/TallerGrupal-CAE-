package edu.unl.cc;

import java.util.List;

/**
 * Clase para representar un ticket de trámite.
 * Incluye historial de notas (Lista) y estado.
 */
public class Ticket {
    private int id;
    private Estado estado;
    private Lista historialNotas;

    public Ticket(int id) {
        this.id = id;
        this.estado = Estado.EN_COLA;
        this.historialNotas = new Lista();
    }

    public int getId() { return id; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Lista getHistorialNotas() { return historialNotas; }

    @Override
    public String toString() {
        return "Ticket #" + id + " - Estado: " + estado;
    }

    public List<String> getNotas() {
        return historialNotas.listar();
    }


}