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
            "select ec from EstudianteCarrera ec where ec.estudianteCarreraPK.idCarrera = :idCarrera AND ec.estudianteCarreraPK.idEstudiante = :idEstudiante");
    query.setParameter("idCarrera", idCarrera);
    query.setParameter("idEstudiante", idEstudiante);
    return (EstudianteCarrera) query.getSingleResult();
  }

  //TODO refactor
  @Override
  public List<ReporteCarrera> getReporteCarrera() {
    List<ReporteCarrera> reporteCarreras = new ArrayList<>();
    Query carrerasQuery = entityManager.createQuery("SELECT c from Carrera c");
    List<Carrera> carreras = carrerasQuery.getResultList();
    for (Carrera carrera : carreras) {
      ReporteCarrera reporteCarrera = new ReporteCarrera(carrera);
      Query query =
          entityManager.createQuery(
              "SELECT ec from EstudianteCarrera ec where ec.matricula.idCarrera = :id order by fechaInscripcion");
      query.setParameter("id", carrera.getId());

      List<EstudianteCarrera> estudianteCarreras = query.getResultList();
      List<DatosReporteCarrera> datosReporteCarreraList = new ArrayList<>();
      List<Integer> anioIngreso = new ArrayList<>();
      List<Integer> anioEgreso = new ArrayList<>();
      for (EstudianteCarrera e : estudianteCarreras) {
        anioIngreso.add(e.getFechaInscripcion().getYear());
        if (Objects.nonNull(e.getFechaGraduacion())) {
          anioEgreso.add(e.getFechaGraduacion().getYear());
        }
      }
      anioIngreso.sort(Integer::compareTo);
      anioEgreso.sort(Integer::compareTo);

      int i = 0;
      while (i < anioIngreso.size()) {
        DatosReporteCarrera datosReporteCarrera = new DatosReporteCarrera();
        int contador = 1;
        while ((i < anioIngreso.size() - 1)
            && (Objects.equals(anioIngreso.get(i), anioIngreso.get(i + 1)))) {
          contador++;
          i++;
        }
        if (anioEgreso.contains(anioIngreso.get(i))) {
          datosReporteCarrera.setEgresados(contarEgresos(anioEgreso, anioIngreso.get(i)));
        }
        datosReporteCarrera.setAño(anioIngreso.get(i));
        datosReporteCarrera.setInscriptos(contador);
        datosReporteCarreraList.add(datosReporteCarrera);
        i++;
      }
      i = 0;
      while (i < anioEgreso.size()) {
        DatosReporteCarrera datosReporteCarrera = new DatosReporteCarrera();
        int contador = 1;
        while ((i < anioEgreso.size() - 1) && (anioEgreso.get(i) == anioEgreso.get(i + 1))) {
          contador++;
          i++;
        }
        if (anioIngreso.contains(anioEgreso.get(i))) {
          i++;
        } else {
          datosReporteCarrera.setEgresados(contador);
          datosReporteCarrera.setAño(anioEgreso.get(i));
          datosReporteCarreraList.add(datosReporteCarrera);
          i++;
        }
      }
      reporteCarrera.setDatosReporteCarreras(datosReporteCarreraList);
      reporteCarreras.add(reporteCarrera);
    }

    return reporteCarreras;
  }

  private Integer contarEgresos(List<Integer> integers, Integer itemToCheck) {
    int count = 0;
    for (Integer i : integers) {
      if (i.equals(itemToCheck)) {
        count++;
      }
    }
    return count;
  }
}
