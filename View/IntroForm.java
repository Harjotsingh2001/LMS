import javax.swing.*;
import java.awt.*;

public class IntroForm {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame introFrame = new JFrame("Library System Welcome");
            introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            introFrame.setSize(500, 300);
            introFrame.setLayout(new BorderLayout());
            
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            panel.setLayout(new BorderLayout(0, 20));
            
            // Welcome message label
            JLabel welcomeLabel = new JLabel("<html><div style='text-align: center;'>"
                + "Welcome to the Library Management System<br><br>"
                + "Developed by Team HS<br>"
                + "Supervised by Prof. Peter Dillinger<br>"
                + "for the Software Development Practice course"
                + "</div></html>");
            welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
            welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            // Enter button
            JButton enterButton = new JButton("Enter Library Management System");
            enterButton.setFont(new Font("Arial", Font.PLAIN, 14));
            enterButton.addActionListener(e -> {
                introFrame.dispose();
                new LibraryManagementSystem();
            });
            
            // Add components to panel
            panel.add(welcomeLabel, BorderLayout.CENTER);
            panel.add(enterButton, BorderLayout.SOUTH);
            
            introFrame.add(panel, BorderLayout.CENTER);
            introFrame.setLocationRelativeTo(null);
            introFrame.setVisible(true);
        });
    }
}