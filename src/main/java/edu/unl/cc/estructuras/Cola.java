package edu.unl.cc.estructuras;

import edu.unl.cc.model.Ticket;
import edu.unl.cc.model.TipoTicket;

public class Cola {

    private Nodo<Ticket> frenteNormal;
    private Nodo<Ticket> finNormal;
    private Nodo<Ticket> frenteUrgente;
    private Nodo<Ticket> finUrgente;
    private int tamañoNormal;
    private int tamañoUrgente;

    public Cola() {
        this.frenteNormal = this.finNormal = null;
        this.frenteUrgente = this.finUrgente = null;
        this.tamañoNormal = this.tamañoUrgente = 0;
    }

    /**
     * Inserta un ticket al final de la cola correspondiente
     * según su tipo (NORMAL o URGENTE).
     */
    public void offer(Ticket ticket) {
        Nodo<Ticket> nuevo = new Nodo<>(ticket);
        if (ticket.getTipo() == TipoTicket.URGENTE) {
            if (isEmptyUrgente()) {
                frenteUrgente = finUrgente = nuevo;
            } else {
                finUrgente.setSiguiente(nuevo);
                finUrgente = nuevo;
            }
            tamañoUrgente++;
        } else {
            if (isEmptyNormal()) {
                frenteNormal = finNormal = nuevo;
            } else {
                finNormal.setSiguiente(nuevo);
                finNormal = nuevo;
            }
            tamañoNormal++;
        }
    }

    /**
     * Devuelve y elimina el ticket del frente de la cola,
     * priorizando los tickets URGENTES.
     */
    public Ticket poll() {
        if (!isEmptyUrgente()) {
            Ticket t = frenteUrgente.getDato();
            frenteUrgente = frenteUrgente.getSiguiente();
            tamañoUrgente--;
            if (frenteUrgente == null) finUrgente = null;
            return t;
        } else if (!isEmptyNormal()) {
            Ticket t = frenteNormal.getDato();
            frenteNormal = frenteNormal.getSiguiente();
            tamañoNormal--;
            if (frenteNormal == null) finNormal = null;
            return t;
        } else {
            System.out.println("La cola está vacía.");
            return null;
        }
    }

    /**
     * Devuelve el siguiente ticket a atender (sin eliminarlo).
     * Se priorizan los tickets urgentes.
     */
    public Ticket peek() {
        if (!isEmptyUrgente()) return frenteUrgente.getDato();
        if (!isEmptyNormal()) return frenteNormal.getDato();
        return null;
    }

    public boolean isEmpty() {
        return isEmptyUrgente() && isEmptyNormal();
    }

    private boolean isEmptyUrgente() {
        return frenteUrgente == null;
    }

    private boolean isEmptyNormal() {
        return frenteNormal == null;
    }

    /**
     * Devuelve el tamaño total (urgentes + normales).
     */
    public int size() {
        return tamañoNormal + tamañoUrgente;
    }

    /**
     * Muestra todo el contenido de la cola,
     * primero los urgentes (FIFO),
     * luego los normales (FIFO).
     */
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("No hay tickets en la cola.");
            return;
        }

        System.out.println("\n--- Tickets URGENTES ---");
        if (isEmptyUrgente()) {
            System.out.println(" (ninguno)");
        } else {
            Nodo<Ticket> actual = frenteUrgente;
            while (actual != null) {
                System.out.println(" - " + actual.getDato());
                actual = actual.getSiguiente();
            }
        }

        System.out.println("\n--- Tickets NORMALES ---");
        if (isEmptyNormal()) {
            System.out.println(" (ninguno)");
        } else {
            Nodo<Ticket> actual = frenteNormal;
            while (actual != null) {
                System.out.println(" - " + actual.getDato());
                actual = actual.getSiguiente();
            }
        }
    }
}
