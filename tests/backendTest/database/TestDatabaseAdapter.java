package backendTest.database;

import backend.database.DatabaseAdapter;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestDatabaseAdapter {
    private static String configPath = "/home/etorres/Programming/cs370/Project/repo/config.ini";

    @Test
    void getInventoryAmount() {
    }

    @Test
    void getItems() {
    }

    @Test
    void addReservation() {
    }

    @Test
    void getReservations() {
    }

    @Test
    void testDatabaseConnection() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            db.getReservations();
        }
    }

    //@Test
    //void testReservationCreationAndDeletion() throws Exception {
    //    DatabaseAdapter db = new DatabaseAdapter(configPath);
    //    Reservation r = new Reservation("Eric Torres", "5555555555", null, 3);
    //    db.addReservation(r);
    //}

    // Make sure getInventory can run without error normally
    @Test
    void testGetInventoryItems() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            var items = db.getItems();
        }
    }

    @Test
    void testAddDuplicateItem() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            db.addInventoryItem("Test Item", 5);
            assertThrows(SQLIntegrityConstraintViolationException.class, () -> db.addInventoryItem("Test Item", 6));
            db.deleteInventoryItem("Test Item");
        }
    }

    @Test
    void testGetInventoryAmount() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            db.addInventoryItem("Test Item", 5);
            assertEquals(5, db.getInventoryAmount("Test Item"));
            db.deleteInventoryItem("Test Item");
        }
    }
}