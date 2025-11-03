package edu.unl.cc.model;

import edu.unl.cc.estructuras.Lista;
import java.util.List;

/**
 * Clase que representa un Ticket dentro del sistema CAE.
 */
public class Ticket {

    private int id;
    private String nombreEstudiante;
    private TipoTicket tipo;
    private Estado estado;
    private Lista historialNotas;

    // Constructor de la clase ticket
    public Ticket(int id, String nombreEstudiante, TipoTicket tipo) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.tipo = tipo;
        this.estado = Estado.EN_COLA;
        this.historialNotas = new Lista();
    }

    // Constructor alternativo por defecto
    public Ticket(int id) {
        this(id, "Desconocido", TipoTicket.NORMAL);
    }

    // GETTERS Y SETTERS
    public int getId() { return id; }

    public String getNombreEstudiante() { return nombreEstudiante; }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public TipoTicket getTipo() { return tipo; }

    public void setTipo(TipoTicket tipo) { this.tipo = tipo; }

    public Estado getEstado() { return estado; }

    public void setEstado(Estado estado) { this.estado = estado; }

    public Lista getHistorialNotas() { return historialNotas; }

    public List<String> getNotas() {
        return historialNotas.listar();
    }

    @Override
    public String toString() {
        return "Ticket #" + id +
                " | Estudiante: " + nombreEstudiante +
                " | Tipo: " + tipo +
                " | Estado: " + estado;
    }
}
