package model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class Matricula implements Serializable {

  Integer idCarrera;

  Integer idEstudiante;

  public Matricula() {}

  public Matricula(Integer idCarrera, Integer idEstudiante) {
    this.idCarrera = idCarrera;
    this.idEstudiante = idEstudiante;
  }

  @Override
  public String toString() {
    return "Matricula{"
        + "idCarrera="
        + idCarrera
        + ", idEstudiante="
        + idEstudiante
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Matricula that = (Matricula) o;
    return Objects.equals(idCarrera, that.idCarrera)
        && Objects.equals(idEstudiante, that.idEstudiante);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idCarrera, idEstudiante);
  }

  public void setIdCarrera(Integer idCarrera) {
    this.idCarrera = idCarrera;
  }

  public void setIdEstudiante(Integer idEstudiante) {
    this.idEstudiante = idEstudiante;
  }

  public Integer getIdCarrera() {
    return idCarrera;
  }

  public Integer getIdEstudiante() {
    return idEstudiante;
  }
}
