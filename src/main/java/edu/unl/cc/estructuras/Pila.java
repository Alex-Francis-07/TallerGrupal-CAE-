package edu.unl.cc.estructuras;

import edu.unl.cc.model.Dato;

/**
 *
 * @author Mark
 */
public class Pila {
    private Node tope_pila;

    private static class Node {
        Dato dato;
        Node siguiente_nodo;
        Node(Dato dato) { this.dato = dato; }
    }

    public void push(Dato dato) {
        Node nuevoNode = new Node(dato);
        // Apunta al tope actual.
        nuevoNode.siguiente_nodo = tope_pila;
        //El nuevo nodo se pone en el tope.
        tope_pila = nuevoNode;
    }

    public Dato pop() {
        // Accion que pasa si la pila esta vacia.
        if (tope_pila == null) return null;
        Dato dato = tope_pila.dato;
        tope_pila = tope_pila.siguiente_nodo;
        return dato;
    }

    //Accion si el tope de la pila esta vacio o nulo.
    public boolean estaVacia() {
        return tope_pila == null;
    }
}