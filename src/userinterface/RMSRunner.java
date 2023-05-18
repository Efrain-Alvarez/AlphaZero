package userinterface;

import javax.swing.*;

public class RMSRunner {

    public static void main(String[] args) {
        try (Dashboard d = new Dashboard()) {
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
