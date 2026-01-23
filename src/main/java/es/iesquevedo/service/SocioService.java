package es.iesquevedo.service;

import es.iesquevedo.modelo.Socio;
import es.iesquevedo.dao.SocioRepository;

import java.util.List;
import java.util.Optional;

public class SocioService {
    private final SocioRepository repo;

    public SocioService() {
        this.repo = new SocioRepository();
    }

    public boolean altaSocio(Socio s) {
        if (s.getId() == null || s.getId().isBlank()) return false;
        if (s.getNombre() == null || s.getNombre().isBlank()) return false;
        return repo.create(s);
    }

    public boolean bajaSocio(String id) {
        return repo.deleteById(id);
    }

    public List<Socio> listar() {
        return repo.findAll();
    }

    public Optional<Socio> buscarPorId(String id) {
        return repo.findById(id);
    }
}
