import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class GUI{
    // creating GUI
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(550, 300));
        frame.getContentPane().setBackground(Color.GRAY);
        frame.pack();
        frame.setVisible(true);
    }
}

