package edu.unl.cc.estructuras;

import edu.unl.cc.model.Nota;

import java.util.ArrayList;
import java.util.List;

/**
 * Lista Enlazada Simple para notas de un ticket.
 * Operaciones: insertar al inicio, eliminar primera coincidencia por contenido, listar.
 * Diseñada para integrarse con pilas (e.g., apilar acciones en PilaUR para undo/redo).
 */
public class Lista {
    private Nodo<Nota> cabeza;

    public Lista() {
        this.cabeza = null;
    }

    /**
     * Inserta una nota al inicio de la lista (O(1)).
     * @param nota La nota a insertar.
     */
    public void insertarAlInicio(Nota nota) {
        Nodo<Nota> nuevoNodo = new Nodo<>(nota);
        nuevoNodo.setSiguiente(cabeza);
        cabeza = nuevoNodo;
    }

    /**
     * Elimina la primera coincidencia de la nota por contenido (O(n)).
     * @param contenido El contenido de la nota a eliminar.
     * @return true si se eliminó, false si no existe.
     */
    public boolean eliminarPrimeraCoincidencia(String contenido) {
        if (cabeza == null) {
            return false; // Lista vacía
        }
        if (cabeza.getDato().getContenido().equals(contenido)) {
            cabeza = cabeza.getSiguiente(); // Eliminar cabeza
            return true;
        }
        Nodo<Nota> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().getContenido().equals(contenido)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente()); // Eliminar el siguiente
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false; // No encontrado
    }

    /**
     * Recorre la lista y retorna una lista de strings con las notas (para consulta).
     * @return Lista de contenidos de notas, o lista vacía si no hay notas.
     */
    public List<String> listar() {
        List<String> notas = new ArrayList<>();
        Nodo<Nota> actual = cabeza;
        while (actual != null) {
            notas.add(actual.getDato().toString());
            actual = actual.getSiguiente();
        }
        return notas;
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si está vacía.
     */
    public boolean estaVacia() {
        return cabeza == null;
    }
}