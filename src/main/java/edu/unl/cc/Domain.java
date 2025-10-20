package edu.unl.cc;

public class Domain {
    private PilaUR gestor;
    private int contadorTickets;

    public Domain() {
        this.gestor = new PilaUR();
        this.contadorTickets = 1;
    }

    public void recibirTicket() {
        Ticket nuevo = new Ticket(contadorTickets++);
        gestor.getCola().offer(nuevo);
        System.out.println("Ticket recibido: " + nuevo);
    }

    public void atenderTicket() {
        gestor.atenderSiguienteTicket();
        System.out.println("Atendiendo ticket...");
    }

    public void agregarNota(String contenido) {
        gestor.agregarNota(contenido);
        System.out.println("Nota agregada.");
    }

    public void cambiarEstado(Estado nuevoEstado) {
        gestor.cambiarEstado(nuevoEstado);
        System.out.println("Estado cambiado a: " + nuevoEstado);
    }

    public void deshacer() {
        gestor.deshacer();
        System.out.println("Última acción deshecha.");
    }

    public void rehacer() {
        gestor.rehacer();
        System.out.println("Última acción rehecha.");
    }

    public void mostrarCola() {
        gestor.getCola().mostrar();
    }

    public void mostrarHistorial() {
        Ticket t = gestor.getTicketEnAtencion();
        if (t != null) {
            System.out.println("Historial de Ticket #" + t.getId());
            for (String nota : t.getHistorialNotas().listar()) {
                System.out.println(" - " + nota);
            }
        } else {
            System.out.println("No hay ticket en atención.");
        }
    }
}
