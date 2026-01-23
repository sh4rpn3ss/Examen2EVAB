package es.iesquevedo.service;

import es.iesquevedo.modelo.Prestamo;
import es.iesquevedo.modelo.Libro;
import es.iesquevedo.modelo.Socio;
import es.iesquevedo.dao.PrestamoRepository;
import es.iesquevedo.dao.LibroRepository;
import es.iesquevedo.dao.SocioRepository;

import java.util.List;
import java.util.Optional;

public class PrestamoService {
    private final PrestamoRepository prestamoRepo;
    private final LibroRepository libroRepo;
    private final SocioRepository socioRepo;

    public PrestamoService() {
        this.prestamoRepo = new PrestamoRepository();
        this.libroRepo = new LibroRepository();
        this.socioRepo = new SocioRepository();
    }

    public boolean crearPrestamo(Prestamo a) {
        if (a.getId() == null || a.getId().isBlank()) return false;
        Optional<Libro> libro = libroRepo.findById(a.getLibroId());
        if (libro.isEmpty()) return false;
        if (!libro.get().isDisponible()) return false;
        Optional<Socio> socio = socioRepo.findById(a.getSocioId());
        if (socio.isEmpty()) return false;
        // titulor libro no disponible
        Libro c = libro.get();
        c.setDisponible(false);
        libroRepo.update(c);
        return prestamoRepo.create(a);
    }

    public boolean finalizarPrestamo(String id) {
        Optional<Prestamo> a = prestamoRepo.findById(id);
        if (a.isEmpty()) return false;
        // titulor libro disponible
        prestamoRepo.deleteById(id);
        Optional<Libro> c = libroRepo.findById(a.get().getLibroId());
        c.ifPresent(co -> { co.setDisponible(true); libroRepo.update(co); });
        return true;
    }

    public List<Prestamo> listar() {
        return prestamoRepo.findAll();
    }

    public Optional<Prestamo> buscarPorId(String id) {
        return prestamoRepo.findById(id);
    }
}
