package LMS.DAO;

import java.util.List;

import LMS.model.Books;

public interface BooksDAO {

    boolean insertBook(Books books);

    Books getBookById(int bookId);
    Books getBookByName(String bookName);
    List<Books> getAllBooks();

    boolean updateBooks(int number, Books books);

    boolean deleteBook(int bookId);

    // int lastAccountNumber();

} 