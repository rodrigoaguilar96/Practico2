package main;

import utils.CsvUtils;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import model.Carrera;
import model.Estudiante;
import model.dto.CarreraEstudiantes;
import model.dto.ReporteCarrera;
import repository.CarreraRepository;
import repository.CarreraRepositoryImpl;
import repository.EstudianteCarreraRepository;
import repository.EstudianteCarreraRepositoryImpl;
import repository.EstudianteRepository;
import repository.EstudianteRepositoryImpl;

/** Clase main */
public class Application {

  private static final CarreraRepository carreraRepository = new CarreraRepositoryImpl();
  private static final EstudianteRepository estudianteRepository = new EstudianteRepositoryImpl();
  private static final EstudianteCarreraRepository estudianteCarreraRepository =
      new EstudianteCarreraRepositoryImpl();

  public static void main(String[] args) throws IOException {
    CsvUtils csvUtils = new CsvUtils();
    csvUtils.uploadCarreras(new FileReader("src/main/resources/carrera.csv"));
    csvUtils.uploadEstudiante(new FileReader("src/main/resources/estudiante.csv"));
    csvUtils.uploadEstudianteCarrera(new FileReader("src/main/resources/estudianteCarrera.csv"));
    System.out.println("\n");
    System.out.println("2a Dar de alta un Estudiante");
    Estudiante estudiante = new Estudiante(21, "Prueba", "2a", 20, 522, "Masculino", "Tandil");
    estudianteRepository.save(estudiante);
    System.out.println(estudiante);

    System.out.println("\n");
    System.out.println("2b Matricular estudiante en carrera");
    Carrera carrera = new Carrera(6, "Profesorado");
    carreraRepository.save(carrera);
    estudianteCarreraRepository.matricular(carrera, estudiante, LocalDate.now());
    System.out.println(estudianteCarreraRepository.findById(6, 21));

    System.out.println("\n");
    System.out.println("2c Estudiantes ordenados por nombre");
    List<Estudiante> estudianteList = estudianteRepository.findAllSortByNombre();
    for (Estudiante estudiante1 : estudianteList) {
      System.out.println(estudiante1);
    }

    System.out.println("\n");
    System.out.println("2d Estudiante por numero de libreta");
    System.out.println(estudianteRepository.findByLibretaUniversitaria(10));

    System.out.println("\n");
    System.out.println("2e Estudiantes en base a su genero");
    List<Estudiante> estudianteListGenero = estudianteRepository.findByGenero("Masculino");
    System.out.println();
    for (Estudiante estudiante1 : estudianteListGenero) {
      System.out.println(estudiante1);
    }

    System.out.println("\n");
    System.out.println("2f Todas las carreras ordenadas segun inscriptos");
    List<CarreraEstudiantes> carreraEstudiantesList = carreraRepository.findAllSortByInscriptos();
    for (CarreraEstudiantes carreraEstudiantes : carreraEstudiantesList) {
      System.out.println(carreraEstudiantes);
    }

    System.out.println("\n");
    System.out.println("2g Estudiantes de una carrera por ciudad");
    List<Estudiante> estudianteList1 =
        estudianteRepository.findAllByCarreraAndCiudad(carrera, "Tandil");
    for (Estudiante estudiante1 : estudianteList1) {
      System.out.println(estudiante1);
    }

    System.out.println("\n");
    System.out.println("3 Reporte");
    List<ReporteCarrera> reporteCarreras = estudianteCarreraRepository.getReporteCarrera();
    Collections.sort(reporteCarreras);
    for (ReporteCarrera r : reporteCarreras) {
      System.out.println(r);
    }
  }
}
