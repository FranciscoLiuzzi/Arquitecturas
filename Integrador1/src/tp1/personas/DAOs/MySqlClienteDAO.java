package tp1.personas.DAOs;

import java.sql.Connection;

import tp1.personas.Objects.Cliente;

public class MySqlClienteDAO implements ClienteDAO{
	
	private Connection conn;
	
	public MySqlClienteDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(Cliente ins) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cliente upd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
