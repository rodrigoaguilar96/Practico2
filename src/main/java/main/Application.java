package main;

import utils.CsvUtils;

import java.io.FileNotFoundException;
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

  public static void csv() throws IOException {
    CsvUtils csvUtils = new CsvUtils();
    csvUtils.uploadCarreras(
        new FileReader(
            "D:\\Documentos\\Workspace\\IntellJ\\Arqui\\Practico 3\\Practico3\\src\\main\\webapp\\carrera.csv"));
    csvUtils.uploadEstudiante(
        new FileReader(
            "D:\\Documentos\\Workspace\\IntellJ\\Arqui\\Practico 3\\Practico3\\src\\main\\webapp\\estudiante.csv"));
    csvUtils.uploadEstudianteCarrera(
        new FileReader(
            "D:\\Documentos\\Workspace\\IntellJ\\Arqui\\Practico 3\\Practico3\\src\\main\\webapp\\estudianteCarrera.csv"));
  }
}
