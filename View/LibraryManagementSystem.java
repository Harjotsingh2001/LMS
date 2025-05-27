import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManagementSystem extends JFrame implements MainView {
    private JFrame frame;
    private JTextField borrowIdField;
    private JTextField bookNameField;
    private JTextField bookAuthorField;
    private JTextField issueDateField;
    private JTextField returnDateField;
    private JButton showButton = new JButton("Show");
    private JButton issueButton = new JButton("Issue Book");
     private JButton returnButton = new JButton("Return Book");

    // private List<Book> books = new ArrayList<>();
    // private DefaultListModel<String> listModel = new DefaultListModel<>();
    // private JList<String> bookList;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LibraryManagementSystem window = new LibraryManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Application failed to initialize: " + e.getMessage(), 
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    public LibraryManagementSystem() {
        initialize();
    }
    
    private void initialize() {
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout(10, 10));

        // Main content panel
        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();
        JPanel listPanel = createListPanel();

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(listPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        borrowIdField = addFormField(panel, "Borrow ID:");
        bookNameField = addFormField(panel, "Book Name:");
        bookAuthorField = addFormField(panel, "Book Author:");
        issueDateField = addDateField(panel, "Issue Date:", true);
        returnDateField = addDateField(panel, "Return Date:", false);

        return panel;
    }

    private JTextField addFormField(JPanel parent, String label) {
        JTextField field = new JTextField();
        parent.add(new JLabel(label));
        parent.add(field);
        return field;
    }

    private JTextField addDateField(JPanel parent, String label, boolean prefill) {
        JTextField field = new JTextField();
        if(prefill) {
            field.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        }
        parent.add(new JLabel(label));
        parent.add(field);
        return field;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panel.setBorder(BorderFactory.createTitledBorder("Operations"));
        
        // panel.add(createOperationButton("Book Issue", this::handleIssueBook));
        // panel.add(createOperationButton("Book Show", this::showButton));
        // panel.add(createOperationButton("Book Return", this::handleReturnBook));
         panel.add(showButton);
         panel.add(issueButton);
         panel.add(returnButton);

        return panel;
    }

    // private JButton createOperationButton(String text, ActionListener handler) {
    //     JButton btn = new JButton(text);
    //     btn.setPreferredSize(new Dimension(150, 40));
    //     btn.addActionListener(handler);
    //     return btn;
    // }

    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Book Records"));
        
        // bookList = new JList<>(listModel);
        // JScrollPane scrollPane = new JScrollPane(bookList);
        // panel.add(scrollPane);
        
        return panel;
    }

    // private void handleIssueBook(ActionEvent e) {
    //     try {
    //         validateEmptyFields();
    //         validateDates();
            
    //         Book book = new Book(
    //             borrowIdField.getText(),
    //             bookNameField.getText(),
    //             bookAuthorField.getText(),
    //             issueDateField.getText(),
    //             returnDateField.getText()
    //         );
            
    //         books.add(book);
    //         listModel.addElement(book.toString());
    //         clearFields();
            
    //         showSuccess("Book issued successfully!");
            
    //     } catch (IllegalArgumentException ex) {
    //         showError(ex.getMessage());
    //     } catch (Exception ex) {
    //         showError("Database error: " + ex.getMessage());
    //         ex.printStackTrace();
    //     }
    // }

    // private void showButton(ActionEvent e) {
    //     try {
    //         listModel.clear();
    //         if(books.isEmpty()) {
    //             showInformation("No books in the system");
    //             return;
    //         }
            
    //         for (Book book : books) {
    //             listModel.addElement(book.toString());
    //         }
            
    //     } catch (Exception ex) {
    //         showError("Error loading books: " + ex.getMessage());
    //         ex.printStackTrace();
    //     }
    // }

    // private void handleReturnBook(ActionEvent e) {
    //     try {
    //         String borrowId = borrowIdField.getText().trim();
    //         if(borrowId.isEmpty()) {
    //             throw new IllegalArgumentException("Please enter Borrow ID");
    //         }
            
    //         boolean removed = books.removeIf(book -> book.getBorrowId().equals(borrowId));
            
    //         if(!removed) {
    //             throw new IllegalArgumentException("Book with ID " + borrowId + " not found");
    //         }
            
    //         listModel.removeElement(borrowId);
    //         clearFields();
    //         showSuccess("Book returned successfully!");
            
    //     } catch (IllegalArgumentException ex) {
    //         showError(ex.getMessage());
    //     } catch (Exception ex) {
    //         showError("Database error: " + ex.getMessage());
    //         ex.printStackTrace();
    //     }
    // }

    // private void validateEmptyFields() throws IllegalArgumentException {
    //     if(borrowIdField.getText().trim().isEmpty() ||
    //        bookNameField.getText().trim().isEmpty() ||
    //        bookAuthorField.getText().trim().isEmpty() ||
    //        issueDateField.getText().trim().isEmpty() ||
    //        returnDateField.getText().trim().isEmpty()) {
    //         throw new IllegalArgumentException("All fields are required!");
    //     }
    // }

    // private void validateDates() throws IllegalArgumentException {
    //     SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    //     sdf.setLenient(false);
        
    //     try {
    //         Date issueDate = sdf.parse(issueDateField.getText());
    //         Date returnDate = sdf.parse(returnDateField.getText());
            
    //         if(returnDate.before(issueDate)) {
    //             throw new IllegalArgumentException("Return date cannot be before issue date");
    //         }
            
    //     } catch (ParseException ex) {
    //         throw new IllegalArgumentException("Invalid date format. Use DD-MM-YYYY format");
    //     }
    // }

    // private void clearFields() {
    //     borrowIdField.setText("");
    //     bookNameField.setText("");
    //     bookAuthorField.setText("");
    //     issueDateField.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
    //     returnDateField.setText("");
    // }

    // private void showError(String message) {
    //     JOptionPane.showMessageDialog(frame, message, "Error", JOptionPane.ERROR_MESSAGE);
    // }

    // private void showSuccess(String message) {
    //     JOptionPane.showMessageDialog(frame, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    // }

    // private void showInformation(String message) {
    //     JOptionPane.showMessageDialog(frame, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    // }

    // private class Book {
    //     private final String borrowId;
    //     private final String bookName;
    //     private final String bookAuthor;
    //     private final String issueDate;
    //     private final String returnDate;
        
        // public Book(String borrowId, String bookName, String bookAuthor, String issueDate, String returnDate) {
        //     this.borrowId = borrowId;
        //     this.bookName = bookName;
        //     this.bookAuthor = bookAuthor;
        //     this.issueDate = issueDate;
        //     this.returnDate = returnDate;
        // }
        
        // public String getBorrowId() { return borrowId; }
        
        // @Override
        // public String toString() {
        //     return String.format("%s - %s by %s (Issued: %s, Return: %s)", 
        //         borrowId, bookName, bookAuthor, issueDate, returnDate);
        // }
    // }

    @Override
    public void enableWindow(boolean enabled) {
        setEnabled(enabled);
    }

    @Override
  public void showButton(int borrowId) {
        borrowIdField.setText( String.valueOf(borrowId) );
    }


    @Override
    public void showBookName(String bookName) {
        bookNameField.setText(bookName);
    }

    @Override
    public void showAuthor(String author) {
        bookAuthorField.setText(author);
    }

    @Override
    public void showIssueDate(String issueDate) {
        issueDateField.setText(issueDate);
    }

    @Override
    public void showReturnDate(String returnDate) {
        returnDateField.setText(returnDate);
    }

    @Override
    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showWarning(String message) {
        JOptionPane.showMessageDialog(this, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public boolean confirmationDialog(String message) {
        return JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, message, "Question", JOptionPane.YES_NO_OPTION);
    }

    @Override
    public int getborrowId() {
        int borrowId = 0;
        try {
            borrowId = Integer.parseInt(borrowIdField.getText());
        } catch (NumberFormatException ignored) {
        
        }
        return borrowId;
    }

    @Override
    public String getBookName() {
       return bookNameField.getText();
    }

    @Override
    public String getAuthor() {
        return bookAuthorField.getText();
    }

    @Override
    public String getIssueDate() {
        return issueDateField.getText();
    }

    @Override
    public String getReturnDate() {
        return returnDateField.getText();
    }

    @Override
    public void setShowButtonActionListener(ActionListener listener) {
        showButton.addActionListener(listener);
    }

    @Override
    public void setIssueButtonActionListener(ActionListener listener) {
         issueButton.addActionListener(listener);
    }

    @Override
    public void setReturnButtonActionListener(ActionListener listener) {
         returnButton.addActionListener(listener);
     }
    // @Override
    // public void setListButtonActionListener(ActionListener listener) {
        

    @Override
    public void getFocus() {
       requestFocus();
    }
}