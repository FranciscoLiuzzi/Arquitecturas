package tp1.personas.DAOs;

import java.sql.Connection;
import tp1.personas.Objects.FacPro;

public class MySqlFacProDAO implements FacProDAO{

	private Connection conn;
	
	public MySqlFacProDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(FacPro ins) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FacPro upd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FacPro select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
