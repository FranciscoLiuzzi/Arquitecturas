package Unidad1.abstracts;

import Unidad1.DAOs.EjemploDAO;
import Unidad1.Factories.DerbyJDBCDAOFactory;
import Unidad1.Factories.JpaHibernateJDBCDAOFactory;
import Unidad1.Factories.MySqlJDBCDAOFactory;

public abstract class DAOFactory {
	
	public static final int MYSQL_JDBC = 1;
	public static final int DERBY_JDBC = 2;
	public static final int JPA_HIBERNATE = 3;
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
			case MYSQL_JDBC : return MySqlJDBCDAOFactory.getInstance();
			case DERBY_JDBC : return new DerbyJDBCDAOFactory();
			case JPA_HIBERNATE : return new JpaHibernateJDBCDAOFactory();
		default: return null;
		}
	}
	
	public abstract EjemploDAO getEjemploDAO();
}
