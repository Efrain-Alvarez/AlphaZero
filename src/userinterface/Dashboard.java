package userinterface;

import backend.database.DatabaseAdapter;
import backend.database.InventoryItem;
import backend.database.Reservation;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Dashboard extends JFrame implements AutoCloseable {
    DatabaseAdapter db;

    public Dashboard() {
        try {
            db = new DatabaseAdapter("config.ini");

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

            JPanel foodMenuPanel = new JPanel();
            foodMenuPanel.setBackground(Color.WHITE);
            foodMenuPanel.setLayout(new BorderLayout());

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
            foodMenuPanel.add(imageLabel2, BorderLayout.CENTER);

            JPanel drinkMenuPanel = new JPanel();
            drinkMenuPanel.setBackground(Color.WHITE);
            drinkMenuPanel.setLayout(new BorderLayout());

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
            drinkMenuPanel.add(imageLabel3, BorderLayout.CENTER);

            JPanel inventoryPanel = new InventoryPanel(db);
            JPanel reservationsPanel = new ReservationPanel(db);

            tabbedPane.addTab("Home", tab1);
            tabbedPane.addTab("Food Menu", foodMenuPanel);
            tabbedPane.addTab("Drink Menu", drinkMenuPanel);
            tabbedPane.addTab("Inventory", inventoryPanel);
            tabbedPane.addTab("Reservations", reservationsPanel);

            getContentPane().add(tabbedPane);

            setVisible(true);
        }  catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
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

            // Example: Open a new file after successful login
            if (username.equals("admin") && password.equals("password")) {
                // Open the new file
                File file = new File("src/userinterface/Test.txt");
                try {
                    Desktop.getDesktop().open(file);
                    JOptionPane.showMessageDialog(this, "Login successful! File opened.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private class ReservationPanel extends JPanel {
        private JComboBox<String> reservationComboBox;
        private JTextField nameTextField;
        private JTextField emailTextField;
        private JButton submitButton;

        public ReservationPanel(DatabaseAdapter db) {
            setLayout(new BorderLayout());

            JPanel reservationFormPanel = new JPanel();
            reservationFormPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            reservationFormPanel.setLayout(new FlowLayout());

            JLabel reservationLabel = new JLabel("Select Reservation:");
            reservationComboBox = new JComboBox<>();

            JLabel nameLabel = new JLabel("Name:");
            nameTextField = new JTextField(20);

            JLabel emailLabel = new JLabel("Email:");
            emailTextField = new JTextField(20);

            submitButton = new JButton("Submit");
            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        refreshReservationList(db.getReservations());
                    } catch (SQLException s) {
                        System.err.println(s);
                        return;
                    }
                }
            });
            reservationFormPanel.add(reservationLabel);
            reservationFormPanel.add(reservationComboBox);
            reservationFormPanel.add(nameLabel);
            reservationFormPanel.add(nameTextField);
            reservationFormPanel.add(emailLabel);
            reservationFormPanel.add(emailTextField);
            reservationFormPanel.add(submitButton);

            add(reservationFormPanel, BorderLayout.CENTER);
        }

        public void refreshReservationList(ArrayList<Reservation> reservationsList) {
            reservationComboBox = new JComboBox<>();
            for (Reservation r : reservationsList)
                reservationComboBox.addItem(r.toString());
        }
    }

    private class InventoryPanel extends JPanel {
        private JButton addButton;
        private JTable inventoryTable;

        public InventoryPanel(DatabaseAdapter db) {
            setLayout(new BorderLayout());

            JPanel inventoryPanel = new JPanel();
            inventoryPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
            inventoryPanel.setLayout(new FlowLayout());
            inventoryTable = new JTable();

            JLabel tableLabel = new JLabel("Restaurant Inventory");
            addButton = new JButton("Add Item");
            addButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        refreshInventoryList(db.getItems());
                    } catch (Exception f) {
                        System.err.println(f.getMessage());
                    }
                }
            });
            inventoryPanel.add(tableLabel);
            add(inventoryPanel, BorderLayout.CENTER);
            add(addButton);
        }

        public void refreshInventoryList(ArrayList<InventoryItem> inventoryList) {
            inventoryTable = new JTable(new InventoryTableModel(inventoryList));
            this.revalidate();
            this.repaint();
        }
    }
    class InventoryTableModel extends AbstractTableModel {
        private String[] columnNames = {"Item", "Data"};
        private Object[] data = {};

        public InventoryTableModel(ArrayList<InventoryItem> items) {
            ArrayList<String> tmp = new ArrayList<>();
            for (InventoryItem item: items) {
                tmp.add(String.format("Item: %s %d", item.getItemName(), item.getItemCount()));
            }
            data = tmp.toArray();
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }
    }

    @Override
    public void close() throws Exception {
        db.close();
    }
}