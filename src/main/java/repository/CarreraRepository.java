package repository;

import java.util.List;
import model.Carrera;
import model.dto.CarreraEstudiantes;

public interface CarreraRepository {

  void save(Carrera carrera);

  /**
   * recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad de inscriptos.
   *
   * @return lista de CarrerasEstudiantes ordenadas por inscriptos
   */
  List<CarreraEstudiantes> findAllSortByInscriptos();

  /**
   * recupera una carrera por ID.
   *
   * @param id
   * @return Carrera
   */
  Carrera findById(Integer id);
}
