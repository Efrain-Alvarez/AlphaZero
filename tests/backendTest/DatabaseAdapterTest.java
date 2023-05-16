package backendTest;

import backend.database.DatabaseAdapter;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

class DatabaseAdapterTest {
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
    void testDatabaseConnection() throws FileNotFoundException {
        DatabaseAdapter db = new DatabaseAdapter(configPath);
    }
}