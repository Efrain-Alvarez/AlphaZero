package backendTest.config;

import backend.config.ConfigFile;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class TestConfigFile {
    @Test
    public void testNormalRead() {
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThrowsFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> new ConfigFile("/home/etorres/Programming/cs370/Project/repo/non-existent.ini"));
    }

    @Test
    public void testThrowsSectionNotFound() {
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            assertThrows(IllegalArgumentException.class, () -> f.getOption("nope", "someopt"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testThrowsOptionNotFound() {
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            assertThrows(IllegalArgumentException.class, () -> f.getOption("database", "nope"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void testRejectsSectionlessOption() {
        try {
            String path = "/home/etorres/Programming/cs370/Project/repo/tests/backendTest/configfile-test.ini";
            assertThrows(RuntimeException.class, () -> new ConfigFile(path));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testReadsAllOpts() {
        int expectedOpts = 5;
        try {
            ConfigFile f = new ConfigFile("/home/etorres/Programming/cs370/Project/repo/config.ini");
            assertThrows(IllegalArgumentException.class, () -> f.getOption("database", "nope"));
            assertEquals(f.size(), expectedOpts);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}