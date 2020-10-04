package rest.response;

import model.Estudiante;

import java.util.List;

public class EstudianteListResponse {
  List<Estudiante> estudiantes;

  public List<Estudiante> getEstudiantes() {
    return estudiantes;
  }

  public void setEstudiantes(List<Estudiante> estudiantes) {
    this.estudiantes = estudiantes;
  }
}
