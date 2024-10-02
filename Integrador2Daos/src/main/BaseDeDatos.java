package main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import main.DTOs.*;
import main.Factories.ConnectionFactory;
import main.Factories.ServiceFactory;
import main.Helper.DBHelper;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Services.CarreraService;
import main.Services.EstudianteCarreraService;
import main.Services.EstudianteService;

public class BaseDeDatos {
	public static void main (String[] args) {
		// Only for testing. NEXT LINE DROP ALL DATABASE!!!
		// dropDB();	
		EntityManager em = ConnectionFactory.createConnection();
		/*
		 * Populate Database, MySql.
		 */
		loadDB(em);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance(em);		
		
		EstudianteService es = serviceFactory.getEstudianteService();
		CarreraService cs = serviceFactory.getCarreraService();
		EstudianteCarreraService ecs = serviceFactory.getEstudianteCarreraService();

		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.a: ");
		System.out.println("//////////////////////////////////////////////////");		
		Estudiante estudiante = new Estudiante("Juan", "Perez", 35, "Tandil", "Male", 68374621, 51);
		es.save(estudiante);
		System.out.println(es.getEstudianteByLibreta(51));
		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.b: ");
		System.out.println("//////////////////////////////////////////////////");	
		Carrera carrera =  cs.findById(cs.getCarreraIdByName("TUDAI"));
		LocalDate date = LocalDate.of(2010,9,11);
		cs.matricular(estudiante, carrera);
		System.out.println(ecs.getCarrerasOf(estudiante));
		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.c: Ordenado por apellido");
		System.out.println("//////////////////////////////////////////////////");	
		for(EstudianteDTO info : es.getAllEstudiantesOrderByApellido())
		    System.out.println(info);
		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.d: ");
		System.out.println("//////////////////////////////////////////////////");
		System.out.println(es.getEstudianteByLibreta(23));
		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.e: ");
		System.out.println("//////////////////////////////////////////////////");
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
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.f: ");
		System.out.println("//////////////////////////////////////////////////");
		for (EstudiantesEnCarreraDTO info : ecs.getCarrerasPorCantEstudiantes())
			System.out.println(info);
		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 2.g: ");
		System.out.println("//////////////////////////////////////////////////");
		System.out.println(ecs.getListEstudiantePorCiudadResidencia("Varna", "Veterinaria"));
		System.out.println();
		System.out.println("//////////////////////////////////////////////////");
		System.out.println("Inciso 3: ");
		System.out.println("//////////////////////////////////////////////////");
		for(CarreraInscriptosGraduadosDTO info : ecs.getInformePorCarrera())
			System.out.println(info);		
		ConnectionFactory.closeConnection();
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
	
	private static void loadDB(EntityManager em) {
		DBHelper helper = new DBHelper(em);
		try {
			helper.fillDB();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}