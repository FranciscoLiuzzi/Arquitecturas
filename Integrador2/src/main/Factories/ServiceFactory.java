package main.Factories;

import javax.persistence.EntityManager;
import main.Services.CarreraService;
import main.Services.EstudianteCarreraService;
import main.Services.EstudianteService;

public class ServiceFactory {
    private static ServiceFactory instance = null;
    private EntityManager emgr;
    
    private ServiceFactory(EntityManager emgr) {
        this.emgr = emgr;
    }
    
    public static synchronized ServiceFactory getInstance(EntityManager em) {
        if (instance == null) {
            instance = new ServiceFactory(em);
        }
        return instance;
    }

    public CarreraService getCarreraService() {
        return new CarreraService(emgr);
    }
    
    public EstudianteCarreraService getEstudianteCarreraService() {
        return new EstudianteCarreraService(emgr);
    }
   
    public EstudianteService getEstudianteService() {
        return new EstudianteService(emgr);
    }
   
}
