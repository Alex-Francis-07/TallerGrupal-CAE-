package edu.unl.cc.servicio;

import edu.unl.cc.estructuras.Cola;
import edu.unl.cc.model.Estado;
import edu.unl.cc.model.Ticket;
import edu.unl.cc.model.TipoTicket;

public class Domain {
    private PilaUR gestor;
    private int contadorTickets;

    public Domain() {
        this.gestor = new PilaUR();
        this.contadorTickets = 1;
    }

    public Ticket recibirTicket(String nombre, TipoTicket tipo, String descripcion) {
        Ticket nuevo = new Ticket(contadorTickets++, nombre, tipo, descripcion);
        gestor.getCola().offer(nuevo);
        System.out.println("Ticket recibido: " + nuevo);
        return nuevo;
    }

    public Cola getCola() {
        return gestor.getCola();
    }

    public void setTicketEnAtencion(Ticket t) {
        gestor.setTicketEnAtencion(t);
    }

    public Ticket getTicketEnAtencion() {
        return gestor.getTicketEnAtencion();
    }

    public void atenderTicket() {
        gestor.atenderSiguienteTicket();
    }

    public void agregarNota(String contenido) {
        gestor.agregarNota(contenido);
    }

    public void cambiarEstado(Estado nuevoEstado) {
        gestor.cambiarEstado(nuevoEstado);
    }

    public void deshacer() {
        gestor.deshacer();
    }

    public void rehacer() {
        gestor.rehacer();
    }

    public void mostrarCola() {
        gestor.getCola().mostrar();
    }

    public void mostrarHistorial() {
        Ticket t = getTicketEnAtencion();
        if (t != null) {
            System.out.println("Historial de Ticket #" + t.getId() + " - " + t.getNombreEstudiante());
            System.out.println("Tipo: " + t.getTipo() + " | Estado: " + t.getEstado());
            System.out.println("Descripción: " + t.getDescripcion());
            System.out.println("Notas:");
            for (String nota : t.getHistorialNotas().listar()) {
                System.out.println(" - " + nota);
            }
        } else {
            System.out.println("No hay ticket en atención.");
        }
    }
}