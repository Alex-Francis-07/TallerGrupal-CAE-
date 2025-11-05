package edu.unl.cc.model;

import edu.unl.cc.estructuras.Lista;
import java.util.List;

public class Ticket {
    private int id;
    private String nombreEstudiante;
    private TipoTicket tipo;
    private Estado estado;
    private Lista historialNotas;
    private String descripcion;

    public Ticket(int id, String nombreEstudiante, TipoTicket tipo, String descripcion) {
        this.id = id;
        this.nombreEstudiante = nombreEstudiante;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = Estado.EN_COLA;
        this.historialNotas = new Lista();
    }

    public Ticket(int id) {
        this(id, "Desconocido", TipoTicket.NORMAL, "Sin descripción");
    }

    public Lista getHistorialNotas() {
        return historialNotas;
    }

    public List<String> getNotas() {
        return historialNotas.listar(); // debe existir en tu clase Lista
    }

    public void agregarNota(String contenido) {
        historialNotas.insertarAlInicio(new Nota(contenido)); // usa tu estructura actual
    }

    // Getters y setters existentes
    public int getId() { return id; }

    public String getNombreEstudiante() { return nombreEstudiante; }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public TipoTicket getTipo() { return tipo; }

    public void setTipo(TipoTicket tipo) { this.tipo = tipo; }

    public Estado getEstado() { return estado; }

    public void setEstado(Estado estado) { this.estado = estado; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    @Override
    public String toString() {
        return "Ticket #" + id +
                " | Estudiante: " + nombreEstudiante +
                " | Tipo: " + tipo +
                " | Estado: " + estado +
                " | Descripción: " + descripcion;
    }
}