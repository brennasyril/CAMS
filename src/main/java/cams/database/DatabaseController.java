/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.cams.database;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import main.java.cams.ims.ResourceItem;

/**
 *
 * @author GUS
 */
public class DatabaseController {

    final String DRIVER = "com.mysql.jdbc.Driver";
    String urlHead = "jdbc:mysql://localhost/";
    final String URL;

    final String USER = "root";
    final String PASS = "";

    Connection connection;
    Statement statement;

    public DatabaseController(String tableName) {

        URL = urlHead + tableName;

        connection = null;
        statement = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public DatabaseController() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int executeInsert(String stmt) {
        PreparedStatement prepStatement;
        ResultSet rs;
        int id = 0;
        try {
            prepStatement = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            prepStatement.executeUpdate();

            rs = prepStatement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void executeStatement(String stmt) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getSingleQueryResult(String stmt) {
        String results = "";
        ResultSet resultSet;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(stmt);

            while (resultSet.next()) {
                results = resultSet.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public ArrayList getQueryResult(String stmt) {
        String results = "";
        ResultSet resultSet = null;
        String row = "";
        ArrayList<String[]> list = new ArrayList<String[]>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(stmt);

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                String delims = "[%]";
                row = ""; 
                for (int i = 1; i <= columnCount; i++) {
                    row += resultSet.getString(i) + "%";
                }
                String[] array = row.split(delims);
                
                list.add(array);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Image getImageQuery(String query) throws FileNotFoundException, IOException {
        String results = "";
        ResultSet resultSet = null;
        String row = "";
        ArrayList<String[]> list = new ArrayList<String[]>();
        Image image = null; 
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            int columnCount = resultSet.getMetaData().getColumnCount();

            resultSet.next(); 
            Blob b = resultSet.getBlob("CRACropImage"); 
            byte barr[] = b.getBytes(1, (int) b.length()); 
            
            ByteArrayInputStream bis = new ByteArrayInputStream(barr); 
            BufferedImage read = ImageIO.read(bis); 
            System.out.println("bladjsjfds");
            image = new Image(new ByteArrayInputStream(barr)); 
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return image; 
    }
    
    public String getColumns(String stmt) {
        String results = "";
        ResultSet resultSet = null;
        String row = "";
        ArrayList<String[]> list = new ArrayList<String[]>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(stmt);

            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    row += resultSet.getMetaData().getColumnName(i)+ "%";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    public String fillComboQuery(String stmt) {
        ResultSet resultSet;
        String row = "";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(stmt);
            int columnCount = resultSet.getMetaData().getColumnCount();

            while (resultSet.next()) {

                for (int i = 1; i <= columnCount; i++) {
                    row += resultSet.getString(i) + "%";
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return row;
    }

    public int getIDQuery(String stmt) {
        ResultSet resultSet;
        int IDNum = 0;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(stmt);
            if (resultSet.next()) {
                IDNum = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return IDNum;
    }
}
