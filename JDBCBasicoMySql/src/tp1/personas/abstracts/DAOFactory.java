package tp1.personas.abstracts;

import tp1.personas.DAOs.PersonaDAO;
import tp1.personas.Factories.DerbyJDBCDAOFactory;
import tp1.personas.Factories.JpaHibernateJDBCDAOFactory;
import tp1.personas.Factories.MySqlJDBCDAOFactory;

public abstract class DAOFactory {
	
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case MYSQL_JDBC : return new MySqlJDBCDAOFactory();
		case DERBY_JDBC : return new DerbyJDBCDAOFactory();
		case JPA_HIBERNATE : return new JpaHibernateJDBCDAOFactory();
		default: return null;
		}
	}
	
	public abstract PersonaDAO getPersonaDAO();
}
