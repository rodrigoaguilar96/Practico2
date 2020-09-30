package model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carrera {

  @Id Integer id;

  String nombre;

  public Carrera() {}

  @Override
  public String toString() {
    return "Carrera{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Carrera(String nombre) {
    this.nombre = nombre;
  }

  public Carrera(Integer id, String nombre) {
    this.id = id;
    this.nombre = nombre;
  }
}
