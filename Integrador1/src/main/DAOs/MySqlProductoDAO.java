package main.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.DTOs.ProductoMasRecauda;
import main.Objects.Producto;

public class MySqlProductoDAO implements ProductoDAO{
	
	private Connection conn;
	
	public MySqlProductoDAO(Connection conn) {
		this.conn = conn;
	}

	@Override
	public int insert(Producto ins) throws Exception{
		String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		try {
			ps.setInt(1, ins.getId());
			ps.setString(2, ins.getName());
			ps.setFloat(3, ins.getValue());
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
		//A implementar
		return false;
	}

	@Override
	public boolean update(Producto upd) throws Exception{
		//A implementar
		return false;
	}

	@Override
	public Producto select(Integer id) {
		//A implementar
		return null;
	}
	
	public ProductoMasRecauda selectProductoQueMasRecauda() {
        String selectProductoQueMasRecauda = "SELECT p.nombre, p.valor, SUM(fp.cantidad * p.valor) AS total_recaudado " +
                                        	"FROM Producto p " +
                                        	"JOIN Factura_Producto fp ON p.idProducto = fp.idProducto " +
                                        	"GROUP BY p.idProducto, p.valor " +
                                        	"ORDER BY total_recaudado DESC " +
                                        	"LIMIT 1;";
        PreparedStatement ps = null;
        ProductoMasRecauda informe = new ProductoMasRecauda();
        try {
            ps = conn.prepareStatement(selectProductoQueMasRecauda);
            ResultSet rs = ps.executeQuery(selectProductoQueMasRecauda);
            while (rs.next()) {
               informe = new ProductoMasRecauda(rs.getString(1), rs.getInt(2), rs.getFloat(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
			ps.close();
			conn.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
        return informe;
	}
}
