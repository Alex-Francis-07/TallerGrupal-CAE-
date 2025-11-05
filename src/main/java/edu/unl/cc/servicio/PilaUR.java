package edu.unl.cc.servicio;

import edu.unl.cc.estructuras.Cola;
import edu.unl.cc.estructuras.Pila;
import edu.unl.cc.model.Dato;
import edu.unl.cc.model.Estado;
import edu.unl.cc.model.Nota;
import edu.unl.cc.model.Ticket;

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

    public void setTicketEnAtencion(Ticket t) {
        this.ticketEnAtencion = t;
    }

    public Ticket getTicketEnAtencion() {
        return ticketEnAtencion;
    }

    public Cola getCola() {
        return colaTickets;
    }

    public void atenderSiguienteTicket() {
        try {
            Ticket siguiente = colaTickets.poll();
            if (siguiente != null) {
                if (ticketEnAtencion != null) {
                    // 🔹 Marcar el ticket anterior como COMPLETADO
                    ticketEnAtencion.setEstado(Estado.COMPLETADO);
                    System.out.println("Ticket #" + ticketEnAtencion.getId() + " marcado como COMPLETADO.");
                }

                // 🔹 Atender el nuevo ticket
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


    public void agregarNota(String contenido) {
        if (ticketEnAtencion == null) {
            System.out.println("Error: No hay ticket en atención.");
            return;
        }

        try {
            Nota nuevaNota = new Nota(contenido);
            ticketEnAtencion.agregarNota(contenido);

            Dato dato = new Dato("Agregar_Nota", ticketEnAtencion, null, nuevaNota);
            pilaUndo.push(dato);
            pilaRedo = new Pila();
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
            if (!esTransicionValida(estadoAnterior, nuevoEstado)) {
                System.out.println("Error: Transición de estado no válida.");
                return;
            }

            ticketEnAtencion.setEstado(nuevoEstado);
            Dato dato = new Dato("Cambiar_Estado", ticketEnAtencion, estadoAnterior, nuevoEstado);
            pilaUndo.push(dato);
            pilaRedo = new Pila();
        } catch (Exception e) {
            System.out.println("Error al cambiar estado: " + e.getMessage());
        }
    }

    private boolean esTransicionValida(Estado actual, Estado nuevo) {
        return switch (actual) {
            case EN_COLA -> nuevo == Estado.EN_ATENCION;
            case EN_ATENCION -> nuevo == Estado.EN_PROCESO || nuevo == Estado.PENDIENTE_DOCS;
            case EN_PROCESO -> nuevo == Estado.PENDIENTE_DOCS || nuevo == Estado.COMPLETADO;
            case PENDIENTE_DOCS -> nuevo == Estado.EN_PROCESO || nuevo == Estado.COMPLETADO;
            case COMPLETADO -> false;
        };
    }

    public void deshacer() {
        if (pilaUndo.estaVacia()) {
            System.out.println("No hay acciones para deshacer.");
            return;
        }

        try {
            Dato dato = pilaUndo.pop();
            switch (dato.getTipoNota()) {
                case "Agregar_Nota" -> {
                    Nota notaAgregada = (Nota) dato.getValorNuevo();
                    boolean eliminado = dato.getTicket().getHistorialNotas()
                            .eliminarPrimeraCoincidencia(notaAgregada.getContenido());
                    if (!eliminado) {
                        System.out.println("Advertencia: No se pudo encontrar la nota para eliminar.");
                    }
                }
                case "Cambiar_Estado" -> {
                    Estado estadoAnterior = (Estado) dato.getValorAnterior();
                    dato.getTicket().setEstado(estadoAnterior);
                }
            }
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
                case "Agregar_Nota" -> {
                    Nota nota = (Nota) dato.getValorNuevo();
                    dato.getTicket().agregarNota(nota.getContenido());
                }
                case "Cambiar_Estado" -> {
                    Estado estadoNuevo = (Estado) dato.getValorNuevo();
                    if (esTransicionValida(dato.getTicket().getEstado(), estadoNuevo)) {
                        dato.getTicket().setEstado(estadoNuevo);
                    } else {
                        System.out.println("No se puede rehacer: transición de estado no válida.");
                    }
                }
            }
            pilaUndo.push(dato);
            System.out.println("Acción rehecha correctamente.");
        } catch (Exception e) {
            System.out.println("Error al rehacer: " + e.getMessage());
        }
    }
}