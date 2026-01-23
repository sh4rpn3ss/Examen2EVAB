package es.iesquevedo.dao;

import com.google.gson.reflect.TypeToken;
import es.iesquevedo.modelo.Socio;
import es.iesquevedo.util.GsonFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.logging.Logger;

public class SocioRepository {
    private static final Logger logger = Logger.getLogger(SocioRepository.class.getName());
    private final Path file;
    private final HashMap<String, Socio> socios;

    public SocioRepository() {
        this.file = Path.of("data/socios.json");
        this.socios = new HashMap<>();
        load();
    }

    private void load() {
        try {
            if (Files.notExists(file.getParent())) Files.createDirectories(file.getParent());
            if (Files.notExists(file)) Files.writeString(file, "[]");
            String json = Files.readString(file);
            List<Socio> list = GsonFactory.getGson().fromJson(json, new TypeToken<List<Socio>>() {}.getType());
            list.forEach(s -> socios.put(s.getId(), s));
        } catch (IOException e) {
            System.err.println("Error cargando socios: " + e.getMessage());
        }
    }

    public void save() {
        try {
            String json = GsonFactory.getGson().toJson(socios.values());
            Files.writeString(file, json);
        } catch (IOException e) {
            System.err.println("Error guardando socios: " + e.getMessage());
        }
    }

    public boolean create(Socio socio) {
        if (socios.containsKey(socio.getId())) return false;
        socios.put(socio.getId(), socio);
        save();
        return true;
    }

    public boolean deleteById(String id) {
        if (socios.remove(id) != null) {
            save();
            return true;
        }
        return false;
    }

    public List<Socio> findAll() {
        return new ArrayList<>(socios.values());
    }

    public Optional<Socio> findById(String id) {
        return Optional.ofNullable(socios.get(id));
    }
}
