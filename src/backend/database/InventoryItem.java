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

    /**
     * Constructor for <code>InventoryItem</code> class. Note that any names are converted to lowercase
     * for consistency.
     * @param name name of item
     * @param amount a positive integer indicating quantity of item on hand
     * @throws RuntimeException if the amount was negative
     */
    public InventoryItem(String name, int amount) throws RuntimeException {
        this.itemName = name.toLowerCase();
        this.amount = amount;

        if (amount < 0)
            throw new RuntimeException("Item amount " + amount + " was negative, must be positive");
    }

    /**
     * @return this item's name
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * @return the stored amount of this item on the database
     */
    public int getItemCount() {
        return amount;
    }

    /**
     * Change the amount of this item.
     * @param newAmount the new amount to set item to
     * @throws RuntimeException if the new amount is a negative number
     */
    public void setAmount(int newAmount) throws RuntimeException{
        if (newAmount < 0) {
            throw new RuntimeException("Item amount " + newAmount + " was negative, must be positive");
        }
        amount = newAmount;
    }
}
