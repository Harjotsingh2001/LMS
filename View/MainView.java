import java.util.Date;

import java.awt.event.ActionListener;


public interface MainView {

    void showButton(int borrowId);
    void showBookName(String bookName);
    void showAuthor(String author);
    void showIssueDate(String issueDate);
    void showReturnDate(String returnDate);

    void showMessageDialog(String message);
    void showWarning(String message);
    boolean confirmationDialog(String message);

    int getborrowId();
    String getBookName();
    String getAuthor();
    String getIssueDate();
    String getReturnDate();

    
    void setShowButtonActionListener(ActionListener listener);
    void setIssueButtonActionListener(ActionListener listener);
    void setReturnButtonActionListener(ActionListener listener);
    // void setListButtonActionListener(ActionListener listener);

    void enableWindow(boolean enabled);
    void getFocus();
    

}
