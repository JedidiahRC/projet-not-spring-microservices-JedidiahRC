package mg.mbds.emploi.competence.Service;

import mg.mbds.emploi.competence.Dto.CompetenceDto;

import java.util.List;

public interface CompetenceService {
    CompetenceDto save(CompetenceDto competenceDto);
    List<CompetenceDto> findAll();
    CompetenceDto findById(Long id);
    void delete(Long id);
    CompetenceDto update(Long id, CompetenceDto competenceDto);
}
