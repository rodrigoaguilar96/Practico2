package model.dto;

/**
* Clase utilizada para los reportes del punto 3.
 */
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
}
