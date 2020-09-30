package rest;

import java.util.Collections;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.dto.ReporteCarrera;
import repository.EstudianteCarreraRepository;
import repository.EstudianteCarreraRepositoryImpl;

@Path("/reporte")
public class ReporteController {

  @GET
  @Path("/estudianteByCiudad")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getReporte() {
    try {
      EstudianteCarreraRepository estudianteCarreraRepository =
          new EstudianteCarreraRepositoryImpl();
      List<ReporteCarrera> reporteCarreras = estudianteCarreraRepository.getReporteCarrera();
      Collections.sort(reporteCarreras);
      return Response
          .status(Response.Status.OK)
          .entity(reporteCarreras)
          .build();
    } catch (Exception e) {
      String error = "Error al Obtener Reporte.";
      return Response
          .serverError()
          .entity(error)
          .build();
    }
  }

}
