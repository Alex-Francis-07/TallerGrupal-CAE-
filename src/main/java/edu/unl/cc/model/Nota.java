package edu.unl.cc.model;

/**
 * Clase que representa una nota (observación) en un ticket.
 * Inmutable para evitar corrupciones en la lista.
 */
public class Nota {
    private final String contenido;

    public Nota(String contenido) {
        if (contenido == null || contenido.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido de la nota no puede ser nulo o vacío.");
        }
        this.contenido = contenido;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Nota nota = (Nota) obj;
        return contenido.equals(nota.contenido);
    }

    @Override
    public int hashCode() {
        return contenido.hashCode();
    }

    @Override
    public String toString() {
        return contenido;
    }
}