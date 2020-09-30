package repository;

import java.util.List;
import model.Carrera;
import model.Estudiante;

public interface EstudianteRepository {

  /**
   * dar de alta un estudiante
   *
   * @param estudiante
   */
  void save(Estudiante estudiante);

  /**
   * recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simple.
   *
   * @return
   */
  List<Estudiante> findAllSortByNombre();

  /**
   * recuperar un estudiante, en base a su número de libreta universitaria.
   *
   * @param libretaUniversitaria
   * @return
   */
  Estudiante findByLibretaUniversitaria(Integer libretaUniversitaria);

  /**
   * recuperar todos los estudiantes, en base a su género.
   *
   * @param genero
   * @return
   */
  List<Estudiante> findByGenero(String genero);

  /**
   * recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
   *
   * @param carrera
   * @param ciudad
   * @return
   */
  List<Estudiante> findAllByCarreraAndCiudad(Carrera carrera, String ciudad);
}
