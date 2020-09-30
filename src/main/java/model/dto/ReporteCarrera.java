package model.dto;

import java.util.Collections;
import java.util.List;
import model.Carrera;

/** Clase que contiene la carrera y la lista de datos para el punto 3 */
public class ReporteCarrera implements Comparable<ReporteCarrera> {
  Carrera carrera;
  List<DatosReporteCarrera> datosReporteCarreras;

  public ReporteCarrera(Carrera carrera) {
    this.carrera = carrera;
  }

  public Carrera getCarrera() {
    return carrera;
  }

  public void setCarrera(Carrera carrera) {
    this.carrera = carrera;
  }

  public List<DatosReporteCarrera> getDatosReporteCarreras() {
    return datosReporteCarreras;
  }

  public void setDatosReporteCarreras(List<DatosReporteCarrera> datosReporteCarreras) {
    this.datosReporteCarreras = datosReporteCarreras;
    Collections.sort(this.getDatosReporteCarreras());
  }

  @Override
  public String toString() {
    String s = "Reporte{" + "carrera=" + carrera + ", datos =\n";
    for (DatosReporteCarrera d : datosReporteCarreras) {
      s += d;
      s += "} \n";
    }
    return s;
  }

  @Override
  public int compareTo(ReporteCarrera o) {
    return this.getCarrera().getNombre().compareTo(o.getCarrera().getNombre());
  }
}
