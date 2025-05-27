package mg.mbds.emploi.poste;
import mg.mbds.emploi.poste.Entity.Poste;
import mg.mbds.emploi.poste.Model.CompetenceClient;
import mg.mbds.emploi.poste.Repository.PosteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "mg.mbds.emploi.poste.model")
public class PosteApplication {

	public static void main(String[] args) {
		SpringApplication.run(PosteApplication.class, args);
	}

	@Bean
	CommandLineRunner initPostes(PosteRepository posteRepository,
								 CompetenceClient competenceClient,
								 PlatformTransactionManager transactionManager) {
		return args -> {
			TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

			transactionTemplate.execute(status -> {
				List<Poste> postesInitiaux = Arrays.asList(
						Poste.builder()
								.id("CTO")
								.nom("Chief Technical Officer")
								.competences(Arrays.asList(1L, 2L))
								.build(),
						Poste.builder()
								.id("DEV")
								.nom("Développeur")
								.competences(Arrays.asList(3L, 4L))
								.build(),
						Poste.builder()
								.id("PM")
								.nom("Project Manager")
								.competences(Arrays.asList(5L, 6L))
								.build()
				);

				posteRepository.saveAll(postesInitiaux);
				return null;
			});

			// For logging only - fetch fresh data in new transaction
			transactionTemplate.execute(status -> {
				posteRepository.findAll().forEach(poste -> {
					System.out.println("Poste initialisé: " + poste.getId());
					// This will work because we're in a transaction
					if (poste.getCompetences() != null) {
						poste.getCompetences().forEach(competenceId -> {
							System.out.println("   - Compétence ID: " + competenceId);
						});
					}
				});
				return null;
			});
		};
	}
}