package mg.mbds.emploi.poste.Service;

import lombok.RequiredArgsConstructor;
import mg.mbds.emploi.poste.Dto.PosteDto;
import mg.mbds.emploi.poste.Entity.Poste;
import mg.mbds.emploi.poste.Mapper.PosteMapper;
import mg.mbds.emploi.poste.Model.CompetenceClient;
import mg.mbds.emploi.poste.Repository.PosteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PosteServiceImpl implements PosteService {

    private final PosteRepository posteRepository;
    private final CompetenceClient competenceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private static final Logger log = LoggerFactory.getLogger(PosteServiceImpl.class);

    @Override
    public PosteDto createPoste(PosteDto posteDto) {
        Poste poste = PosteMapper.toEntity(posteDto);
        Poste savedPoste = posteRepository.save(poste);
        return PosteMapper.toDto(savedPoste, competenceClient);
    }

//    public PosteDto getPosteWithCompetences(String id) {
//        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("competenceService");
//        return circuitBreaker.run(
//                () -> {
//                    // Appel normal au service
//                    return competenceClient.getCompetences(id);
//                },
//                throwable -> {
//                    // Fallback en cas d'échec
//                    return getDefaultCompetences();
//                }
//        );
//    }

    @Override
    @Transactional(readOnly = true)
    public PosteDto getPosteById(String id) {
        Poste poste = posteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Poste not found"));
        return PosteMapper.toDto(poste, competenceClient);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PosteDto> getAllPostes() {
        List<Poste> postes = posteRepository.findAll();
        return postes.stream()
                .map(poste -> PosteMapper.toDto(poste, competenceClient))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePoste(String id) {
        posteRepository.deleteById(id);
    }

    @Override
    public PosteDto addCompetenceToPoste(String posteId, Long competenceId) {
        Poste poste = posteRepository.findById(posteId)
                .orElseThrow(() -> new RuntimeException("Poste not found"));

        if (!poste.getCompetences().contains(competenceId)) {
            poste.getCompetences().add(competenceId);
            Poste updatedPoste = posteRepository.save(poste);
            return PosteMapper.toDto(updatedPoste, competenceClient);
        }

        return PosteMapper.toDto(poste, competenceClient);
    }
}