package LMS.DAO;

import LMS.model.Books;

public abstract class BooksAbstract implements BooksDAO{
   
    @Override
    public boolean updateBooks(int bookId, Books books) {
        if ( ! deleteBook(bookId) ) return false;
        return insertBook(books);
   }
}
