package backend;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Pattern;

/*
 * Config file formatting:
 * ; this is a comment
 * [sectionhaslettersornums]
 * key=value
 * keywithspace = valuewithspace
 */

public class ConfigFile {
    private final File configFile;
    private final ArrayList<ConfigSection> sections;

    /**
     * Parse an ini-style file for its options
     *
     * @param filePath absolute path of configuration file on system
     */
    public ConfigFile(String filePath) throws FileNotFoundException, IOError {
        configFile = new File(filePath);
        sections = new ArrayList<ConfigSection>();

        // Parse config file line-by-line
        Pattern comment = Pattern.compile("^;");
        Pattern configSection = Pattern.compile("^\\[[\\w]+\\]$");
        Pattern configOption = Pattern.compile("\\w+\s*=\s*[\\w\s]+");

        if (!configFile.isAbsolute())
            throw new FileNotFoundException("File " + filePath + " is not an absolute path");
        if (!configFile.exists())
            throw new FileNotFoundException("File " + filePath + " was not found on the filesystem");

        ConfigSection currentSection = null;

        try (Scanner fileReader = new Scanner(configFile)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                if (comment.matcher(line).matches()) {
                    continue; // ignore comments
                } else if (configSection.matcher(line).matches()) {
                    // strip []
                    String sectionName = line.replace("[", "");
                    sectionName = sectionName.replace("]", "");

                    currentSection = new ConfigSection(sectionName);
                    sections.add(currentSection);
                    continue;
                } else if (configOption.matcher(line).matches()) {
                    // file is in wrong format
                    if (currentSection == null) {
                        throw new RuntimeException("Config file is invalid, no config section found");
                    }
                    currentSection.parseOpt(line);
                    continue;
                }
            }
        }
    }

    public void dumpToStdout() {
        for (ConfigSection sec: sections) {
            System.out.println("Section: " + sec.getName());
            for(ConfigOption opt: sec.getOpts()) {
                System.out.println(opt);
            }
        }
    }

    public String getOption(String section, String option) throws IllegalArgumentException {
        return null;
    }

    private class ConfigSection {
        private final ArrayList<ConfigOption> opts;
        private final String name; // section name

        public ConfigSection(String name) {
            opts = new ArrayList<ConfigOption>();
            this.name = name;
        }

        /**
         * Parse a key-value pair and add to this config section.
         *
         * @param line key-value pair
         */
        public void parseOpt(String line) {
            String[] parts = line.split("\s*=\s*");
            opts.add(new ConfigOption(parts[0], parts[1]));
        }

        public String getName() {
            return name;
        }

        public ArrayList<ConfigOption> getOpts() {
            return opts;
        }
    }

    private class ConfigOption {
        private final String key;
        private final String value;

        public ConfigOption(String key, String value) {
            this.key = key.toLowerCase();
            this.value = value.toLowerCase();
        }

        public String getKey() { return key; }

        public String getValue() { return value; }

        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
