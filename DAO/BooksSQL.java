package LMS.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import LMS.model.Books;

public class BooksSQL extends BooksAbstract{
    private static final String SQL_MARIADB_HOST = "localhost";
    private static final String SQL_MARIADB_PORT = "3306";
    private static final String SQL_MARIADB_USER = "dblms";
    private static final String SQL_MARIADB_PASSWORD = "secret123";
    private static final String SQL_MARIADB_DATABASE = "dblms";

    private static final String SQL_MARIADB_CONNECTOR = "org.mariadb.jdbc.Driver";

    private Connection sqlConnection;

    public BooksSQL() {
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
    public boolean insertBook(Books books) {
        // INSERT INTO account (number, owner, balance) VALUES (?, ?, ?);
        try {
            PreparedStatement sqlStatement = sqlConnection.prepareStatement(
                "INSERT INTO books (bookId, bookName, author) VALUES (?, ?, ?)");
            sqlStatement.setInt(1, books.getBookId());
            sqlStatement.setString(2, books.getBookName());
            sqlStatement.setString(3, books.getAuthor());
            return sqlStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Books getBookById(int number) {
        try {
            PreparedStatement sqlStatement = sqlConnection.prepareStatement(
                "SELECT bookName,author FROM books WHERE bookId = ?");
            sqlStatement.setInt(1, bookId);
            ResultSet dataTable = sqlStatement.executeQuery();
            if (dataTable.next()) {
                return new Books(
                        bookId,
                        dataTable.getString("bookName"), 
                        dataTable.getString("author")
                    );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Books getBookByName(String owner) {
        
        throw new UnsupportedOperationException("Unimplemented method 'getBookByName'");
    }

    @Override
    public List<Books> getAllBooks() {
        List<Books> accountList = new ArrayList<>();
        // SELECT * FROM account;
        try {
            Statement sqlStatement = sqlConnection.createStatement();
            ResultSet dataTable = sqlStatement.executeQuery("SELECT bookId,bookName,author FROM books");
            while (dataTable.next()) {
                accountList.add(
                    new Books(
                        dataTable.getInt("bookId"), 
                        dataTable.getString("bookName"), 
                        dataTable.getString("author")
                    ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean deleteBook(int bookId) {
        // DELETE FROM account WHERE number = ?;
        try {
            PreparedStatement sqlStatement = sqlConnection.prepareStatement(
                "DELETE FROM books WHERE bookId = ?");
            sqlStatement.setInt(1, bookId);
            return sqlStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
