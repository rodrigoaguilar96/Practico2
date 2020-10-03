package rest.request;

import model.Carrera;
import model.Estudiante;

public class MatricularEstudianteRequest {
  Integer estudiante;
  Integer carrera;

  public Integer getEstudiante() {
    return estudiante;
  }

  public void setEstudiante(Integer estudiante) {
    this.estudiante = estudiante;
  }

  public Integer getCarrera() {
    return carrera;
  }

  public void setCarrera(Integer carrera) {
    this.carrera = carrera;
  }
}
