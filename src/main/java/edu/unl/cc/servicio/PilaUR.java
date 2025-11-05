package edu.unl.cc.servicio;

import edu.unl.cc.estructuras.Cola;
import edu.unl.cc.estructuras.Pila;
import edu.unl.cc.model.Dato;
import edu.unl.cc.model.Estado;
import edu.unl.cc.model.Nota;
import edu.unl.cc.model.Ticket;

/**
 * Gestor principal del sistema con undo/redo
 */
public class PilaUR {
    private Cola colaTickets;
    private Ticket ticketEnAtencion;
    private Pila pilaUndo;
    private Pila pilaRedo;

    public PilaUR() {
        this.colaTickets = new Cola();
        this.pilaUndo = new Pila();
        this.pilaRedo = new Pila();
    }

    public void agregarNota(String contenido) {
        if (ticketEnAtencion == null) {
            System.out.println("Error: No hay ticket en atención.");
            return;
        }

        try {
            Nota nuevaNota = new Nota(contenido);
            ticketEnAtencion.getHistorialNotas().insertarAlInicio(nuevaNota);

            // Registrar acción para UNDO
            Dato dato = new Dato("Agregar_Nota", ticketEnAtencion, null, nuevaNota);
            pilaUndo.push(dato);
            pilaRedo = new Pila(); // Limpiar redo al hacer nueva acción
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void cambiarEstado(Estado nuevoEstado) {
        if (ticketEnAtencion == null) {
            System.out.println("Error: No hay ticket en atención.");
            return;
        }

        try {
            Estado estadoAnterior = ticketEnAtencion.getEstado();

            // Validar transición de estado
            if (!esTransicionValida(estadoAnterior, nuevoEstado)) {
                System.out.println("Error: Transición de estado no válida.");
                return;
            }

            ticketEnAtencion.setEstado(nuevoEstado);

            // Registrar acción para UNDO
            Dato dato = new Dato("Cambiar_Estado", ticketEnAtencion, estadoAnterior, nuevoEstado);
            pilaUndo.push(dato);
            pilaRedo = new Pila();
        } catch (Exception e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
        }
    }

    /**
     * Valida transiciones de estado según reglas de negocio
     */
    private boolean esTransicionValida(Estado actual, Estado nuevo) {
        // Reglas básicas de transición
        switch (actual) {
            case EN_COLA:
                return nuevo == Estado.EN_ATENCION;
            case EN_ATENCION:
                return nuevo == Estado.EN_PROCESO || nuevo == Estado.PENDIENTE_DOCS;
            case EN_PROCESO:
                return nuevo == Estado.PENDIENTE_DOCS || nuevo == Estado.COMPLETADO;
            case PENDIENTE_DOCS:
                return nuevo == Estado.EN_PROCESO || nuevo == Estado.COMPLETADO;
            case COMPLETADO:
                return false; // No se puede cambiar desde COMPLETADO
            default:
                return false;
        }
    }

    public void deshacer() {
        if (pilaUndo.estaVacia()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }

        try {
            Dato dato = pilaUndo.pop();

            switch (dato.getTipoNota()) {
                case "Agregar_Nota":
                    if (dato.getValorNuevo() instanceof Nota) {
                        Nota notaAgregada = (Nota) dato.getValorNuevo();
                        boolean eliminado = dato.getTicket().getHistorialNotas()
                                .eliminarPrimeraCoincidencia(notaAgregada.getContenido());
                        if (!eliminado) {
                            System.out.println("Advertencia: No se pudo encontrar la nota para eliminar.");
                        }
                    }
                    break;

                case "Cambiar_Estado":
                    if (dato.getValorAnterior() instanceof Estado) {
                        Estado estadoAnterior = (Estado) dato.getValorAnterior();
                        dato.getTicket().setEstado(estadoAnterior);
                    }
                    break;
            }

            // Mover a pila REDO para poder rehacer
            pilaRedo.push(dato);
            System.out.println("Acción deshecha correctamente.");
        } catch (Exception e) {
            System.out.println("Error al deshacer: " + e.getMessage());
        }
    }

    public void rehacer() {
        if (pilaRedo.estaVacia()) {
            System.out.println("No hay acciones para rehacer.");
            return;
        }

        try {
            Dato dato = pilaRedo.pop();

            switch (dato.getTipoNota()) {
                case "Agregar_Nota":
                    if (dato.getValorNuevo() instanceof Nota) {
                        Nota nota = (Nota) dato.getValorNuevo();
                        dato.getTicket().getHistorialNotas().insertarAlInicio(nota);
                    }
                    break;
                case "Cambiar_Estado":
                    if (dato.getValorNuevo() instanceof Estado) {
                        Estado estadoNuevo = (Estado) dato.getValorNuevo();
                        if (esTransicionValida(dato.getTicket().getEstado(), estadoNuevo)) {
                            dato.getTicket().setEstado(estadoNuevo);
                        } else {
                            System.out.println("No se puede rehacer: transición de estado no válida.");
                        }
                    }
                    break;
            }

            // Mover a pila UNDO
            pilaUndo.push(dato);
            System.out.println("Acción rehecha correctamente.");
        } catch (Exception e) {
            System.out.println("Error al rehacer: " + e.getMessage());
        }
    }

    public void atenderSiguienteTicket() {
        try {
            Ticket siguiente = colaTickets.poll();
            if (siguiente != null) {
                if (ticketEnAtencion != null) {
                    System.out.println("Terminando atención del ticket actual: " + ticketEnAtencion.getId());
                }
                ticketEnAtencion = siguiente;
                ticketEnAtencion.setEstado(Estado.EN_ATENCION);
                System.out.println("Atendiendo nuevo ticket: " + ticketEnAtencion);
            } else {
                System.out.println("No hay tickets en la cola para atender.");
            }
        } catch (Exception e) {
            System.out.println("Error al atender ticket: " + e.getMessage());
        }
    }

    public Cola getCola() {
        return colaTickets;
    }

    public Ticket getTicketEnAtencion() {
        return ticketEnAtencion;
    }
}