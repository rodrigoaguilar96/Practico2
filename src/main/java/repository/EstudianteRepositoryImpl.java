package repository;

import java.util.List;
import javax.persistence.Query;
import model.Carrera;
import model.Estudiante;

public class EstudianteRepositoryImpl extends GlobalRepository<Estudiante>
    implements EstudianteRepository {

  @Override
  public void save(Estudiante estudiante) {
    executeInsideTransaction(entityManager -> entityManager.persist(estudiante));
  }

  @Override
  public List<Estudiante> findAllSortByNombre() {
    Query query = entityManager.createQuery("select e from Estudiante e ORDER BY nombre");
    return (List<Estudiante>) query.getResultList();
  }

  @Override
  public Estudiante findByLibretaUniversitaria(Integer libretaUniversitaria) {
    Query query =
        entityManager.createQuery(
            "select e from Estudiante e where libretaUniversitaria = :libretaUniversitaria");
    query.setParameter("libretaUniversitaria", libretaUniversitaria);
    return (Estudiante) query.getSingleResult();
  }

  @Override
  public List<Estudiante> findByGenero(String genero) {
    Query query = entityManager.createQuery("select e from Estudiante e where genero = :genero");
    query.setParameter("genero", genero);
    return (List<Estudiante>) query.getResultList();
  }

  @Override
  public List<Estudiante> findAllByCarreraAndCiudad(Carrera carrera, String ciudad) {
    Query query =
        entityManager.createQuery(
            "select e "
                + "from Estudiante e "
                + "JOIN EstudianteCarrera ec "
                + "JOIN Carrera c "
                + "where ec.matricula.idCarrera = :idCarrera AND e.ciudad like :ciudad");
    query.setParameter("idCarrera", carrera.getId());
    query.setParameter("ciudad", ciudad);
    return (List<Estudiante>) query.getResultList();
  }
}
