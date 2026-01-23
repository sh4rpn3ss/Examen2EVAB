package es.iesquevedo.ui;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ConsoleController controller = new ConsoleController(sc);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("--- Biblioteca de Préstamos de Libros ---");
            System.out.println("1) Gestionar libros");
            System.out.println("2) Gestionar socios");
            System.out.println("3) Gestionar préstamos");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> controller.menuLibros();
                case "2" -> controller.menuSocios();
                case "3" -> controller.menuPrestamos();
                case "0" -> running = false;
                default -> System.out.println("Opción no válida");
            }
        }
        System.out.println("Adiós");
    }
}
