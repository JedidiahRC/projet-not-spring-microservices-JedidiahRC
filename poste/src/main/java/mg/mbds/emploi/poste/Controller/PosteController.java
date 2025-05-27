package mg.mbds.emploi.poste.Controller;

import lombok.RequiredArgsConstructor;
import mg.mbds.emploi.poste.Dto.ErrorResponse;
import mg.mbds.emploi.poste.Dto.PosteDto;
import mg.mbds.emploi.poste.Service.PosteService;
import mg.mbds.emploi.poste.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/postes")
@RequiredArgsConstructor
public class PosteController {

    private final PosteService posteService;
    private static final Logger log = LoggerFactory.getLogger(PosteController.class);

    @PostMapping
    public ResponseEntity<?> createPoste(@RequestBody PosteDto posteDto) {
        try {
            log.info("Creation de poste: ", posteDto);
            PosteDto createdPoste = posteService.createPoste(posteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPoste);
        } catch (Exception e) {
            log.error("Erreur creatyion poste: ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Erreur creation poste",
                            e.getMessage()
                    ));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPostes() {
        try {
            log.info("Get postes");
            List<PosteDto> postes = posteService.getAllPostes();
            return ResponseEntity.ok(postes);
        } catch (Exception e) {
            log.error("Erreur sur get postes", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Erreur sur get postes",
                            e.getMessage()
                    ));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPosteById(@PathVariable String id) {
        try {
            log.info("Recherche par id", id);
            PosteDto poste = posteService.getPosteById(id);
            return ResponseEntity.ok(poste);
        } catch (ResourceNotFoundException e) {
            log.error("Id non trouve", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Poste non trouve",
                            e.getMessage()
                    ));
        } catch (Exception e) {
            log.error("Erreur recherche par id", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Erreur recherche par id",
                            e.getMessage()
                    ));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePoste(@PathVariable String id) {
        try {
            log.info("Suppression...", id);
            posteService.deletePoste(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            log.error("Poste introuvable", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Poste introuvable",
                            e.getMessage()
                    ));
        } catch (Exception e) {
            log.error("Erreur de suppression", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Erreur de suppression",
                            e.getMessage()
                    ));
        }
    }

    @PostMapping("/{posteId}/competences/{competenceId}")
    public ResponseEntity<?> addCompetenceToPoste(
            @PathVariable String posteId,
            @PathVariable Long competenceId) {
        try {
            log.info("Ajout de competence a un poste", competenceId, posteId);
            PosteDto updatedPoste = posteService.addCompetenceToPoste(posteId, competenceId);
            return ResponseEntity.ok(updatedPoste);
        } catch (ResourceNotFoundException e) {
            log.error("Ressource manquante", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Ressource manquante",
                            e.getMessage()
                    ));
        } catch (Exception e) {
            log.error("Erreur ajout competence au poste", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            LocalDateTime.now(),
                            "Erreur ajout competence au poste",
                            e.getMessage()
                    ));
        }
    }
}