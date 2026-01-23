package es.iesquevedo.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Prestamo {
    private String id;
    private String libroId;
    private String socioId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Prestamo() {
    }

    public Prestamo(String id, String libroId, String socioId, LocalDate fechaInicio, LocalDate fechaFin) {
        this.id = id;
        this.libroId = libroId;
        this.socioId = socioId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibroId() {
        return libroId;
    }

    public void setLibroId(String libroId) {
        this.libroId = libroId;
    }

    public String getSocioId() {
        return socioId;
    }

    public void setSocioId(String socioId) {
        this.socioId = socioId;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Préstamo{" +
                "id='" + id + '\'' +
                ", libroId='" + libroId + '\'' +
                ", socioId='" + socioId + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Prestamo)) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(id, prestamo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
