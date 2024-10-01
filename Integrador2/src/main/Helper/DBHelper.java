package main.Helper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DBHelper {

    private Connection conn = null;
    
    public DBHelper() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String uri = "jdbc:mysql://localhost:3306/Arquitecturas";
        
        try {
            Class.forName(driver).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
        
        try {           
            conn = DriverManager.getConnection(uri, "root", "");
            conn.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (conn != null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void fillDB() throws Exception {
    	DAOFactory DAOF = DAOFactory.getDAOFactory(1);
    	dbDAO<Cliente> clienteDAO = DAOF.getClienteDAO();
    	dbDAO<Factura> facturaDAO = DAOF.getFacturaDAO();
    	dbDAO<Producto> productoDAO = DAOF.getProductoDAO();
    	dbDAO<FacPro> FacProDAO = DAOF.getFacProDAO();
        try {
            for(CSVRecord row : getData("clientes.csv")) {
                Cliente client = new Cliente(Integer.parseInt(row.get(0)), row.get(1), row.get(2));
                clienteDAO.insert(client);
            }
            System.out.println("Clientes insertados");
            for(CSVRecord row : getData("facturas.csv")) {
                Factura bill = new Factura(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)));
                facturaDAO.insert(bill);
            }
            System.out.println("Facturas insertadas");
            for(CSVRecord row : getData("facturas-productos.csv")) {
                FacPro item = new FacPro(Integer.parseInt(row.get(0)), Integer.parseInt(row.get(1)), Integer.parseInt(row.get(2)));
                FacProDAO.insert(item);
            }
            System.out.println("Facturas-Productos insertados");
            for(CSVRecord row : getData("productos.csv")) {
                Producto product = new Producto(Integer.parseInt(row.get(0)), row.get(1), Float.parseFloat(row.get(2)));
                productoDAO.insert(product);
            }
            System.out.println("Productos insertados");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }       
    
    private Iterable<CSVRecord> getData(String archivo) throws IOException {
        String path = ".\\src\\main\\resources\\" + archivo;
        Reader in = new FileReader(path);
        String[] header = {};
        CSVParser csvParser = CSVFormat.EXCEL.builder().setHeader(header).build().parse(in);
        
        Iterable<CSVRecord> record = csvParser;
        return record;
    }
}