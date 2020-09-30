package rest.request;

import model.Carrera;
import model.Estudiante;

public class MatricularEstudianteRequest {
  Estudiante estudiante;
  Carrera carrera;

  public Estudiante getEstudiante() {
    return estudiante;
  }

  public void setEstudiante(Estudiante estudiante) {
    this.estudiante = estudiante;
  }

  public Carrera getCarrera() {
    return carrera;
  }

  public void setCarrera(Carrera carrera) {
    this.carrera = carrera;
  }
}
