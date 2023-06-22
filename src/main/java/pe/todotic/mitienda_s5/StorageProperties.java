package pe.todotic.mitienda_s5;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    private Location location;

    @Getter
    @Setter
    public static class Location {
        private String path;
    }
}
