package backendTest.database;

import backend.database.DatabaseAdapter;
import backend.database.Reservation;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class TestDatabaseAdapter {
    private static final String configPath = "/home/etorres/Programming/cs370/Project/repo/config.ini";

    @Test
    void testDatabaseConnection() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
        }
        assertThrows(FileNotFoundException.class, () -> new DatabaseAdapter((".non/existent/path")));
    }

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

    @Test
    void testGetReservations() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            var reservations = db.getReservations();
        }
    }

    @Test
    void testAddDeleteReservation() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            Reservation r = new Reservation("test name", "test number", LocalDateTime.now(), 1, 1);
            db.addReservation(r);
            db.deleteReservationByName(r.getName());
        }
    }

    @Test
    void testDate() throws Exception {
        try (DatabaseAdapter db = new DatabaseAdapter(configPath)) {
            // parse valid date and time
            assertDoesNotThrow(() -> db.parseDateAndTime("2023-05-17", "14", "00"));
            //assertDoesNotThrow(() -> db.parseDateAndTime("05/17/23", "14", "00"));
            assertThrows(DateTimeParseException.class, () -> db.parseDateAndTime("05/17/23", "0", "00"));
        }
    }
}