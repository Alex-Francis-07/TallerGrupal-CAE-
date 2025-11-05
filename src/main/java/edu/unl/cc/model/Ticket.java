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
    private String descripcion; // NUEVO CAMPO

    // Constructor de la clase ticket
    public Ticket(int id, String nombreEstudiante, TipoTicket tipo, String descripcion) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = Estado.EN_COLA;
        this.historialNotas = new Lista();
    }

    // Constructor alternativo
    public Ticket(int id) {
        this(id, "Desconocido", TipoTicket.NORMAL, "Sin descripción");
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

    public String getDescripcion() { return descripcion; } // NUEVO GETTER

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; } // NUEVO SETTER

    public Lista getHistorialNotas() { return historialNotas; }

    public List<String> getNotas() {
        return historialNotas.listar();
    }

    @Override
    public String toString() {
        return "Ticket #" + id +
                " | Estudiante: " + nombreEstudiante +
                " | Tipo: " + tipo +
                " | Estado: " + estado +
                " | Descripción: " + descripcion; // INCLUIR DESCRIPCIÓN
    }
}