package model.dto;

import java.util.Objects;

/** Clase utilizada para los reportes del punto 3. */
public class DatosReporteCarrera implements Comparable<DatosReporteCarrera> {
  Integer inscriptos;
  Integer egresados;
  Integer año;

  @Override
  public String toString() {

    return "{ año=" + año + " inscriptos=" + inscriptos + ", egresados=" + egresados + ", " + '}';
  }

  public void setInscriptos(Integer inscriptos) {
    this.inscriptos = inscriptos;
  }

  public Integer getInscriptos() {
    return inscriptos;
  }

  public Integer getEgresados() {
    return egresados;
  }

  public void setEgresados(Integer egresados) {
    this.egresados = egresados;
  }

  public Integer getAño() {
    return año;
  }

  public void setAño(Integer año) {
    this.año = año;
  }

  @Override
  public int compareTo(DatosReporteCarrera o) {
    return Integer.compare(this.getAño(), o.getAño());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    DatosReporteCarrera temp = (DatosReporteCarrera) o;
    if (Objects.equals(this.getAño(), temp.getAño())) {
      return true;
    } else return false;
  }
}
