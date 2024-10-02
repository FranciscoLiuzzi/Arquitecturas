package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import main.DTOs.*;
import main.Factories.ConnectionFactory;
import main.Factories.ServiceFactory;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Services.CarreraService;
import main.Services.EstudianteCarreraService;
import main.Services.EstudianteService;

public class BaseDeDatos {
	public static void main (String[] args) {
		dropDB();
		EntityManager em = ConnectionFactory.createConnection();
	
		ServiceFactory serviceFactory = ServiceFactory.getInstance(em);		
		
		EstudianteService es = serviceFactory.getEstudianteService();
		CarreraService cs = serviceFactory.getCarreraService();
		EstudianteCarreraService ecs = serviceFactory.getEstudianteCarreraService();
		
		System.out.println("Previo vamos a cargar un par de carreras");
		Carrera carrera = new Carrera("TUDAI");
		Carrera carrera1 = new Carrera("TUARI");
		Carrera carrera2 = new Carrera("Ingenieria");
		Carrera carrera3 = new Carrera("Veterinaria");
		Carrera carrera4 = new Carrera("Contador");
		cs.save(carrera);
		cs.save(carrera1);
		cs.save(carrera2);
		cs.save(carrera3);
		cs.save(carrera4);
		System.out.println("A ver si se cargaron");
		Carrera carrera5 =  cs.findById(cs.getCarreraIdByName("TUDAI"));
		System.out.println();
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Vamos a dar de alta un par de estudiantes");
		Estudiante estudiante = new Estudiante("Juan", "Perez", 35, "Tandil", "M", 42523351, 1);
		Estudiante estudiante1 = new Estudiante("Jose", "Gomez", 22, "Necochea", "M", 11663388, 2);
		Estudiante estudiante2 = new Estudiante("Pedro", "Asve", 18, "Loberia", "M", 44573574, 3);
		Estudiante estudiante3 = new Estudiante("Nico", "Castilla", 33, "Tandil", "M", 45687523, 4);
		Estudiante estudiante4 = new Estudiante("Loan", "Abarri", 25, "Olavarria", "M", 42578964, 5);
		Estudiante estudiante5 = new Estudiante("Agustin", "Quino", 40, "Junin", "T", 25476852, 6);
		Estudiante estudiante6 = new Estudiante("Julio", "Belga", 24, "Tandil", "T", 45875478, 7);
		Estudiante estudiante7 = new Estudiante("Mariano", "Etchebarria", 21, "Tandil", "F", 43568795, 8);
		es.save(estudiante);
		es.save(estudiante1);
		es.save(estudiante2);
		es.save(estudiante3);
		es.save(estudiante4);
		es.save(estudiante5);
		es.save(estudiante6);
		es.save(estudiante7);
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Vamos a buscar a esos estudiantes por libreta");
		System.out.println(es.getEstudianteByLibreta(1));
		System.out.println(es.getEstudianteByLibreta(2));
		System.out.println(es.getEstudianteByLibreta(3));
		System.out.println(es.getEstudianteByLibreta(4));
		System.out.println(es.getEstudianteByLibreta(5));
		System.out.println(es.getEstudianteByLibreta(6));
		System.out.println(es.getEstudianteByLibreta(7));
		System.out.println(es.getEstudianteByLibreta(8));
		System.out.println();
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Vamos a matricularlos en carreras");
		cs.matricular(estudiante, carrera);
		cs.matricular(estudiante, carrera2);
		cs.matricular(estudiante1, carrera);
		cs.matricular(estudiante2, carrera);
		cs.matricular(estudiante3, carrera2);
		cs.matricular(estudiante4, carrera2);
		cs.matricular(estudiante5, carrera);
		cs.matricular(estudiante6, carrera);
		cs.matricular(estudiante7, carrera3);
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Vamos a buscar las carreras de Juan");
		System.out.println(ecs.getCarrerasOf(estudiante));
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Los Estudiantes por apellido");	
		for(EstudianteDTO info : es.getAllEstudiantesOrderByApellido())
		    System.out.println(info);
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Filtramos los estudiantes por Genero");
		Scanner scanner = new Scanner(System.in);
		for (String string : es.getGeneros()) {
			System.out.print(string + ", ");
		}
		System.out.println();
		System.out.print("Ingrese el genero a filtrar: ");
		String genero = scanner.nextLine();
		System.out.println("Estudiantes de genero " + genero + ":");
		for(EstudianteDTO est : es.getEstudiantesPorGenero(genero)) {
			System.out.println(est);
		}
		scanner.close();
		System.out.println();
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Traemos todas las carreras y las ordenamos por cantidad de alumnos");
		for (EstudiantesEnCarreraDTO info : ecs.getCarrerasPorCantEstudiantes())
			System.out.println(info);
		System.out.println();
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Traemos los estudiantes de Tandil que estan en TUDAI");
		System.out.println(ecs.getListEstudiantePorCiudadResidencia("Tandil", "TUDAI"));
		System.out.println();
		System.out.println("__________________________________________________________________________");
		System.out.println();
		System.out.println("Probamos el DTO de todas las carreras");
		for(CarreraInscriptosGraduadosDTO info : ecs.getInformePorCarrera())
			System.out.println(info);		
		ConnectionFactory.closeConnection();
		System.out.println("__________________________________________________________________________");
	}
	
	private static void dropDB() {
		EntityManager emDrop = ConnectionFactory.createConnection();
		emDrop.getTransaction().begin();
		String sql = "DROP DATABASE IF EXISTS integrador2; ";
		Query nq = emDrop.createNativeQuery(sql);
		nq.executeUpdate();
		emDrop.getTransaction().commit();	
		ConnectionFactory.closeConnection();
	}
}