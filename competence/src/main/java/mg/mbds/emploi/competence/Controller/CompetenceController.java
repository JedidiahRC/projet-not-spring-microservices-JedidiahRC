package mg.mbds.emploi.competence.Controller;


import lombok.RequiredArgsConstructor;
import mg.mbds.emploi.competence.Dto.CompetenceDto;
import mg.mbds.emploi.competence.Mapper.CompetenceMapper;
import mg.mbds.emploi.competence.Service.CompetenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/competences")
@RequiredArgsConstructor
public class CompetenceController {
    private final CompetenceService competenceService;
    private final CompetenceMapper competenceMapper;
    @GetMapping
    public ResponseEntity<List<CompetenceDto>> getAllCompetences() {
        return ResponseEntity.ok(competenceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetenceDto> getCompetenceById(@PathVariable Long id) {
        return ResponseEntity.ok(competenceService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CompetenceDto> createCompetence(@RequestBody CompetenceDto competenceDto) {
        return new ResponseEntity<>(competenceService.save(competenceDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetenceDto> updateCompetence(@PathVariable Long id, @RequestBody CompetenceDto competenceDto) {
        return ResponseEntity.ok(competenceService.update(id, competenceDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetence(@PathVariable Long id) {
        competenceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}