package backendTest.database;

import backend.database.DatabaseAdapter;
import backend.database.Reservation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

class TestDatabaseAdapter {
    private static final String validConfigPath = "/home/etorres/Programming/cs370/Project/repo/config.ini";
    private static DatabaseAdapter db;

    @BeforeAll
    static void beforeAll() throws SQLException, FileNotFoundException {
        db = new DatabaseAdapter(validConfigPath);
    }

    @AfterAll
    static void afterAll() throws Exception {
        db.close();
    }

    @Test
    void testInvalidPath() {
        assertThrows(FileNotFoundException.class, () -> new DatabaseAdapter((".non/existent/path")));
    }

    // Make sure getInventory can run without error normally
    @Test
    void testGetInventoryItems() throws Exception {
        db.getItems();
    }

    @Test
    void testAddDuplicateItem() throws Exception {
        db.addInventoryItem("Test Item", 5);
        assertThrows(SQLIntegrityConstraintViolationException.class, () -> db.addInventoryItem("Test Item", 6));
        db.deleteInventoryItem("Test Item");
    }

    @Test
    void testGetInventoryAmount() throws Exception {
        db.addInventoryItem("Test Item", 5);
        assertEquals(5, db.getInventoryAmount("Test Item"));
        db.deleteInventoryItem("Test Item");
    }

    @Test
    void testGetReservations() throws Exception {
        db.getReservations();
    }

    @Test
    void testAddDeleteReservation() throws Exception {
        Reservation r = new Reservation("test name", "test number", LocalDateTime.now(), 1, 1);
        db.addReservation(r);
        db.deleteReservationByName(r.getName());
    }

    @Test
    void testDate() {
        // parse valid date and time
        assertDoesNotThrow(() -> db.parseDateAndTime("2023-05-17", "14", "00"));
        //assertDoesNotThrow(() -> db.parseDateAndTime("05/17/23", "14", "00"));
        assertThrows(DateTimeParseException.class, () -> db.parseDateAndTime("05/17/23", "0", "00"));
    }
}