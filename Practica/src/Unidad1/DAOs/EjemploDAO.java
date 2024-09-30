package Unidad1.DAOs;

import java.util.List;
import Unidad1.Objects.Ejemplo;

public interface EjemploDAO extends dbDAO<Ejemplo>{
	public List<Ejemplo> listarEjemplosPorID();
	
	//Interfaz que extiende las operaciones basicas de cada tabla aportadas por el dbDAO
	//en la interfaz se encuentran metodos especificos para el Ejemplo
}
