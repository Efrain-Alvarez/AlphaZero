package userinterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dashboard extends JFrame{

    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLayout(null);
        // // Create a new JLabel with the background image
        // ImageIcon backgroundImage = new ImageIcon("D:/efrai/Pictures/Camera Roll/rocks.jpg");
        // JLabel backgroundLabel = new JLabel(backgroundImage);
        // backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        
        // // Add the background label to the content pane of the frame
        // Container contentPane = frame.getContentPane();
        // contentPane.add(backgroundLabel);
        
        String[] menu = {"Lunch", "Dinner"};
        String[] reservation = {"Make Reservation", "Cancel Reservation"};
        String[] managementSide = {"Contact Management", "Check Inventory", "Update Inventory"};
        // Create a new combo box using the menu array
        JComboBox<String> comboBox = new JComboBox<>(menu);
        JComboBox<String> comboBox2 = new JComboBox<>(reservation);
        JComboBox<String> comboBox3 = new JComboBox<>(managementSide);
        // Set the initial selected item of the combo box
        comboBox.setSelectedIndex(0);
        comboBox2.setSelectedIndex(0);
        comboBox3.setSelectedIndex(0);

        //formatting of comboBoxes
        comboBox.setBounds(50, 30, 200, 25);
        comboBox2.setBounds(280, 30, 200, 25);
        comboBox3.setBounds(510, 30, 200, 25);
        
        // Add an ActionListener to the combo box
        // comboBox.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         // Get the selected item from the combo box
        //         String selected = (String) comboBox.getSelectedItem();
                
        //         // Perform some action based on the selected item
        //         System.out.println("Selected: " + selected);
        //     }
        // });


        // Make the frame visible
        frame.add(comboBox);
        frame.add(comboBox2);
        frame.add(comboBox3);
        frame.setVisible(true);
    }

}


