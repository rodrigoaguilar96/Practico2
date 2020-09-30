package rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.dto.CarreraEstudiantes;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;

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
      CarreraRepository carreraRepository = new CarreraRepositoryImpl();
      List<CarreraEstudiantes> carreraEstudiantesList = carreraRepository.findAllSortByInscriptos();
      return Response
          .status(Response.Status.OK)
          .entity(carreraEstudiantesList)
          .build();
    } catch (Exception e) {
      String error = "Error al Obtener lista de Carreras.";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }


}
