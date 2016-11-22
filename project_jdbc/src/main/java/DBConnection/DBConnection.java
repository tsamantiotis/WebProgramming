/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBConnection;

/**
 *
 * @author iraklis
 */
import java.io.*;
import java.sql.*;
import java.util.Properties;

    
public class DBConnection {
 public static Connection getConnection() throws FileNotFoundException, IOException,
ClassNotFoundException, SQLException {
 Properties props = new Properties();
 
 FileInputStream fis = null;
 Connection con = null;
 fis = new FileInputStream("data.properties");
 props.load(fis);
 // load the Driver Class
 Class.forName(props.getProperty("DB_DRIVER_CLASS"));
 // create the connection now
 con = DriverManager.getConnection(props.getProperty("DB_URL"),
 props.getProperty("DB_USERNAME"),
 props.getProperty("DB_PASSWORD"));
 return con;
 }
}
