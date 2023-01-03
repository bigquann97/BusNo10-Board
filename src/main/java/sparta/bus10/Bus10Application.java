package sparta.bus10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Bus10Application {

    public static void main(String[] args) {
        SpringApplication.run(Bus10Application.class, args);
    }

}
