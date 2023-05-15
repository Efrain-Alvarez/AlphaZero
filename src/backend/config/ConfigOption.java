package backend.config;

public record ConfigOption(String key, String value) {
    public ConfigOption(String key, String value) {
        this.key = key.toLowerCase();
        this.value = value.toLowerCase();
    }
}
