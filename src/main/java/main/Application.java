package main;

import utils.CsvUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletContext;

/** Clase main */
public class Application {

  public static void csv() throws IOException {
    CsvUtils csvUtils = new CsvUtils();
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

    InputStream carreraCsv = classLoader.getResourceAsStream("/carrera.csv");
    InputStream estudianteCsv = classLoader.getResourceAsStream("/estudiante.csv");
    InputStream estudianteCarreraCsv = classLoader.getResourceAsStream("/estudianteCarrera.csv");

    csvUtils.uploadCarreras(new InputStreamReader(carreraCsv));
    csvUtils.uploadEstudiante(new InputStreamReader(estudianteCsv));
    csvUtils.uploadEstudianteCarrera(new InputStreamReader(estudianteCarreraCsv));
  }
}
