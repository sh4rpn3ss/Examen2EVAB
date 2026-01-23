package es.iesquevedo.service;

import es.iesquevedo.modelo.Libro;
import es.iesquevedo.dao.LibroRepository;

import java.util.List;
import java.util.Optional;

public class LibroService {
    private final LibroRepository repo;

    public LibroService() {
        this.repo = new LibroRepository();
    }

    public boolean altaLibro(Libro libro) {
        if (libro.getId() == null || libro.getId().isBlank()) return false;
        if (libro.getEditorial() == null || libro.getEditorial().isBlank()) return false;
        return repo.create(libro);
    }

    public boolean bajaLibro(String id) {
        return repo.deleteById(id);
    }

    public List<Libro> listar() {
        return repo.findAll();
    }

    public Optional<Libro> buscarPorId(String id) {
        return repo.findById(id);
    }

    public void actualizar(Libro libro) {
        repo.update(libro);
    }
}
