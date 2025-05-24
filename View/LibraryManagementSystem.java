import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LibraryManagementSystem {
    private JFrame frame;
    private JTextField borrowIdField;
    private JTextField bookNameField;
    private JTextField bookAuthorField;
    private JTextField issueDateField;
    private JTextField returnDateField;
    
    private List<Book> books = new ArrayList<>();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LibraryManagementSystem window = new LibraryManagementSystem();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public LibraryManagementSystem() {
        initialize();
    }
    
    private void initialize() {
        frame = new JFrame("Library Management System");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2, 5, 5));
        frame.getContentPane().add(inputPanel, BorderLayout.NORTH);
        
        // Borrow ID
        JLabel borrowIdLabel = new JLabel("Borrow ID:");
        inputPanel.add(borrowIdLabel);
        
        borrowIdField = new JTextField();
        inputPanel.add(borrowIdField);
        
        // Book Name
        JLabel bookNameLabel = new JLabel("Book Name:");
        inputPanel.add(bookNameLabel);
        
        bookNameField = new JTextField();
        inputPanel.add(bookNameField);
        
        // Book Author
        JLabel bookAuthorLabel = new JLabel("Book Author:");
        inputPanel.add(bookAuthorLabel);
        
        bookAuthorField = new JTextField();
        inputPanel.add(bookAuthorField);
        
        // Issue Date
        JLabel issueDateLabel = new JLabel("Issue Date (dd-MM-yyyy):");
        inputPanel.add(issueDateLabel);
        
        issueDateField = new JTextField();
        issueDateField.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        inputPanel.add(issueDateField);
        
        // Return Date
        JLabel returnDateLabel = new JLabel("Return Date (dd-MM-yyyy):");
        inputPanel.add(returnDateLabel);
        
        returnDateField = new JTextField();
        inputPanel.add(returnDateField);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        
        // Book Issue Button
        JButton bookIssueButton = new JButton("Book Issue");
        bookIssueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                issueBook();
            }
        });
        buttonPanel.add(bookIssueButton);
        
        // Book Show Button
        JButton bookShowButton = new JButton("Book Show");
        bookShowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showBooks();
            }
        });
        buttonPanel.add(bookShowButton);
        
        // Book Return Button
        JButton bookReturnButton = new JButton("Book Return");
        bookReturnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });
        buttonPanel.add(bookReturnButton);
        
        // List Panel
        JPanel listPanel = new JPanel();
        frame.getContentPane().add(listPanel, BorderLayout.SOUTH);
        listPanel.setLayout(new BorderLayout(0, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        listPanel.add(scrollPane);
        
        JList<String> bookList = new JList<>(listModel);
        scrollPane.setViewportView(bookList);
    }
    
    private void issueBook() {
        try {
            String borrowId = borrowIdField.getText();
            String bookName = bookNameField.getText();
            String bookAuthor = bookAuthorField.getText();
            String issueDate = issueDateField.getText();
            String returnDate = returnDateField.getText();
            
            if (borrowId.isEmpty() || bookName.isEmpty() || bookAuthor.isEmpty() || 
                issueDate.isEmpty() || returnDate.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Book book = new Book(borrowId, bookName, bookAuthor, issueDate, returnDate);
            books.add(book);
            listModel.addElement(book.toString());
            
            clearFields();
            JOptionPane.showMessageDialog(frame, "Book issued successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error issuing book: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showBooks() {
        listModel.clear();
        for (Book book : books) {
            listModel.addElement(book.toString());
        }
    }
    
    private void returnBook() {
        String borrowId = borrowIdField.getText();
        if (borrowId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Borrow ID to return book", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        boolean found = false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBorrowId().equals(borrowId)) {
                books.remove(i);
                found = true;
                break;
            }
        }
        
        if (found) {
            showBooks();
            clearFields();
            JOptionPane.showMessageDialog(frame, "Book returned successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Book with Borrow ID " + borrowId + " not found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearFields() {
        borrowIdField.setText("");
        bookNameField.setText("");
        bookAuthorField.setText("");
        issueDateField.setText(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
        returnDateField.setText("");
    }
    
    private class Book {
        private String borrowId;
        private String bookName;
        private String bookAuthor;
        private String issueDate;
        private String returnDate;
        
        public Book(String borrowId, String bookName, String bookAuthor, String issueDate, String returnDate) {
            this.borrowId = borrowId;
            this.bookName = bookName;
            this.bookAuthor = bookAuthor;
            this.issueDate = issueDate;
            this.returnDate = returnDate;
        }
        
        public String getBorrowId() {
            return borrowId;
        }
        
        // @Override
        // public String toString() {
        //     return "ID: " + borrowId + " | Book: " + bookName + " | Author: " + bookAuthor + 
        //            " | Issued: " + issueDate + " | Return: " + returnDate;
        // }
    }
}