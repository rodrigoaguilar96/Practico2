package rest.response;

import model.dto.CarreraEstudiantes;

import java.util.List;

public class CarrerasEstudiantesListResponse {
    List<CarreraEstudiantes> carreraEstudiantesList;

    public List<CarreraEstudiantes> getCarreraEstudiantesList() {
        return carreraEstudiantesList;
    }

    public void setCarreraEstudiantesList(List<CarreraEstudiantes> carreraEstudiantesList) {
        this.carreraEstudiantesList = carreraEstudiantesList;
    }
}
