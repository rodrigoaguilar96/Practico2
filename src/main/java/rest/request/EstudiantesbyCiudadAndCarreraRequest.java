package rest.request;

import model.Carrera;


public class EstudiantesbyCiudadAndCarreraRequest {
    String ciudad;
    Carrera carrera;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
