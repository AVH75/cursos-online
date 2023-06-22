package pe.todotic.mitienda_s5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class MitiendaS5Application {

    public static void main(String[] args) {
        SpringApplication.run(MitiendaS5Application.class, args);
    }

}
