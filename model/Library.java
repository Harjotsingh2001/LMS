package model;

import java.sql.Date;

public class Library {
    private int borrowId;
    private String bookName;
    private String author;
    private String issueDate;
    private String returnDate;

    public Library(int borrowId, String bookName, String author, String issueDate, String returnDate){
        this.borrowId = borrowId;
        this.bookName = bookName;
        this.author = author;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public int getborrowId(){
        return borrowId;
    }

    public String getBookName(){
        return bookName;
    }

    public String getAuthor(){
        return author;
    }

    public String getIssueDate(){
        return issueDate;
    }

    public String getReturnDate(){
        return returnDate;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setIssueDate(String issueDate){
        this.issueDate = issueDate;
    }
    public void setReturnDate(String returnDate){
        this.returnDate = returnDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Library clone() {
        return new Library(borrowId, bookName, author, issueDate, returnDate);
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s) %d %d", borrowId, bookName, author, issueDate, returnDate);
       
    }

}
