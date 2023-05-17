package backendTest.database;

import backend.database.InventoryItem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestInventoryItem {
    @Test
    void testGetItemName() {
        InventoryItem i = new InventoryItem("CAPITAL NAME", 0);
        assertEquals("capital name", i.getItemName());

        InventoryItem j = new InventoryItem("lowercase name", 0);
        assertEquals("lowercase name", j.getItemName());
    }

    @Test
    void getItemCount() {
        InventoryItem i = new InventoryItem("Item", 1);
        assertEquals(1, i.getItemCount());
    }

    @Test
    void setAmount() {
        InventoryItem i = new InventoryItem("Item", 1);
        i.setAmount(2);
        assertEquals(2, i.getItemCount());
        assertThrows(RuntimeException.class, () -> i.setAmount(-1));
    }

    @Test
    void testRejectsNegativeAmount() {
        assertThrows(RuntimeException.class, () -> new InventoryItem("Item", -1));
    }

    @Test
    void testDefaultConstructor() {
        InventoryItem testItem = new InventoryItem();
        assertEquals("unknown", testItem.getItemName());
        assertEquals(0, testItem.getItemCount());
    }
}