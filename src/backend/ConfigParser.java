package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * Config file formatting:
 * ; this is a comment
 * [sectionhaslettersornums]
 * key=value
 * keywithspace = valuewithspace
 * ; dontaddspaces = to anything
 */

public class ConfigParser {
    private final File configFile;

    /**
     * Parse an ini-style file for its options
     * @param filePath absolute path of configuration file on system
     */
    public ConfigParser(String filePath) throws FileNotFoundException, IOError {
        configFile = new File(filePath);
        /*
        Pattern comment = Pattern.compile("^;");
        Pattern configSection = Pattern.compile("^\[[\w]+\]$");
        Pattern configOption = Pattern.compile("\w+ *= *\w+");
         */

        if (!configFile.isAbsolute())
            throw new FileNotFoundException("File " + filePath + " is not an absolute path");
        if (!configFile.exists())
            throw new FileNotFoundException("File " + filePath + " was not found on the filesystem");

        try (Scanner fileReader = new Scanner(configFile)) {
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                // check if comment
                // check if config section
                // check if config option
                // default case
            }
        }
    }
    private class ConfigSection {
        private final ArrayList<ConfigOption> opts;

        public ConfigSection() {
            opts = new ArrayList<ConfigOption>();
        }

        /**
         * Parse a key-value pair and add to this config section.
         * @param line key-value pair
         */
        public void parseOpt(String line) {

        }
        private class ConfigOption {
            private final String key;
            private final String value;

            public ConfigOption(String key, String value) {
                this.key = key.toLowerCase();
                this.value = value.toLowerCase();
            }

            public String toString() {
                return this.key+ "=" + this.value;
            }
        }   }
}


