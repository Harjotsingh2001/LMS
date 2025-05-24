package LMS.model;

public class Books {
    private int bookId;
    private String bookName;
    private String author;

    public Books(int bookId, String bookName, String author){
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
    }

    public int getBookId(){
        return bookId;
    }

    public String getBookName(){
        return bookName;
    }

    public String getAuthor(){
        return author;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Books clone() {
        return new Books(bookId, bookName, author);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", bookId, bookName, author);
       
    }

}
