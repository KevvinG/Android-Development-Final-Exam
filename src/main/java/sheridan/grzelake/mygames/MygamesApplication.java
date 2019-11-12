package sheridan.grzelake.mygames;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MygamesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MygamesApplication.class, args);
	}
}
