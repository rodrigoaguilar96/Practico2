package rest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Carrera;
import model.Estudiante;
import model.dto.CarreraEstudiantes;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;
import rest.response.CarrerasEstudiantesListResponse;

/** Controller Carreras */
@Path("/carreras")
public class CarreraController {

  /**
   * Punto 2f.
   *
   * @return lista de carreras ordenadas por cantidad de inscriptos por nombre o error.
   */
  @GET
  @Path("/listaCarreras")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getListaCarrera() {
    try {
      List<CarreraEstudiantes> carreraList =
          LectorCicloDeVida.carreraRepository.findAllSortByInscriptos();
      return Response.status(Response.Status.OK).entity(carreraList).build();
    } catch (Exception e) {
      String error = "Error al Obtener lista de Carreras.";
      return Response.serverError().entity(error).build();
    }
  }

  /**
   * enpoint no obligatorio, esta incluido en la coleccion de Postman
   *
   * @param carrera
   * @return carrera creada o error
   */
  @POST
  @Path("/addCarrera")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response saveEstudiante(Carrera carrera) {
    try {
      LectorCicloDeVida.carreraRepository.save(carrera);
      return Response.status(Response.Status.OK).entity(carrera).build();
    } catch (Exception e) {
      String error = "Error al insertar Carrera";
      return Response.serverError().entity(error).build();
    }
  }
}
