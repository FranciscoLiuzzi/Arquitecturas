package main.Factories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionFactory {
	private static EntityManagerFactory emf;
	private static EntityManager em;

	public static EntityManager createConnection() {
		if (emf == null) {			
			emf = Persistence.createEntityManagerFactory("integrador2");
		}
		if (em == null) {
			em = emf.createEntityManager();
		}
		return em;
	}

	public static void closeConnection() {
		em.close();
		em = null;
		emf.close();
		emf = null;
	}    
}
