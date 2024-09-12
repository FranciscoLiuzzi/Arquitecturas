package main;

import java.util.List;
import main.DAOs.*;
import main.DTOs.*;
import main.Factories.DAOFactory;
import main.Helper.DBHelper;

public class BaseDeDatos {
     public static void main(String[] args) throws Exception {
          DBHelper dbHelper = new DBHelper();
          dbHelper.dropTables();
          dbHelper.createTables();
          dbHelper.fillDB();
          dbHelper.closeConnection();
          DAOFactory systemFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL_JDBC);

          System.out.println();
          System.out.println("_______________________________________________");
          System.out.println();
          
          ProductoDAO producto = systemFactory.getProductoDAO();
          ProductoMasRecauda productoConMasRecaudacion = producto.selectProductoQueMasRecauda();
          
          System.out.println(productoConMasRecaudacion);
          
          ClienteDAO cliente = systemFactory.getClienteDAO();
          List<ClienteMasFacturas> clientesConMasFacturacion = cliente.selectClientesQueMasFacturan();
          
          System.out.println();
          System.out.println("_______________________________________________");
          System.out.println();
          System.out.println("Clientes que más facturan:");
          for (ClienteMasFacturas clienteMF : clientesConMasFacturacion) {
               System.out.println(clienteMF);
          }
          System.out.println();
          systemFactory.closeConnection();
     }
}