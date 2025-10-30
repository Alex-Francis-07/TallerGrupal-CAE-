package edu.unl.cc.model;

import edu.unl.cc.estructuras.Lista;
import java.util.List;

/**
 * Lo nuevo que hay: harán bien chucha
 *  - Nombre del estudiante
 *  - Tipo de ticket (NORMAL o URGENTE)
 *  - Estado actual del ticket (EN_COLA/URGENTE → EN_ATENCION → EN_PROCESO/PENDIENTE_DOCS → COMPLETADO)
 */
public class Ticket {

    // ATRIBUTOS
    private int id;
    private String nombreEstudiante;
    private TipoTicket tipo;
    private Estado estado;
    private Lista historialNotas;

    /**
     * Constructor de la clase Ticket.
     *
     * @param id Identificador único del ticket
     */
    public Ticket(int id) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.tipo = tipo;
        this.estado = Estado.EN_COLA;
        this.historialNotas = new Lista();
    }

    // METODO GETTERS Y SETTERS
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

    /**
     * Retorna una lista con todas las notas del historial.
     */
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