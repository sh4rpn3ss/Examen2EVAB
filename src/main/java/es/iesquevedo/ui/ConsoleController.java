package es.iesquevedo.ui;

import es.iesquevedo.modelo.Prestamo;
import es.iesquevedo.modelo.Libro;
import es.iesquevedo.modelo.Socio;
import es.iesquevedo.service.PrestamoService;
import es.iesquevedo.service.LibroService;
import es.iesquevedo.service.SocioService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleController {
    private final Scanner sc;
    private final LibroService libroService;
    private final SocioService socioService;
    private final PrestamoService prestamoService;

    public ConsoleController(Scanner sc) {
        this.sc = sc;
        this.libroService = new LibroService();
        this.socioService = new SocioService();
        this.prestamoService = new PrestamoService();
    }

    // Menús delegados desde Main
    public void menuLibros() {
        System.out.println("-- Libros --");
        System.out.println("1) Alta libro");
        System.out.println("2) Baja libro");
        System.out.println("3) Listar libros");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> altaLibro();
            case "2" -> bajaLibro();
            case "3" -> listarLibros();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void altaLibro() {
        System.out.print("Título: ");
        String titulo = sc.nextLine().trim();
        System.out.print("Autor: ");
        String autor = sc.nextLine().trim();
        System.out.print("ISBN: ");
        String isbn = sc.nextLine().trim();
        String id = UUID.randomUUID().toString();
        Libro l = new Libro(id, titulo, autor, isbn);
        if (libroService.altaLibro(l)) System.out.println("Libro dado de alta con id=" + id);
        else System.out.println("No se pudo crear el libro (id duplicado o datos inválidos)");
    }

    public void bajaLibro() {
        System.out.print("Id libro a eliminar: ");
        String id = sc.nextLine().trim();
        if (libroService.bajaLibro(id)) System.out.println("Libro eliminado");
        else System.out.println("No existe libro con ese id");
    }

    public void listarLibros() {
        List<Libro> ls = libroService.listar();
        if (ls.isEmpty()) System.out.println("No hay libros");
        else ls.forEach(System.out::println);
    }

    public void menuSocios() {
        System.out.println("-- Socios --");
        System.out.println("1) Alta socio");
        System.out.println("2) Baja socio");
        System.out.println("3) Listar socios");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> altaSocio();
            case "2" -> bajaSocio();
            case "3" -> listarSocios();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void altaSocio() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Documento (DNI/email): ");
        String doc = sc.nextLine().trim();
        String id = UUID.randomUUID().toString();
        Socio s = new Socio(id, nombre, doc);
        if (socioService.altaSocio(s)) System.out.println("Socio creado con id=" + id);
        else System.out.println("No se pudo crear el socio (id duplicado o datos inválidos)");
    }

    public void bajaSocio() {
        System.out.print("Id socio a eliminar: ");
        String id = sc.nextLine().trim();
        if (socioService.bajaSocio(id)) System.out.println("Socio eliminado");
        else System.out.println("No existe socio con ese id");
    }

    public void listarSocios() {
        List<Socio> ls = socioService.listar();
        if (ls.isEmpty()) System.out.println("No hay socios");
        else ls.forEach(System.out::println);
    }

    public void menuPrestamos() {
        System.out.println("-- Préstamos --");
        System.out.println("1) Crear préstamo");
        System.out.println("2) Finalizar préstamo");
        System.out.println("3) Listar préstamos");
        System.out.println("0) Volver");
        System.out.print("Opción: ");
        String opt = sc.nextLine().trim();
        switch (opt) {
            case "1" -> crearPrestamo();
            case "2" -> finalizarPrestamo();
            case "3" -> listarPrestamos();
            case "0" -> {
            }
            default -> System.out.println("Opción no válida");
        }
    }

    public void crearPrestamo() {
        System.out.print("Id libro: ");
        String libroId = sc.nextLine().trim();
        System.out.print("Id socio: ");
        String socioId = sc.nextLine().trim();
        System.out.print("Fecha inicio (yyyy-MM-dd): ");
        String fi = sc.nextLine().trim();
        System.out.print("Fecha fin (yyyy-MM-dd): ");
        String ff = sc.nextLine().trim();
        try {
            LocalDate inicio = LocalDate.parse(fi);
            LocalDate fin = LocalDate.parse(ff);
            if (fin.isBefore(inicio)) { System.out.println("Fecha fin anterior a inicio"); return; }
            String id = UUID.randomUUID().toString();
            Prestamo p = new Prestamo(id, libroId, socioId, inicio, fin);
            if (prestamoService.crearPrestamo(p)) System.out.println("Préstamo creado con id=" + id);
            else System.out.println("No se pudo crear el préstamo (libro no existe/disponible o socio no existe)");
        } catch (Exception e) {
            System.out.println("Formato de fecha inválido, usa yyyy-MM-dd");
        }
    }

    public void finalizarPrestamo() {
        System.out.print("Id préstamo a finalizar: ");
        String id = sc.nextLine().trim();
        if (prestamoService.finalizarPrestamo(id)) System.out.println("Préstamo finalizado");
        else System.out.println("No existe préstamo con ese id");
    }

    public void listarPrestamos() {
        List<Prestamo> ls = prestamoService.listar();
        if (ls.isEmpty()) System.out.println("No hay préstamos");
        else ls.forEach(System.out::println);
    }
}
