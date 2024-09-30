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

import main.DAOs.dbDAO;
import main.Factories.DAOFactory;
import main.Objects.Cliente;
import main.Objects.FacPro;
import main.Objects.Factura;
import main.Objects.Producto;

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
            conn = DriverManager.getConnection(uri, "root", "naranja");
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

    public void dropTables() throws SQLException {
        String dropProducto = "DROP TABLE IF EXISTS Producto";
        this.conn.prepareStatement(dropProducto).execute();
        this.conn.commit();
        
        String dropFactura_Producto = "DROP TABLE IF EXISTS Factura_Producto";
        this.conn.prepareStatement(dropFactura_Producto).execute();
        this.conn.commit();
        
        String dropFactura = "DROP TABLE IF EXISTS Factura";
        this.conn.prepareStatement(dropFactura).execute();
        this.conn.commit();

        String dropCliente = "DROP TABLE IF EXISTS Cliente";
        this.conn.prepareStatement(dropCliente).execute();
        this.conn.commit();
    }

    public void createTables() throws SQLException {
        String tableCliente = "CREATE TABLE IF NOT EXISTS Cliente(" +
            "idCliente INT NOT NULL, " +
            "nombre VARCHAR(500), " +
            "email VARCHAR(500), " +
            "CONSTRAINT Cliente_pk PRIMARY KEY (idCliente))";
        this.conn.prepareStatement(tableCliente).execute();
        this.conn.commit();

        String tableProducto = "CREATE TABLE IF NOT EXISTS Producto(" +
            "idProducto INT NOT NULL, " + 
            "nombre VARCHAR(50), " +
            "valor INT NOT NULL, " +
            "CONSTRAINT Producto_pk PRIMARY KEY (idProducto));" ;
        this.conn.prepareStatement(tableProducto).execute();
        this.conn.commit();

        String tableFacPro = "CREATE TABLE IF NOT EXISTS Factura_Producto(" +
            "idFactura INT NOT NULL, " + 
            "idProducto INT NOT NULL, " +
            "cantidad INT NOT NULL);" ;
        this.conn.prepareStatement(tableFacPro).execute();
        this.conn.commit();
        
        String tableFactura = "CREATE TABLE IF NOT EXISTS Factura(" +
            "idFactura INT NOT NULL, " + 
            "idCliente INT, " +
            "CONSTRAINT Factura_pk PRIMARY KEY (idFactura), " +
            "CONSTRAINT FOREIGN KEY (idCLiente) REFERENCES Cliente (idCliente));" ;
        this.conn.prepareStatement(tableFactura).execute();
        this.conn.commit();     
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