import model.Library;
import DAO.LibraryDAO;

import java.awt.event.ActionEvent;

import javax.swing.text.View;

public class MainController {
    private MainView mainView;
    private LibraryDAO libraryDAO;

    public MainController(MainView mainView, LibraryDAO libraryDAO ){
        this.mainView = mainView;
        this.libraryDAO = libraryDAO;

        mainView.setShowButtonActionListener(this::onClickShowButton);
        mainView.setIssueButtonActionListener(this::onClickIssueButton);
        // mainView.setReturnButtonActionListener(this::onClickReturnButton);

    }

    public void onClickShowButton(ActionEvent e) {
        int borrowId = mainView.getborrowId();
        if (borrowId > 0){
            Library library = libraryDAO.getDataById(borrowId);
            if(library != null) {
                mainView.showBookName(library.getBookName());
                mainView.showAuthor(library.getAuthor());
                mainView.showIssueDate(library.getIssueDate());
                mainView.showReturnDate(library.getReturnDate());
                return;
            }
            else{
                mainView.showWarning("An data with that Id does not exist!");
            }

        }
    }

    public void onClickIssueButton(ActionEvent e){
        int borrowId = mainView.getborrowId();
        String bookName = mainView.getBookName();
        String  author = mainView.getAuthor();
        String issueDate = mainView.getIssueDate();
        String returnDate = mainView.getReturnDate();
        if (borrowId > 0 && !bookName.isBlank()) {
            if (libraryDAO.insertData(new Library(borrowId, bookName, author, issueDate, returnDate)));{
                mainView.showMessageDialog("Data added successfully");
            }
            {
                 mainView.showWarning("Unable to add data.");
            }
        }
        else{
            mainView.showWarning("Please provide valid Id");
        }
    }

}
