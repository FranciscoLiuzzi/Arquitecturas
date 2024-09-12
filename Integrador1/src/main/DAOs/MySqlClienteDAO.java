package main.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.DTOs.ClienteMasFacturas;
import main.DTOs.ProductoMasRecauda;
import main.Objects.Cliente;

public class MySqlClienteDAO implements ClienteDAO{
	
	private Connection conn;
	
	public MySqlClienteDAO(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public int insert(Cliente ins) throws Exception{
		String insert = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		try {
            ps.setInt(1, ins.getId());
    		ps.setString(2, ins.getName());
    		ps.setString(3, ins.getEmail());
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
		String delete = "DELETE FROM Cliente WHERE idCliente = ?";
		PreparedStatement ps = conn.prepareStatement(delete);
		try {
			ps.setInt(1, id);
            if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		ps.close();
		conn.commit();
		return false;
	}

	@Override
	public boolean update(Cliente upd) throws Exception{
		String update = "UPDATE Cliente SET nombre = ?, email = ? WHERE idCliente = ?";
		PreparedStatement ps = conn.prepareStatement(update);
		try {
			ps.setString(1, upd.getName());
			ps.setString(2, upd.getEmail());
			ps.setInt(3, upd.getId());
			if (ps.executeUpdate() == 0) {
                throw new Exception("No se pudo insertar");
            }	
		} catch (SQLException e) {
            e.printStackTrace();
        }
		ps.close();
		conn.commit();
		return false;
	}

	@Override
	public Cliente select(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ClienteMasFacturas> selectClientesQueMasFacturan() {
        String selectClientesConMasFacturacion = "SELECT c.nombre, c.email, SUM(p.valor * fp.cantidad) AS total_facturado " +
									             "FROM Cliente c " +
									             "JOIN Factura ON c.idCliente = Factura.idCliente " +
									             "JOIN Factura_Producto fp ON Factura.idFactura = fp.idFactura " +
									             "JOIN Producto p ON fp.idProducto = p.idProducto " +
									             "GROUP BY c.idCliente, c.nombre, c.email " +
									             "ORDER BY SUM(p.valor * fp.cantidad) DESC; "; 
        PreparedStatement ps = null;
        List<ClienteMasFacturas> informe = new ArrayList<>();
        try {
            ps = conn.prepareStatement(selectClientesConMasFacturacion);
            ResultSet rs = ps.executeQuery(selectClientesConMasFacturacion);
            while (rs.next()) {
            	ClienteMasFacturas detail = new ClienteMasFacturas(rs.getString(1), rs.getString(2), rs.getFloat(3));
                informe.add(detail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }              
        try {
            ps.close();
            conn.commit();
        } catch (SQLException e) {
             e.printStackTrace();
        }                
        return informe;
    }  
}
