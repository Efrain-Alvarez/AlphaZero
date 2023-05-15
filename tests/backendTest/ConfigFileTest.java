package backendTest;

import backend.config.ConfigFile;
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


    @Test
    public void testRejectsSectionlessOption() {
        try {
            String path = "/home/etorres/Programming/cs370/Project/repo/tests/backendTest/configfile-test.ini";
            var s = assertThrows(RuntimeException.class, () -> new ConfigFile(path));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Test
    public void testReadsAllOpts() {
        int expectedOpts = 5;
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            var s = assertThrows(IllegalArgumentException.class, () -> f.getOption("database", "nope"));
            assertEquals(f.size(), expectedOpts);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}