package mg.mbds.emploi.competence.Service;

import lombok.RequiredArgsConstructor;
import mg.mbds.emploi.competence.Dto.CompetenceDto;
import mg.mbds.emploi.competence.Entity.Competence;
import mg.mbds.emploi.competence.Mapper.CompetenceMapper;
import mg.mbds.emploi.competence.Repository.CompetenceRepository;
import mg.mbds.emploi.competence.Service.CompetenceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetenceServiceImpl implements CompetenceService {
    private final CompetenceRepository competenceRepository;
    private final CompetenceMapper competenceMapper;

    @Override
    public CompetenceDto save(CompetenceDto competenceDto) {
        Competence competence = competenceMapper.toEntity(competenceDto);
        Competence savedCompetence = competenceRepository.save(competence);
        return competenceMapper.toDto(savedCompetence);
    }

    @Override
    public List<CompetenceDto> findAll() {
        return competenceRepository.findAll()
                .stream()
                .map(competenceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CompetenceDto findById(Long id) {
        Competence competence = competenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competence not found"));
        return competenceMapper.toDto(competence);
    }

    @Override
    public void delete(Long id) {
        competenceRepository.deleteById(id);
    }

    @Override
    public CompetenceDto update(Long id, CompetenceDto competenceDto) {
        Competence existingCompetence = competenceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Competence not found"));

        existingCompetence.setNom(competenceDto.getNom());

        Competence updatedCompetence = competenceRepository.save(existingCompetence);
        return competenceMapper.toDto(updatedCompetence);
    }
}