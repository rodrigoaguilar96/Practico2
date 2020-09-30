package rest;

import java.time.LocalDate;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Estudiante;
import repository.EstudianteCarreraRepository;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;
import rest.request.MatricularEstudianteRequest;

@Path("/controller")
public class EstudianteController {

    /**
     * Punto 2a
     * @param estudiante formato JSON
     * @return Response
     */
    @POST
    @Path("/addEstudiante")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEstudiante(Estudiante estudiante) {
        try{
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
     * Punto 2b
     * @param matricularEstudianteRequest
     * @return
     */
    @POST
    @Path("/matricularEstudiante")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response matricularEstudiante(MatricularEstudianteRequest matricularEstudianteRequest) {
        System.out.println("Entre");
        try{
            EstudianteCarreraRepository estudianteCarreraRepository = new EstudianteCarreraRepositoryImpl();
            EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
            estudianteCarreraRepository.matricular(matricularEstudianteRequest.getCarrera(), matricularEstudianteRequest.getEstudiante(), LocalDate.now());
            System.out.println("Entrex2");
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

}
