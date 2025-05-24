package LMS.DAO;

import java.util.ArrayList;
import java.util.List;

import LMS.model.Books;

public class BooksInMemory /*extends BooksAbstract*/ {
    private List<Books> bookList = new ArrayList<Books>();

    // @Override
    public boolean insertBook(Books books){
        if ( findBookById(books.getBookId()) != null )
            return false;
        bookList.add(books.clone());
        return true;
    }

    private Books findBookById(int bookId) {
        for (Books books : bookList) {
            if (books.getBookId() == bookId)
                return books; 
        }
        return null;
    }

    //  @Override
    public Books getBookById(int bookId) {
        return findBookById(bookId).clone();
    }

    // @Override
    public Books getbookByName(String bookName) {
        // TODO implement this indiviudally
        throw new UnsupportedOperationException("Unimplemented method 'getAccountByOwner'");
    }

    //  @Override
    public boolean deletebook(int bookId) {
        for (int i = 0; i < bookList.size(); i++) {
            if ( bookList.get(i).getBookId() == bookId ) {
                bookList.remove(i);
                return true;
            }
        }
        return false;
    }

    //  @Override
    public List<Books> getAllBooks() {
        List<Books> copyList = new ArrayList<>();
        for (Books books : bookList) {
            copyList.add(books.clone());
        }
        return copyList;
    }

}
