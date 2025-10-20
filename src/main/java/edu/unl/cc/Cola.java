package edu.unl.cc;

public class Cola {
    private Nodo<Ticket> frente;
    private Nodo<Ticket> fin;
    private int tamaño;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Inserta una nota al final de la cola haciendo uso de la funcion vista en clase (offer/enqueue)
    public void offer(Ticket ticket) {
        Nodo<Ticket> nuevo = new Nodo<>(ticket);
        if (isEmpty()) {
            frente = fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
        tamaño++;
    }

    // Elimina y devuelve la nota del frente basado en lo visto en clase(poll/dequeue)
    public Ticket poll() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        Ticket ticket = frente.getDato();
        frente = frente.getSiguiente();
        tamaño--;
        if (frente == null) {
            fin = null;
        }
        return ticket;
    }

    // Esta funcion devuelve el frente de la cola sin eliminar el elemento (peek)
    public Ticket peek() {
        return isEmpty() ? null : frente.getDato();
    }

    // Indica si la cola no contiene elementos
    public boolean isEmpty() {
        return frente == null;
    }

    // Esta funcion Devuelve el tamaño actual de la cola
    public int size() {
        return tamaño;
    }

    // Muestra el contenido de la cola
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("No hay tickets en la cola");
            return;
        }
        Nodo<Ticket> actual = frente;
        System.out.println("\nTickets en cola");
        while (actual != null) {
            System.out.println(" - " + actual.getDato());
            actual = actual.getSiguiente();
        }
    }


}
