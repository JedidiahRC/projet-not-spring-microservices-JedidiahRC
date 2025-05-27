package mg.mbds.emploi.poste.Model;

import mg.mbds.emploi.poste.Dto.CompetenceResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPETENCE", url = "http://localhost:8080")
public interface CompetenceClient {
    @GetMapping("/api/competences/{id}")
    CompetenceResponseDto getCompetenceById(@PathVariable Long id);
}
