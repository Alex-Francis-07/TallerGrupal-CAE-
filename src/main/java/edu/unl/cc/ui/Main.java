package edu.unl.cc.ui;

import edu.unl.cc.model.Estado;
import edu.unl.cc.model.TipoTicket;
import edu.unl.cc.servicio.Domain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Domain sistema = new Domain();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- CAE - Centro de Atención al Estudiante ---");
            System.out.println("1. Recibir nuevo ticket");
            System.out.println("2. Atender siguiente ticket");
            System.out.println("3. Agregar nota al ticket en atención");
            System.out.println("4. Cambiar estado del ticket");
            System.out.println("5. Deshacer última acción");
            System.out.println("6. Rehacer última acción");
            System.out.println("7. Mostrar cola de tickets");
            System.out.println("8. Mostrar historial del ticket en atención");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            try {
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1 -> {
                        System.out.print("Nombre del estudiante: ");
                        String nombre = sc.nextLine();

                        System.out.print("Tipo de ticket (NORMAL o URGENTE): ");
                        String tipoStr = sc.nextLine().toUpperCase();

                        System.out.print("Descripción del problema: ");
                        String descripcion = sc.nextLine();

                        try {
                            TipoTicket tipo = TipoTicket.valueOf(tipoStr);
                            sistema.recibirTicket(nombre, tipo, descripcion);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Tipo inválido. Use NORMAL o URGENTE.");
                        }
                    }
                    case 2 -> sistema.atenderTicket();
                    case 3 -> {
                        System.out.print("Contenido de la nota: ");
                        String nota = sc.nextLine();
                        sistema.agregarNota(nota);
                    }
                    case 4 -> {
                        System.out.println("Estados disponibles:");
                        for (Estado e : Estado.values()) {
                            System.out.println(" - " + e);
                        }
                        System.out.print("Nuevo estado: ");
                        String estadoStr = sc.nextLine().toUpperCase();
                        try {
                            Estado estado = Estado.valueOf(estadoStr);
                            sistema.cambiarEstado(estado);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: Estado inválido.");
                        }
                    }
                    case 5 -> sistema.deshacer();
                    case 6 -> sistema.rehacer();
                    case 7 -> sistema.mostrarCola();
                    case 8 -> sistema.mostrarHistorial();
                    case 0 -> System.out.println("Saliendo del sistema...");
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: Entrada inválida. Intente nuevamente.");
                sc.nextLine(); // Limpiar buffer en caso de error
                opcion = -1;
            }
        } while (opcion != 0);

        sc.close();
        System.out.println("Sistema finalizado.");
    }
}