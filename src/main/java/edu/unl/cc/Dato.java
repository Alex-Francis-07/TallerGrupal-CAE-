package edu.unl.cc;
/**
 *
 * @author Mark
 */
public class Dato {
    private String tipoNota; // Tipos de notas.
    private Ticket ticket; // Referencia al ticket que fue modificado.
    private Object valorAnterior; // Estado anterior o nota eliminada.
    private Object valorNuevo;   // Estado nuevo o nota agregada.

    // Constructor.
    public Dato(String tipoNota, Ticket ticket, Object valorAnterior, Object valorNuevo) {
        this.tipoNota = tipoNota;
        this.ticket = ticket;
        this.valorAnterior = valorAnterior;
        this.valorNuevo = valorNuevo;
    }

    // Getters
    public String getTipoNota() {
        return tipoNota;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public Object getValorAnterior() {
        return valorAnterior;
    }

    public Object getValorNuevo() {
        return valorNuevo;
    }
}
