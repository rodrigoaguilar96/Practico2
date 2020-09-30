package model.dto;

import model.Carrera;

/**
* Esta clase es utilizada para el punto 2f
 */
public class CarreraEstudiantes {
  Carrera carrera;

  Integer inscriptos;

  @Override
  public String toString() {
    return carrera + ", inscriptos=" + inscriptos + '}';
  }

  public Carrera getCarrera() {
    return carrera;
  }

  public void setCarrera(Carrera carrera) {
    this.carrera = carrera;
  }

  public void setInscriptos(Integer inscriptos) {
    this.inscriptos = inscriptos;
  }


}
