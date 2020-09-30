package rest;

import java.time.LocalDate;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.Estudiante;
import repository.EstudianteCarreraRepository;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;
import rest.request.MatricularEstudianteRequest;

@Path("/estudiantes")
public class EstudianteController {

  /**
   * Punto 2a.
   *
   * @param estudiante formato JSON
   * @return Estudiante cargado con status 200 o Server error y un mensaje.
   */
  @POST
  @Path("/addEstudiante")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveEstudiante(Estudiante estudiante) {
    try {
      EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
      estudianteRepository.save(estudiante);
      return Response
          .status(Response.Status.OK)
          .entity(estudiante)
          .build();
    } catch (Exception e) {
      String error = "Error al insertar Estudiante";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }

  /**
   * Punto 2b.
   *
   * @param matricularEstudianteRequest
   * @return Estudiante a matricular y la carrera cargado con status 200 o Server error y un
   * mensaje.
   */
  @POST
  @Path("/matricularEstudiante")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response matricularEstudiante(MatricularEstudianteRequest matricularEstudianteRequest) {
    try {
      EstudianteCarreraRepository estudianteCarreraRepository = new EstudianteCarreraRepositoryImpl();
      EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
      estudianteCarreraRepository.matricular(matricularEstudianteRequest.getCarrera(),
          matricularEstudianteRequest.getEstudiante(), LocalDate.now());
      estudianteRepository.save(matricularEstudianteRequest.getEstudiante());
      return Response
          .status(Response.Status.OK)
          .entity(matricularEstudianteRequest)
          .build();
    } catch (Exception e) {
      String error = "Error al Matricular un estudiante";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }

  /**
   * Punto 2c.
   *
   * @return lista de estudiantes ordenada por nombre o error.
   */
  @GET
  @Path("/listaEstudiantes")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getListaEstudiantes() {
    try {
      EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
      List<Estudiante> estudianteList = estudianteRepository.findAllSortByNombre();
      return Response
          .status(Response.Status.OK)
          .entity(estudianteList)
          .build();
    } catch (Exception e) {
      String error = "Error al Obtener lista de estudiantes ordenada por nombre.";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }

  /**
   * Punto 2d.
   *
   * @param libreta Integer libreta universitaria.
   * @return Estudiante por libreta universitaria.
   */
  @GET
  @Path("/estudianteById")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEstudiantebyLibreta(@QueryParam("libreta") Integer libreta) {
    try {
      EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
      return Response
          .status(Response.Status.OK)
          .entity(estudianteRepository.findByLibretaUniversitaria(libreta))
          .build();
    } catch (Exception e) {
      String error = "Error al Obtener Estudiante por la Libreta Universitaria";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }

  /**
   * Punto 2e.
   *
   * @param genero String genero.
   * @return lista de estudiantes por genero.
   */
  @GET
  @Path("/estudianteByGenero")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEstudiantebyGenero(@QueryParam("genero") String genero) {
    try {
      EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
      List<Estudiante> estudiantesByGenero = estudianteRepository.findByGenero(genero);
      return Response
          .status(Response.Status.OK)
          .entity(estudiantesByGenero)
          .build();
    } catch (Exception e) {
      String error = "Error al Obtener la lista de estudiantes por genero.";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }

  /**
   * Punto 2g.
   * @param ciudad
   * @return Estudiantes de una carrera por ciudad
   */
  @GET
  @Path("/reporteGraduados")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getEstudiantebyCiudad(@QueryParam("ciudad") String ciudad) {
    try {
      EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
      List<Estudiante> estudiantesByCiudad = estudianteRepository.findByGenero(ciudad);
      return Response
          .status(Response.Status.OK)
          .entity(estudiantesByCiudad)
          .build();
    } catch (Exception e) {
      String error = "Error al Obtener la lista de estudiantes por ciudad.";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }
}
