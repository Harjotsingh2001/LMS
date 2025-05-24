package LMS.controller;

import javax.swing.JFrame;

import LMS.DAO.BooksDAO;
import LMS.model.Books;
import LMS.DAO.BooksSQL;
import LMS.DAO.BooksInMemory;
public class Main {
    public static void main(String[] args) {
        // JFrame frame = new jFrame();

        // fra0
        // BooksDAO accountDB = new BooksInMemory();
        BooksSQL booksDB = new BooksSQL();

        Books specificBook = booksDB.getBookById(1);
        System.out.println("My precious: " + specificBook);
        
    }
    
}
