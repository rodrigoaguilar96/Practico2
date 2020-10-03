package model;

import java.time.LocalDate;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EstudianteCarrera {

  @EmbeddedId
  Matricula matricula;

  LocalDate fechaInscripcion;

  LocalDate fechaGraduacion;

  public EstudianteCarrera() {}

  public EstudianteCarrera(Integer idCarrera, Integer idEstudiante, LocalDate fechaInscripcion) {
    Matricula matricula = new Matricula(idCarrera, idEstudiante);
    this.matricula = matricula;
    this.fechaInscripcion = fechaInscripcion;
  }

  public EstudianteCarrera(
      Integer idCarrera,
      Integer idEstudiante,
      LocalDate fechaInscripcion,
      LocalDate fechaGraduacion) {
    Matricula matricula = new Matricula(idCarrera, idEstudiante);
    this.matricula = matricula;
    this.fechaInscripcion = fechaInscripcion;
    this.fechaGraduacion = fechaGraduacion;
  }

  @Override
  public String toString() {
    return "EstudianteCarrera{"
        + ""
        + matricula
        + ", fechaInscripcion="
        + fechaInscripcion
        + ", fechaGraduacion="
        + fechaGraduacion
        + '}';
  }

  public Matricula getEstudianteCarreraPK() {
    return matricula;
  }

  public LocalDate getFechaInscripcion() {
    return fechaInscripcion;
  }

  public LocalDate getFechaGraduacion() {
    return fechaGraduacion;
  }

  public void setFechaGraduacion(LocalDate fechaGraduacion) {
    this.fechaGraduacion = fechaGraduacion;
  }
}
