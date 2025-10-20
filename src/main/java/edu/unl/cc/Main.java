package edu.unl.cc;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Domain sistema = new Domain();
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- CAE - Centro de Atención ---");
            System.out.println("1. Recibir nuevo ticket");
            System.out.println("2. Atender siguiente ticket");
            System.out.println("3. Agregar nota");
            System.out.println("4. Cambiar estado");
            System.out.println("5. Deshacer");
            System.out.println("6. Rehacer");
            System.out.println("7. Mostrar cola");
            System.out.println("8. Mostrar historial");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    sistema.recibirTicket();
                    break;
                case 2:
                    sistema.atenderTicket();
                    break;
                case 3:
                    System.out.print("Contenido de la nota: ");
                    String nota = sc.nextLine();
                    sistema.agregarNota(nota);
                    break;
                case 4:
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
                        System.out.println("Estado inválido.");
                    }
                    break;
                case 5:
                    sistema.deshacer();
                    break;
                case 6:
                    sistema.rehacer();
                    break;
                case 7:
                    sistema.mostrarCola();
                    break;
                case 8:
                    sistema.mostrarHistorial();
                    break;
            }
        } while (opcion != 0);

        sc.close();
        System.out.println("Sistema finalizado.");
    }
}