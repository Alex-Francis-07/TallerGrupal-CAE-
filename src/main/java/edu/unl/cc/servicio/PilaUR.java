package edu.unl.cc.servicio;

import edu.unl.cc.estructuras.Cola;
import edu.unl.cc.estructuras.Pila;
import edu.unl.cc.model.Dato;
import edu.unl.cc.model.Estado;
import edu.unl.cc.model.Nota;
import edu.unl.cc.model.Ticket;

/**
 *
 * @author Mark
 */
public class PilaUR {
    private Cola colaTickets;
    private Ticket ticketEnAtencion;
    private Pila pilaUndo;    // Accion para deshacer
    private Pila pilaRedo;    // Accion para rehacer

    public PilaUR() {
        this.colaTickets = new Cola();
        this.pilaUndo = new Pila();
        this.pilaRedo = new Pila();
    }

    public void agregarNota(String contenido) {
        if (ticketEnAtencion == null) return;

        Nota nuevaNota = new Nota(contenido);
        ticketEnAtencion.getHistorialNotas().insertarAlInicio(nuevaNota);

        // Accion para registrar acción para UNDO.
        Dato dato = new Dato("Agregar_Nota", ticketEnAtencion, null, nuevaNota);
        pilaUndo.push(dato);
        pilaRedo = new Pila(); // Accion para limpiar redo al hacer nueva acción.
    }

    public void cambiarEstado(Estado nuevoEstado) {
        if (ticketEnAtencion == null) return;

        Estado estadoAnterior = ticketEnAtencion.getEstado();
        ticketEnAtencion.setEstado(nuevoEstado);

        // Accion para registrar acción para UNDO.
        Dato dato = new Dato("Cambiar_Estado", ticketEnAtencion, estadoAnterior, nuevoEstado);
        pilaUndo.push(dato);
        pilaRedo = new Pila();
    }

    public void deshacer() {
        if (pilaUndo.estaVacia()) return;

        Dato dato = pilaUndo.pop();

        switch (dato.getTipoNota()) {
            case "Agregar_Nota":
                if (dato.getValorNuevo() instanceof Nota) {
                    // Accion que elimina la nota que se agrego.
                    Nota notaAgregada = (Nota) dato.getValorNuevo();
                    dato.getTicket().getHistorialNotas().eliminarPrimeraCoincidencia(notaAgregada.getContenido());
                }
            break;

            case "Cambiar_Estado":
                if (dato.getValorNuevo() instanceof Estado) {
                    // Accion que devuelve o restaura el estado anterior.
                    Estado estadoAnterior = (Estado) dato.getValorAnterior();
                    dato.getTicket().setEstado(estadoAnterior);
                }
            break;
        }

        // Accion para mover a pila REDO para poder rehacer.
        pilaRedo.push(dato);
    }

    public void rehacer() {
        if (pilaRedo.estaVacia()) return;

        Dato dato = pilaRedo.pop();

        switch (dato.getTipoNota()) {
            case "Agregar_Nota":
                if (dato.getValorNuevo() instanceof Nota) {
                    // Accion para volver a agregar la nota
                    Nota nota = (Nota) dato.getValorNuevo();
                    dato.getTicket().getHistorialNotas().insertarAlInicio(nota);
                }
            break;
            case "Cambiar_Estado":
                if (dato.getValorNuevo() instanceof Estado) {
                    // Accion para aplicar el estado nuevo nuevamente.
                    Estado estadoNuevo = (Estado) dato.getValorNuevo();
                    dato.getTicket().setEstado(estadoNuevo);

                }
            break;
        }

        // Accion para mover a pila UNDO.
        pilaUndo.push(dato);
    }

    public void atenderSiguienteTicket() {
        ticketEnAtencion = colaTickets.poll();
        if (ticketEnAtencion != null) {
            ticketEnAtencion.setEstado(Estado.EN_ATENCION);
        }
    }

    public Cola getCola() {
        return colaTickets;
    }

    public Ticket getTicketEnAtencion() {
        return ticketEnAtencion;
    }
}