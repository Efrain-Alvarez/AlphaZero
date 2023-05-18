package userinterface;

import backend.database.InventoryItem;

import java.util.ArrayList;

public class InventoryDisplay {
    private ArrayList<InventoryItem> items;
    String[] columnNames = {"Item Name", "Count"};
    Object[][] displayData;

    public InventoryDisplay() {
        items = new ArrayList<>();
        displayData = null;
    }

    public void refreshItems(ArrayList<InventoryItem> items) {
        this.items = items;
        //displayData = {columnNames[0], {}},{columnNames[1],{}};
    }
}