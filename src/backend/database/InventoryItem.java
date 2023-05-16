package backend.database;

/**
 * Representation of a single list item on an inventory.
 */
public class InventoryItem {
    private String itemName;
    private int amount;

    public InventoryItem() {
        itemName = "unnamed item";
        amount = 0;
    }

    public InventoryItem(String name, int amount) {
        this.itemName = name;
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemCount() {
        return amount;
    }

    public void setAmount(int newAmount) {
        amount = newAmount;
    }
}
