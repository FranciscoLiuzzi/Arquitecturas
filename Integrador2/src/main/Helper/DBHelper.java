package main.Helper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.EntityManager;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import main.Factories.ServiceFactory;
import main.Objects.Carrera;
import main.Objects.Estudiante;
import main.Objects.EstudianteCarrera;
import main.Services.CarreraService;
import main.Services.EstudianteCarreraService;
import main.Services.EstudianteService;

public class DBHelper {
	private EntityManager em;
	private CarreraService carreraRepo;
	private EstudianteService estudianteRepo;
	private EstudianteCarreraService estudianteCarreraRepo;
	private ServiceFactory repoFactory;

	public DBHelper(EntityManager em) {		
		this.em = em;
		this.repoFactory = ServiceFactory.getInstance(em);
		this.carreraRepo = repoFactory.getCarreraService();
		this.estudianteRepo = repoFactory.getEstudianteService();
		this.estudianteCarreraRepo = repoFactory.getEstudianteCarreraService();
	}    
	
	private Iterable<CSVRecord> getData(String archivo) throws IOException {
		String path = ".\\src\\main\\resources\\" + archivo;
		Reader in = new FileReader(path);
		String[] header = {};
		CSVParser csvParser = CSVFormat.EXCEL.builder().setNullString("").setHeader(header).build().parse(in);		
		Iterable<CSVRecord> record = csvParser;
		return record;
	}

    public void fillDB() throws Exception {
    	System.out.println("Populating DB...");
		for(CSVRecord row : getData("carreras.csv")) {
			Carrera carrera = new Carrera((String) row.get(0));
			carreraRepo.save(carrera);
		}
		System.out.println("Carreras insertadas");
		for(CSVRecord row : getData("estudiantes.csv")) {
			Estudiante estudiante = new Estudiante((String) row.get(0), (String) row.get(1), 
							Integer.parseInt(row.get(2)), (String) row.get(3), Integer.parseInt(row.get(4)),
							(String) row.get(5), Integer.parseInt(row.get(6)));
			estudianteRepo.save(estudiante);
		}
		System.out.println("Estudiantes insertados");
		for(CSVRecord row : getData("estudiantes_carreras.csv")) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
			LocalDate date1 = LocalDate.parse(row.get(2), formatter);
			LocalDate date = row.get(3) == null ? null : LocalDate.parse(row.get(3), formatter) ;
			EstudianteCarrera estudianteCarrera = new EstudianteCarrera((Estudiante) em.find(Estudiante.class, Integer.parseInt(row.get(0))), 
						(Carrera) em.find(Carrera.class, Integer.parseInt(row.get(1))), 
						date1, date);
			estudianteCarreraRepo.save(estudianteCarrera);
		}
		System.out.println("Relacion Estudantes-Carrera insertadas");
	}
}