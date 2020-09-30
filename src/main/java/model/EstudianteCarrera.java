package model;

import java.time.LocalDate;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class EstudianteCarrera {

  @EmbeddedId EstudianteCarreraPK estudianteCarreraPK;

  LocalDate fechaInscripcion;

  LocalDate fechaGraduacion;

  public EstudianteCarrera() {}

  public EstudianteCarrera(Integer idCarrera, Integer idEstudiante, LocalDate fechaInscripcion) {
    EstudianteCarreraPK estudianteCarreraPK = new EstudianteCarreraPK(idCarrera, idEstudiante);
    this.estudianteCarreraPK = estudianteCarreraPK;
    this.fechaInscripcion = fechaInscripcion;
  }

  public EstudianteCarrera(
      Integer idCarrera,
      Integer idEstudiante,
      LocalDate fechaInscripcion,
      LocalDate fechaGraduacion) {
    EstudianteCarreraPK estudianteCarreraPK = new EstudianteCarreraPK(idCarrera, idEstudiante);
    this.estudianteCarreraPK = estudianteCarreraPK;
    this.fechaInscripcion = fechaInscripcion;
    this.fechaGraduacion = fechaGraduacion;
  }

  @Override
  public String toString() {
    return "EstudianteCarrera{"
        + ""
        + estudianteCarreraPK
        + ", fechaInscripcion="
        + fechaInscripcion
        + ", fechaGraduacion="
        + fechaGraduacion
        + '}';
  }

  public EstudianteCarreraPK getEstudianteCarreraPK() {
    return estudianteCarreraPK;
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
