package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Library;


public class LibrarySQL extends LibraryAbstract{
    private static final String SQL_MARIADB_HOST = "localhost";
    private static final String SQL_MARIADB_PORT = "3306";
    private static final String SQL_MARIADB_USER = "root";
    private static final String SQL_MARIADB_PASSWORD = "secret123";
    private static final String SQL_MARIADB_DATABASE = "dblms";

    private static final String SQL_MARIADB_CONNECTOR = "org.mariadb.jdbc.Driver";

    private Connection sqlConnection;
    

    public LibrarySQL() {
        try {
            Class.forName(SQL_MARIADB_CONNECTOR);
        } catch (ClassNotFoundException e) {
            System.err.println("Could not find required SQL driver!");
            e.printStackTrace();
            System.exit(-1);
        }
        connect();
    }

    private void connect() {
        try {
            sqlConnection = DriverManager.getConnection(
                "jdbc:mariadb://" +
                SQL_MARIADB_HOST + ":" + SQL_MARIADB_PORT + "/" +
                SQL_MARIADB_DATABASE, SQL_MARIADB_USER, SQL_MARIADB_PASSWORD);
        } catch (SQLException e) {
            System.err.println("Got some problem when establishing SQL connection");
            e.printStackTrace();
            System.exit(-2);
        }        
    }

    @Override
    public boolean insertData(Library library) {
        try {
            PreparedStatement sqlStatement = sqlConnection.prepareStatement(
                "INSERT INTO libaryManagement (borrowId, bookName, author, issueDate, returnDate)"+
                "values (?,?,?,?,?)");
            sqlStatement.setInt(1, library.getborrowId());
            sqlStatement.setString(2, library.getBookName());
            sqlStatement.setString(3, library.getAuthor());
            sqlStatement.setString(4, library.getIssueDate());
            sqlStatement.setString(5, library.getReturnDate());
            return sqlStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Library getDataById(int borrowId) {
        try {
            PreparedStatement sqlStatement = sqlConnection.prepareStatement(
                "SELECT *"+ "FROM libraryManagement " +  
                "WHERE borrowId = ?");
            sqlStatement.setInt(1, borrowId);
            ResultSet dataTable = sqlStatement.executeQuery();
            if (dataTable.next()) {
                return new Library(
                        borrowId,
                        dataTable.getString("bookName"), 
                        dataTable.getString("author"),
                        dataTable.getString("issueDate"),
                        dataTable.getString("returnDate")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Library getDataByBookName(String bookName) {
        throw new UnsupportedOperationException("Unimplemented method 'getDataByBookName'");
    }

    @Override
    public List<Library> getAllData() {
        List<Library> libraryList = new ArrayList<>();
        // SELECT * FROM account;
        try {
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet dataTable = sqlStatement.executeQuery(
                "SELECT * "+"FROM libraryManagement");
            while (dataTable.next()) {
                libraryList.add(
                    new Library(
                    dataTable.getInt("borrowId"),
                    dataTable.getString("bookname"),
                    dataTable.getString("author"),
                    dataTable.getString("issueDate"),
                    dataTable.getString("returnDate")
                    ));
                }
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
        return libraryList;
    }

    @Override
    public boolean deleteLibrary(int borrowId) {
         try {
            PreparedStatement sqlStatement = sqlConnection.prepareStatement(
                "DELETE FROM libraryManagement WHERE borrowId = ?");
            sqlStatement.setInt(1, borrowId);
            return sqlStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int lastDataNumber() {
         try {
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet dataTable = sqlStatement.executeQuery(
                "SELECT MAX(libraryManagement) AS maxnumber FROM borrowId");
            if (dataTable.next()) {
                return dataTable.getInt("maxnumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
