package backend.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/*
 * Config file formatting:
 * ; this is a comment
 * [sectionhaslettersornums]
 * key=value
 * keywithspace = valuewithspace
 */
public class ConfigFile {
    private static final Logger logger = Logger.getLogger(ConfigFile.class.getName());
    private final ArrayList<ConfigSection> sections;

    /**
     * Parse an ini-style file for its options
     *
     * @param filePath absolute path of configuration file on system
     * @throws FileNotFoundException if path points to a file that does not exist
     * @throws IOError if an option in the config file could not be read
     */
    public ConfigFile(String filePath) throws FileNotFoundException, IOError {
        logger.log(Level.FINER, "Trying to create a ConfigFile object...");
        logger.log(Level.FINER, "File path: " + filePath);
        final File configFile = new File(filePath);
        sections = new ArrayList<>();

        // Parse config file line-by-line
        Pattern comment = Pattern.compile("^;");
        Pattern configSection = Pattern.compile("^\\[[\\w]+\\]$");
        Pattern configOption = Pattern.compile("\\w+\s*=\s*[\\w\s.-]+");

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
                } else if (configOption.matcher(line).matches()) {
                    // file is in wrong format
                    if (currentSection == null) {
                        throw new RuntimeException("Config file is invalid, no config section found");
                    }
                    currentSection.parseOpt(line);
                }
            }
        }

        logger.log(Level.FINE, "Number of options parsed: " + size());
    }

    /**
     * Retrieve an option from a specific section.
     * For instance if you have the following config file:<br><br>
     * <code>
     *     [section]<br>
     *     opt1 = val1<br>
     *     opt2 = val2<br>
     * </code><br>
     * and in your code you had:<br>
     * <code>
     *     ConfigFile f = new ConfigFile("/path/to/config");
     * </code><br>
     * then a call to retrieve opt1 would be:<br>
     * <code>f.getOption("section", "opt1"</code>
     *
     * @param section the section to look for denoted by [option]
     * @param option  the specific option in the given section
     * @return the value for the option in the given section
     * @throws IllegalArgumentException if the config section or option does not exist
     */
    public String getOption(String section, String option) throws IllegalArgumentException {
        for (ConfigSection sec : sections) {
            if (sec.getName().equals(section.toLowerCase())) {
                for (ConfigOption opt : sec.getOpts()) {
                    if (opt.key().equals(option.toLowerCase())) {
                        return opt.value();
                    }
                }
                throw new IllegalArgumentException("Unable to find config option: " + section + " in file");
            }
        }
        throw new IllegalArgumentException("Unable to find config section: " + section + " in file");
    }

    public void dumpToStdout() {
        for (ConfigSection sec : sections) {
            System.out.println("Section: " + sec.getName());
            for (ConfigOption opt : sec.getOpts()) {
                System.out.println(opt);
            }
        }
    }

    /**
     * @return the total number of options that this config file has parsed
     */
    public int size() {
        int result = 0;
        for (ConfigSection sec : sections) {
            for (ConfigOption ignored : sec.getOpts()) {
                result++;
            }
        }
        return result;
    }
}
