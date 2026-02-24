package es.iesquevedo.ui;

import es.iesquevedo.dao.LibroRepository;
import es.iesquevedo.dao.PrestamoRepository;
import es.iesquevedo.dao.SocioRepository;
import es.iesquevedo.service.LibroService;
import es.iesquevedo.service.PrestamoService;
import es.iesquevedo.service.SocioService;

import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    LibroRepository librorepo =new LibroRepository();
    SocioRepository sociorepo =new SocioRepository();
    PrestamoRepository prestamorepo =new PrestamoRepository();

    LibroService libroService =new LibroService(librorepo);
    SocioService socioService =new SocioService(sociorepo);
    PrestamoService prestamoService =new PrestamoService(prestamorepo,librorepo,sociorepo);

    private static final ConsoleController controller = new ConsoleController(sc,libroService,socioService,prestamoService);

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
