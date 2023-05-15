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
    public void testNormalRead() {
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            f.dumpToStdout();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void testThrowsFileNotFound() {
        FileNotFoundException t = assertThrows(FileNotFoundException.class, () -> new ConfigFile("/home/etorres/Programming/cs370/Project/repo/non-existent.ini"));
    }

    @Test
    public void testThrowsSectionNotFound() {
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            IllegalArgumentException s = assertThrows(IllegalArgumentException.class, () -> f.getOption("nope", "someopt"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void testThrowsOptionNotFound() {
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            IllegalArgumentException s = assertThrows(IllegalArgumentException.class, () -> f.getOption("database", "nope"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}