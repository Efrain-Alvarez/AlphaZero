package backendTest;

import backend.database.DatabaseAdapter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

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
    void testDatabaseConnection() throws FileNotFoundException, SQLException {
        DatabaseAdapter db = new DatabaseAdapter(configPath);
    }

    //@Test
    //void testReservationCreationAndDeletion() throws FileNotFoundException, SQLException {
    //    DatabaseAdapter db = new DatabaseAdapter(configPath);
    //    Reservation r = new Reservation("Eric Torres", "5555555555", null, 3);
    //    db.addReservation(r);
    //}

    // Make sure getInventory can run without error normally
    @Test
    void testGetInventoryItems() throws FileNotFoundException, SQLException {
        DatabaseAdapter db = new DatabaseAdapter(configPath);
        var items = db.getItems();
    }

    @Test
    void testAddDuplicateItem() throws FileNotFoundException, SQLException {
        DatabaseAdapter db = new DatabaseAdapter(configPath);
        db.addInventoryItem("Test Item", 5);
        assertThrows(SQLException.class, () -> db.addInventoryItem("Test Item", 6));
        db.deleteInventoryItem("Test Item");
    }
}