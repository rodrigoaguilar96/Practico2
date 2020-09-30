package rest;

import main.Application;
import repository.*;
import utils.CsvUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

@WebListener
public class LectorCicloDeVida implements ServletContextListener {
    public static EstudianteRepository estudianteRepository = null;
    public static EstudianteCarreraRepository estudianteCarreraRepository= null;
    public static CarreraRepository carreraRepository= null;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        estudianteRepository = new EstudianteRepositoryImpl();
        estudianteCarreraRepository = new EstudianteCarreraRepositoryImpl();
        carreraRepository = new CarreraRepositoryImpl();
        try{
            Application.csv();
        } catch (IOException ioException) {
            System.out.print(ioException);
        }


    }
}
