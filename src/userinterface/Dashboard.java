import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Dashboard();
            }
        });
    }

    public Dashboard() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setSize(800, 600);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel tab1 = new JPanel();
        tab1.setBackground(Color.WHITE);
        tab1.setLayout(new BorderLayout());

        // Load the original image
        ImageIcon originalIcon1 = new ImageIcon("src/userinterface/tony.jpg");
        Image originalImage1 = originalIcon1.getImage();

        // Resize the image to the desired dimensions
        int newWidth1 = 800;
        int newHeight1 = 600;
        Image resizedImage1 = originalImage1.getScaledInstance(newWidth1, newHeight1, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image
        ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);
        JLabel imageLabel1 = new JLabel(resizedIcon1);
        imageLabel1.setHorizontalAlignment(JLabel.CENTER);
        tab1.add(imageLabel1, BorderLayout.CENTER);

        // Create a text box with the title
        JTextField titleTextField1 = new JTextField("Tony's Italian Restaurant");
        titleTextField1.setFont(new Font("Arial", Font.BOLD, 24));
        titleTextField1.setEditable(false);
        titleTextField1.setHorizontalAlignment(JTextField.CENTER);
        tab1.add(titleTextField1, BorderLayout.NORTH);

        JPanel tab2 = new JPanel();
        tab2.setBackground(Color.WHITE);
        tab2.setLayout(new BorderLayout());

        // Load the image for tab2
        ImageIcon originalIcon2 = new ImageIcon("src/userinterface/menu.jpg");
        Image originalImage2 = originalIcon2.getImage();

        // Resize the image to the desired dimensions for tab2
        int newWidth2 = 800;
        int newHeight2 = 550;
        Image resizedImage2 = originalImage2.getScaledInstance(newWidth2, newHeight2, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image for tab2
        ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);
        JLabel imageLabel2 = new JLabel(resizedIcon2);
        imageLabel2.setHorizontalAlignment(JLabel.CENTER);
        tab2.add(imageLabel2, BorderLayout.CENTER);

        JPanel tab3 = new JPanel();
        tab3.setBackground(Color.WHITE);
        tab3.setLayout(new BorderLayout());

        // Load the image for tab3
        ImageIcon originalIcon3 = new ImageIcon("src/userinterface/wine2.jpg");
        Image originalImage3 = originalIcon3.getImage();

        // Resize the image to the desired dimensions for tab3
        int newWidth3 = 800;
        int newHeight3 = 550;
        Image resizedImage3 = originalImage3.getScaledInstance(newWidth3, newHeight3, Image.SCALE_SMOOTH);

        // Create a new ImageIcon with the resized image for tab3
        ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);
        JLabel imageLabel3 = new JLabel(resizedIcon3);
        imageLabel3.setHorizontalAlignment(JLabel.CENTER);
        tab3.add(imageLabel3, BorderLayout.CENTER);

        JPanel tab4 = new LoginPanel();

        tabbedPane.addTab("Tab 1", tab1);
        tabbedPane.addTab("Tab 2", tab2);
        tabbedPane.addTab("Tab 3", tab3);
        tabbedPane.addTab("Tab 4", tab4);

        // Set the titles for the tabs
        tabbedPane.setTitleAt(0, "Home");
        tabbedPane.setTitleAt(1, "Food Menu");
        tabbedPane.setTitleAt(2, "Drink Menu");
        tabbedPane.setTitleAt(3, "Login Tab");

        getContentPane().add(tabbedPane);

        setVisible(true);
    }


private class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPanel() {
        setLayout(new BorderLayout());

        // Create login components
        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setLayout(new GridLayout(3, 2, 10, 10));
        loginFormPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> login());

        loginFormPanel.add(usernameLabel);
        loginFormPanel.add(usernameField);
        loginFormPanel.add(passwordLabel);
        loginFormPanel.add(passwordField);
        loginFormPanel.add(new JLabel());
        loginFormPanel.add(loginButton);

        add(loginFormPanel, BorderLayout.CENTER);
    }

    private void login() {
        String username = usernameField.getText();
        String password = String.valueOf(passwordField.getPassword());

        // Perform login authentication logic here
        // ...

        // Example: Display a message dialog with the login status
        if (username.equals("admin") && password.equals("password")) {
            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
}
