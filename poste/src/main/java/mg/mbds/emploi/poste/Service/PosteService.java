package mg.mbds.emploi.poste.Service;

import mg.mbds.emploi.poste.Dto.PosteDto;

import java.util.List;

public interface PosteService {
    PosteDto createPoste(PosteDto posteDto);
    PosteDto getPosteById(String id);
    List<PosteDto> getAllPostes();
    void deletePoste(String id);
    PosteDto addCompetenceToPoste(String posteId, Long competenceId);
}