/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author iraklis
 */
import DBConnection.DBConnection;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.sql.*;
import com.mysql.cj.api.result.Row;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static java.lang.String.format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class NewClass {
 public final static void main(String[] args) throws SQLException, ClassNotFoundException,
IOException {
     DBConnection db = new DBConnection();
 Connection connection;
 connection = db.getConnection();
 String choice="y";
 String select = "";
 while (choice.equalsIgnoreCase("y")) {
 System.out.println("Select:");
                System.out.println("1. Show records in database");
                System.out.println("2. insert client");
                System.out.println("3. export clients to excel");
                System.out.println("4. import clients from excel");
                
                Scanner in = new Scanner(System.in);
                select = in.nextLine();
                switch (select) {
                    case "1":
                        Connection conn = null;
                        Statement stmt = null;
                        Class.forName("com.mysql.jdbc.Driver");
                        //Άνοιγμα σύνδεσης
                        System.out.println("Connecting to database...");
                        conn = db.getConnection();
                        System.out.println("Creating statement...");
                        stmt = conn.createStatement();
                        String sql;
                        sql="Select firstname,lastname,birthdate,telephone,email from clients";
                        ResultSet rs =  stmt.executeQuery(sql);
                        while (rs.next()) {
                        
                         String firstname = rs.getString("firstname");
                         String lastname = rs.getString("lastname");
                         String birthdate = rs.getString("birthdate");
                         String telephone = rs.getString("telephone");
                         String email = rs.getString("email");
                         //Εμφάνιση αποτελεσμάτων
                         System.out.println("Firstname: " + firstname);
                         System.out.println("Lastname: " + lastname);
                         System.out.println("Birthdate: " + birthdate);
                         System.out.println("Telephone: " + telephone);
                         System.out.println("Email: " + email);
                         System.out.println("");                       
                    }

                        break;
                        
                    case "2":
                        Connection connection1;
                        connection1 = db.getConnection();
                        Class.forName("com.mysql.jdbc.Driver");
                      
                        //Άνοιγμα σύνδεσης
                        System.out.println("Connecting to database...");
                        conn = db.getConnection();
                        System.out.println("Creating statement...");
                        stmt = conn.createStatement();
                        String firstname1,lastname1,birthdate1,telephone1,email1,year,month,day;
                        System.out.print("Give your Firstname: ");
                        firstname1 = in.nextLine();
                        System.out.print("Give your Lastname: ");
                        lastname1 = in.nextLine();
                        System.out.println("-----Give your Birthdate-----");
                        System.out.print("Give  year: ");
                        year = in.nextLine();
                        System.out.print("Give  month: ");
                        month = in.nextLine();
                        System.out.print("Give  day: ");
                        day = in.nextLine();
                        birthdate1 = (year +"-"+month+ "-" +day);
                        System.out.print("Give your Telephone: ");
                        telephone1= in.nextLine();
                        System.out.print("Give your Email: ");
                        email1 = in.nextLine();
                        //eisagogi stoixeiwn
                        PreparedStatement stmt1 = connection1.prepareStatement("insert into clients (firstname,lastname,birthdate,telephone,email) values (?,?,?,?,?)");
                        stmt1.setString(1,firstname1);
                        stmt1.setString(2,lastname1);
                        stmt1.setString(3,birthdate1);
                        stmt1.setString(4,telephone1);
                        stmt1.setString(5,email1);
                        stmt1.executeUpdate();
                        break;
                    case "3":
                    {
                        
                     Connection conn3 = null;
                        Statement stmt3 = null;
                        Class.forName("com.mysql.jdbc.Driver");
                        //Άνοιγμα σύνδεσης
                        System.out.println("Connecting to database...");
                        conn3 = db.getConnection();
                        System.out.println("Creating statement...");
                        stmt3 = conn3.createStatement();
                        String sql3;
                        sql3="Select * from clients";
                        ResultSet rs3 =  stmt3.executeQuery(sql3);
                        while (rs3.next()) {
                         HSSFWorkbook workbook = new HSSFWorkbook(); 
                             HSSFSheet spreadsheet = workbook
                             .createSheet("CLIENT db");
                             HSSFRow row=spreadsheet.createRow(1);
                             HSSFCell cell;
                             cell=row.createCell(1);
                             cell.setCellValue("ID");
                             cell=row.createCell(2);
                             cell.setCellValue("FIRSTNAME");
                             cell=row.createCell(3);
                             cell.setCellValue("LASTNAME");
                             cell=row.createCell(4);
                             cell.setCellValue("BIRTHDATE");
                             cell=row.createCell(5);
                             cell.setCellValue("TELEPHONE");
                             cell=row.createCell(6);
                             cell.setCellValue("EMAIL");
                             int i=2;
                             while(rs3.next())
                             {
                                row=spreadsheet.createRow(i);
                                cell=row.createCell(1);
                                cell.setCellValue(rs3.getInt("id"));
                                cell=row.createCell(2);
                                cell.setCellValue(rs3.getString("firstname"));
                                cell=row.createCell(3);
                                cell.setCellValue(rs3.getString("lastname"));
                                cell=row.createCell(4);
                                cell.setCellValue(rs3.getString("birthdate"));
                                cell=row.createCell(5);
                                cell.setCellValue(rs3.getString("telephone"));
                                cell=row.createCell(6);
                                cell.setCellValue(rs3.getString("email"));
                                i++;
                             }
                             FileOutputStream out = new FileOutputStream(
                             new File("C:\\Users\\iraklis\\Documents\\new.xls"));
                             workbook.write(out);
                             out.close();
                             System.out.println(
                             "\new.xls written successfully");
                          }
                       }
                    break;
                     case "4":
                     {
                         

                                Connection conn4 = null;
                        
                        
                        //Άνοιγμα σύνδεσης
                        System.out.println("Connecting to database...");
                        conn4 = db.getConnection();
                        System.out.println("Creating statement...");
                       Statement Statement = conn4.createStatement();
                                FileInputStream input = new FileInputStream("C:\\Users\\iraklis\\Documents\\test.xls");
                                POIFSFileSystem fs = new POIFSFileSystem( input );
                                HSSFWorkbook wb = new HSSFWorkbook(fs);
                                HSSFSheet sheet = wb.getSheetAt(0);
                                 HSSFRow row;
                                for(int i=1; i<=sheet.getLastRowNum(); i++){
                                    row = sheet.getRow(i);
                                    sheet.getRow(i);
                                    
                                    String firstname = row.getCell(0).getStringCellValue();
                                    String lastname = row.getCell(1).getStringCellValue();
                                    String birthdate = row.getCell(2).getStringCellValue();
                                    String telephone = row.getCell(3).getStringCellValue();
                                    String email = row.getCell(4).getStringCellValue();
                                    String sql4 = "INSERT INTO clients (firstname,lastname,birthdate,telephone,email) VALUES('"+firstname+"','"+lastname+"','"+birthdate+"','"+telephone+"','"+email+"')";
                                      Statement.executeUpdate(sql4);
                                    System.out.println("Import rows "+i);
                                }
                                conn4.commit();
                               Statement.close();
                                conn4.close();
                                input.close();
                                System.out.println("Success import excel to mysql table");
                                         }
                                 break;
                        default:
                        System.out.println("There is no that choice...");
                       
                }
 
                        System.out.println("Do you want to continue? (y/n): ");
                       choice = in.nextLine();
 }
 }
}
 