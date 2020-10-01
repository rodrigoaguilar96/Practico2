package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import model.Carrera;
import model.Estudiante;
import model.EstudianteCarrera;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import rest.LectorCicloDeVida;

public class CsvUtils {

  public CsvUtils() {}

  public void uploadCarreras(InputStreamReader fileReader) throws IOException {
    CSVParser carreraCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    List<Carrera> carreras = new ArrayList<>();
    for (CSVRecord row : carreraCsv) {
      Integer id = Integer.parseInt(row.get("id"));
      String nombre = row.get("nombre");
      carreras.add(new Carrera(id, nombre));
    }
    for (Carrera carrera : carreras) {
      LectorCicloDeVida.carreraRepository.save(carrera);
    }
  }

  public void uploadEstudiante(InputStreamReader fileReader) throws IOException {
    CSVParser carreraCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    List<Estudiante> estudiantes = new ArrayList<>();
    for (CSVRecord row : carreraCsv) {
      Integer libretaUniversitaria = Integer.parseInt(row.get("libretaUniversitaria"));
      String nombre = row.get("nombre");
      String apellido = row.get("apellido");
      Integer edad = Integer.parseInt(row.get("edad"));
      Integer documento = Integer.parseInt(row.get("documento"));
      String genero = row.get("genero");
      String ciudad = row.get("ciudad");
      estudiantes.add(
          new Estudiante(libretaUniversitaria, nombre, apellido, edad, documento, genero, ciudad));
    }
    for (Estudiante estudiante : estudiantes) {
      LectorCicloDeVida.estudianteRepository.save(estudiante);
    }
  }

  public void uploadEstudianteCarrera(InputStreamReader fileReader) throws IOException {
    CSVParser carreraCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    String europeanDatePattern = "yyyy-MM-dd";
    DateTimeFormatter europeanDateFormatter = DateTimeFormatter.ofPattern(europeanDatePattern);
    List<EstudianteCarrera> estudianteCarreras = new ArrayList<>();
    for (CSVRecord row : carreraCsv) {
      Integer idCarrera = Integer.parseInt(row.get("idCarrera"));
      Integer idEstudiante = Integer.parseInt(row.get("idEstudiante"));
      LocalDate fechaInscripcion = LocalDate.parse(row.get("fechaInscripcion"));
      LocalDate fechaGraduacion = null;
      String validador = row.get("fechaGraduacion");
      if (!Objects.equals(validador, "null")) {
        fechaGraduacion = LocalDate.parse(row.get("fechaGraduacion"), europeanDateFormatter);
      }
      estudianteCarreras.add(
          new EstudianteCarrera(idCarrera, idEstudiante, fechaInscripcion, fechaGraduacion));
    }
    for (EstudianteCarrera estudianteCarrera : estudianteCarreras) {
      LectorCicloDeVida.estudianteCarreraRepository.save(estudianteCarrera);
    }
  }
}
