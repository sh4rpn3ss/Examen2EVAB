package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Prestamo;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrestamoRepository {
    private final Path file = Path.of("data", "prestamos.json");
    private final Type listType = new TypeToken<List<Prestamo>>(){}.getType();
    private List<Prestamo> alquileres = new ArrayList<>();

    public PrestamoRepository() {
        load();
    }

    private void load() {
        try {
            if (Files.notExists(file.getParent())) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.writeString(file, "[]");
            String json = Files.readString(file);
            List<Prestamo> list = GsonFactory.getGson().fromJson(json, listType);
            if (list != null) alquileres = list;
        } catch (IOException e) {
            System.err.println("Error cargando alquileres: " + e.getMessage());
        }
    }

    private void save() {
        try {
            Files.writeString(file, GsonFactory.getGson().toJson(alquileres, listType));
        } catch (IOException e) {
            System.err.println("Error guardando préstamos: " + e.getMessage());
        }
    }

    public boolean create(Prestamo prestamo) {
        load();
        if (findById(prestamo.getId()).isPresent()) return false;
        alquileres.add(prestamo);
        save();
        return true;
    }

    public boolean deleteById(String id) {
        load();
        boolean removed = alquileres.removeIf(a -> a.getId().equals(id));
        if (removed) save();
        return removed;
    }

    public List<Prestamo> findAll() {
        load();
        return alquileres.stream().collect(Collectors.toList());
    }

    public Optional<Prestamo> findById(String id) {
        load();
        return alquileres.stream().filter(a -> a.getId().equals(id)).findFirst();
    }
}
