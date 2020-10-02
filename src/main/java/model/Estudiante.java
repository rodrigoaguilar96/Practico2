package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Estudiante {

  @Id Integer libretaUniversitaria;

  String nombre;

  String apellido;

  Integer edad;

  Integer documento;

  String genero;

  String ciudad;

  public Estudiante() {}

  public Estudiante(
      String nombre,
      String apellido,
      Integer edad,
      Integer documento,
      String genero,
      String ciudad) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.documento = documento;
    this.genero = genero;
    this.ciudad = ciudad;
  }

  public Estudiante(
      Integer libretaUniversitaria,
      String nombre,
      String apellido,
      Integer edad,
      Integer documento,
      String genero,
      String ciudad) {
    this.libretaUniversitaria = libretaUniversitaria;
    this.nombre = nombre;
    this.apellido = apellido;
    this.edad = edad;
    this.documento = documento;
    this.genero = genero;
    this.ciudad = ciudad;
  }

  public Integer getLibretaUniversitaria() {
    return libretaUniversitaria;
  }

  public String getNombre() {
    return nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public Integer getEdad() {
    return edad;
  }

  public Integer getDocumento() {
    return documento;
  }

  public String getGenero() {
    return genero;
  }

  public String getCiudad() {
    return ciudad;
  }

  @Override
  public String toString() {
    return "Estudiante{"
        + "libretaUniversitaria="
        + libretaUniversitaria
        + ", nombre='"
        + nombre
        + '\''
        + ", apelldio='"
        + apellido
        + '\''
        + ", edad="
        + edad
        + ", documento="
        + documento
        + ", genero='"
        + genero
        + '\''
        + ", ciudad='"
        + ciudad
        + '\''
        + '}';
  }
}
