package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Query;
import model.Carrera;
import model.Estudiante;
import model.EstudianteCarrera;
import model.dto.DatosReporteCarrera;
import model.dto.ReporteCarrera;

public class EstudianteCarreraRepositoryImpl extends GlobalRepository<EstudianteCarrera>
    implements EstudianteCarreraRepository {

  @Override
  public void matricular(Carrera carrera, Estudiante estudiante, LocalDate fechaInscripcion) {
    EstudianteCarrera estudianteCarrera =
        new EstudianteCarrera(
            carrera.getId(), estudiante.getLibretaUniversitaria(), fechaInscripcion);
    executeInsideTransaction(entityManager -> entityManager.persist(estudianteCarrera));
  }

  @Override
  public void save(EstudianteCarrera estudianteCarrera) {
    executeInsideTransaction(entityManager -> entityManager.persist(estudianteCarrera));
  }

  @Override
  public EstudianteCarrera findById(Integer idCarrera, Integer idEstudiante) {
    Query query =
        entityManager.createQuery(
            "select ec from EstudianteCarrera ec where ec.matricula.idCarrera = :idCarrera AND ec.matricula.idEstudiante = :idEstudiante");
    query.setParameter("idCarrera", idCarrera);
    query.setParameter("idEstudiante", idEstudiante);
    return (EstudianteCarrera) query.getSingleResult();
  }

  @Override
  public List<ReporteCarrera> getReporteCarrera() {
    List<ReporteCarrera> reporteCarreras = new ArrayList<>();
    Query carrerasQuery = entityManager.createQuery("SELECT c from Carrera c");
    List<Carrera> carreras = carrerasQuery.getResultList();
    String queryInscriptos =
            "select YEAR(ec.fechaInscripcion), count(fechaInscripcion) "
                    + "from EstudianteCarrera ec "
                    + "where ec.idCarrera= ? "
                    + "group by YEAR(ec.fechaInscripcion) "
                    + "order by 1";
    String queryEgresados =
            "select YEAR(ec.fechaGraduacion), count(fechaGraduacion) "
                    + "from EstudianteCarrera ec "
                    + "where ec.idCarrera= ? "
                    + "group by YEAR(ec.fechaGraduacion) "
                    + "order by 1";
    for (Carrera carrera : carreras) {
      ReporteCarrera reporteCarrera = new ReporteCarrera(carrera);
      Query query = entityManager.createNativeQuery(queryInscriptos);
      query.setParameter(1, carrera.getId());

      List<DatosReporteCarrera> datosReporteCarreraList = new ArrayList<>();
      List<Object[]> queryResultList = query.getResultList();
      for (Object[] o : queryResultList) {
        DatosReporteCarrera datosReporteCarrera = new DatosReporteCarrera();
        datosReporteCarrera.setAño((Integer) o[0]);
        datosReporteCarrera.setInscriptos(Integer.valueOf(Math.toIntExact((Long) o[1])));
        datosReporteCarreraList.add(datosReporteCarrera);
      }
      query = entityManager.createNativeQuery(queryEgresados);
      query.setParameter(1, carrera.getId());
      List<Object[]> queryEgresosResultList = query.getResultList();
      for (Object[] o : queryEgresosResultList) {
        if (Objects.nonNull((Integer) o[0])) {
          DatosReporteCarrera forCompere = new DatosReporteCarrera();
          forCompere.setAño((Integer) o[0]);
          if (datosReporteCarreraList.contains(forCompere)) {
            for (int i = 0; i < datosReporteCarreraList.size(); i++) {
              if (Objects.equals(datosReporteCarreraList.get(i).getAño(), (Integer) o[0])) {
                datosReporteCarreraList
                        .get(i)
                        .setEgresados(Integer.valueOf(Math.toIntExact((Long) o[1])));
              }
            }
          } else {
            DatosReporteCarrera datosReporteCarrera = new DatosReporteCarrera();
            datosReporteCarrera.setAño((Integer) o[0]);
            datosReporteCarrera.setEgresados(Integer.valueOf(Math.toIntExact((Long) o[1])));
            datosReporteCarreraList.add(datosReporteCarrera);
          }
        }
      }
      reporteCarrera.setDatosReporteCarreras(datosReporteCarreraList);
      reporteCarreras.add(reporteCarrera);
    }

    return reporteCarreras;
  }

}
