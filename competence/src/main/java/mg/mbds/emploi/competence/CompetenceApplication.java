package mg.mbds.emploi.competence;

import mg.mbds.emploi.competence.Entity.Competence;
import mg.mbds.emploi.competence.Repository.CompetenceRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class CompetenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetenceApplication.class, args);
		System.out.println("*****************Competence Started***************");
	}
	@Bean
	CommandLineRunner start(CompetenceRepository competenceRepository) {
		return args -> {
			competenceRepository.save(Competence.builder().nom("Java").build());
			competenceRepository.save(Competence.builder().nom("Spring Boot").build());
			competenceRepository.save(Competence.builder().nom("Microservices").build());
		};
	}

}
