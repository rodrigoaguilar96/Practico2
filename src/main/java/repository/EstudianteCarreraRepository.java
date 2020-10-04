package repository;

import java.time.LocalDate;
import java.util.List;
import model.Carrera;
import model.Estudiante;
import model.EstudianteCarrera;
import model.dto.ReporteCarrera;

public interface EstudianteCarreraRepository {

  /**
   * matricular un estudiante en una carrera.
   *
   * @param carrera
   * @param estudiante
   * @param fechaInscripcion
   */
  void matricular(Carrera carrera, Estudiante estudiante, LocalDate fechaInscripcion);

  /**
   * guarda en la base de datos un objeto EstudianteCarrera.
   *
   * @param estudianteCarrera
   */
  void save(EstudianteCarrera estudianteCarrera);

  /**
   * Obtiene un estudianteCarrera por ID.
   *
   * @param idCarrera
   * @param idEstudiante
   * @return
   */
  EstudianteCarrera findById(Integer idCarrera, Integer idEstudiante);

  /**
   * Metodo que crea el reporte para el punto 3.
   * Se realizo un refactor para bajar el costo de metodo.
   * @return lista de reportes
   */
  List<ReporteCarrera> getReporteCarrera();
}
