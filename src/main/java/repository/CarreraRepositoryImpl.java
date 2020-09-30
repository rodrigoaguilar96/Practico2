package repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import model.Carrera;
import model.dto.CarreraEstudiantes;

public class CarreraRepositoryImpl extends GlobalRepository<Carrera> implements CarreraRepository {

  @Override
  public void save(Carrera carrera) {
    executeInsideTransaction(entityManager -> entityManager.persist(carrera));
  }

  @Override
  public List<CarreraEstudiantes> findAllSortByInscriptos() {
    String query =
        "SELECT idCarrera, count(idCarrera) "
            + "FROM EstudianteCarrera "
            + "group by idCarrera "
            + "order by 2 desc";
    Query nativeQuery = entityManager.createNativeQuery(query);
    List<Object[]> nativeQueryResultList = nativeQuery.getResultList();
    List<CarreraEstudiantes> carreras = new ArrayList<>();
    for (Object[] o : nativeQueryResultList) {
      CarreraEstudiantes carreraEstudiantes = new CarreraEstudiantes();
      carreraEstudiantes.setCarrera(findById((Integer) o[0]));
      carreraEstudiantes.setInscriptos(Integer.parseInt(String.valueOf((Long) o[1])));
      carreras.add(carreraEstudiantes);
    }
    return carreras;
  }

  @Override
  public Carrera findById(Integer id) {
    Query query = entityManager.createQuery("select c from Carrera c where id = :id");
    query.setParameter("id", id);
    return (Carrera) query.getSingleResult();
  }
}
