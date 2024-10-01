package main;

import java.util.List;
import main.DTOs.*;
import main.Helper.DBHelper;

public class BaseDeDatos {
     public static void main(String[] args) throws Exception {
          DBHelper dbHelper = new DBHelper();
          dbHelper.dropTables();
          dbHelper.createTables();
          dbHelper.fillDB();
          dbHelper.closeConnection();
          System.out.println();
          System.out.println("_______________________________________________");
          System.out.println();
          System.out.println();
          System.out.println("_______________________________________________");
          System.out.println();
          System.out.println();
     }
}