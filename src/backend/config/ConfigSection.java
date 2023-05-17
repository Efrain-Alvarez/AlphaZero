package backend.config;

import java.io.IOError;
import java.util.ArrayList;

public class ConfigSection {
    private final ArrayList<ConfigOption> opts;
    private final String name; // section name

    public ConfigSection(String name) {
        opts = new ArrayList<>();
        this.name = name;
    }

    /**
     * Parse a key-value pair and add to this config section.
     *
     * @param line key-value pair
     */
    public void parseOpt(String line) {
        try {
            String[] parts = line.split(" *= *");
            opts.add(new ConfigOption(parts[0], parts[1]));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IOError(e);
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<ConfigOption> getOpts() {
        return opts;
    }
}
