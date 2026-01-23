package es.iesquevedo.modelo;

import java.util.Objects;

public class Socio {
    private String id;
    private String nombre;
    private String documento;

    public Socio() {
    }

    public Socio(String id, String nombre, String documento) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", documento='" + documento + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Socio)) return false;
        Socio socio = (Socio) o;
        return Objects.equals(id, socio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
