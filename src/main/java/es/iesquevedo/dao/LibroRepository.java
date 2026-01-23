package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Libro;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibroRepository {
    private final Path file = Path.of("data", "libros.json");
    private final Type listType = new TypeToken<List<Libro>>(){}.getType();
    private List<Libro> coches = new ArrayList<>();

    public LibroRepository() {
        load();
    }

    private void load() {
        try {
            if (Files.notExists(file.getParent())) {
                Files.createDirectories(file.getParent());
            }
            if (Files.notExists(file)) {
                Files.writeString(file, "[]");
            }
            String json = Files.readString(file);
            List<Libro> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) coches = list;
        } catch (IOException e) {
            System.err.println("Error cargando coches: " + e.getMessage());
        }
    }

    private void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(coches, listType));
        } catch (IOException e) {
            System.err.println("Error guardando libros: " + e.getMessage());
        }
    }

    public boolean create(Libro libro) {
        // recargar estado antes de modificar
        load();
        if (findById(libro.getId()).isPresent()) return false;
        coches.add(libro);
        save();
        return true;
    }

    public boolean deleteById(String id) {
        // recargar
        load();
        boolean removed = coches.removeIf(c -> c.getId().equals(id));
        if (removed) save();
        return removed;
    }

    public List<Libro> findAll() {
        load();
        return coches.stream().collect(Collectors.toList());
    }

    public Optional<Libro> findById(String id) {
        load();
        return coches.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void update(Libro updated) {
        load();
        for (int i = 0; i < coches.size(); i++) {
            if (coches.get(i).getId().equals(updated.getId())) {
                coches.set(i, updated);
                save();
                return;
            }
        }
    }
}
