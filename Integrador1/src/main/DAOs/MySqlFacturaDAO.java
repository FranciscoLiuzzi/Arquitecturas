package main.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.Objects.Factura;

public class MySqlFacturaDAO implements FacturaDAO{
	
	private Connection conn;
	
	public MySqlFacturaDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(Factura ins) throws Exception{
		String insert = "INSERT INTO Factura (idFactura, idCliente) VALUES (?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		try {
			ps.setInt(1, ins.getIdFac());
			ps.setInt(2, ins.getIdCli());
			if (ps.executeUpdate() == 0) {
	            throw new Exception("No se pudo insertar");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		ps.close();
		conn.commit();
		return 0;
	}
	
	@Override
	public boolean delete(Integer id) throws Exception{
		String delete = "DELETE FROM Factura WHERE idFactura = ?";
		PreparedStatement ps = conn.prepareStatement(delete);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
		conn.commit();
		return false;
	}

	@Override
	public boolean update(Factura upd) throws Exception{
		//A implementar
		return false;
	}

	@Override
	public Factura select(Integer id) {
		//A implementar
		return null;
	}
	
}
