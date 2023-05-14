package backendTest;

import backend.ConfigFile;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ConfigFileTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @Test
    public void testRead() {
        try {
            ConfigFile f = new ConfigFile("/Users/etorres/testfile.conf");
            f.dumpToStdout();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}